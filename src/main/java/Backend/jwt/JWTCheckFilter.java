package Backend.jwt;

import Backend.role.entity.RoleEntity;
import Backend.role.entity.UserRoleEntity;
import Backend.role.repository.RoleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

// import Backend.role.repository.RoleRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.lang.NonNull;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
public class JWTCheckFilter extends OncePerRequestFilter {
    private final RoleRepository roleRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected boolean shouldNotFilter(@NonNull HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        boolean isLoginOrSignup = requestURI.startsWith("/api/user/login") || requestURI.startsWith("/api/user/signup");
        return isLoginOrSignup;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        try {
            // 1. 토큰 추출 및 검증
            String token = extractToken(request);
            if (token == null) {
                log.warn("No token found in request: {}", request.getRequestURI());
                handleError(response, "TOKEN_NOT_FOUND");
                return;
            }

            // 2. 토큰 유효성 검증
            try {
                JwtTokenProvider.validateToken(token);
            } catch (Exception e) {
                log.error("Token validation failed: {}", e.getMessage());
                handleError(response, "INVALID_TOKEN");
                return;
            }

            // 3. 클레임 추출 및 권한 검사
            Claims claims = JwtTokenProvider.extractAllClaims(token);
            List<UserRoleEntity> userRoleEntityList = extractRoleNames(claims);

            if (userRoleEntityList.isEmpty()) {
                log.warn("No roles found in token for request: {}", request.getRequestURI());
                handleError(response, "NO_ROLES_FOUND");
                return;
            }

            // 4. 요청 URL에 대한 권한 검사
            if (!hasRequiredRole(userRoleEntityList, request.getRequestURI())) {
                log.warn("Access denied for request: {} with roles: {}", request.getRequestURI(), userRoleEntityList);
                handleError(response, "ACCESS_DENIED");
                return;
            }

            // 5. 인증 객체 생성 및 SecurityContext 설정
            Authentication authentication = createAuthentication(claims, userRoleEntityList);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // 6. 다음 필터로 요청 전달
            filterChain.doFilter(request, response);

        } catch (Exception e) {
            log.error("Unexpected error during JWT validation: {}", e.getMessage(), e);
            handleError(response, "INTERNAL_SERVER_ERROR", e.getMessage());
        }
    }

    private String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    private List<UserRoleEntity> extractRoleNames(Claims claims) {
        try {
            Object roleNamesObject = claims.get("roleNames", List.class);
            if (roleNamesObject instanceof List<?>) {
                return ((List<?>) roleNamesObject).stream()
                    .filter(obj -> obj instanceof Map)
                    .map(obj -> {
                        Map<?, ?> map = (Map<?, ?>) obj;
                        UserRoleEntity userRole = new UserRoleEntity();
                        userRole.setUserId(((Number) map.get("userId")).longValue());
                        userRole.setRoleId(((Number) map.get("roleId")).longValue());
                        return userRole;
                    })
                    .collect(Collectors.toList());
            }
            return Collections.emptyList();
        } catch (Exception e) {
            log.error("Error extracting role names: {}", e.getMessage());
            return Collections.emptyList();
        }
    }

    private boolean hasRequiredRole(List<UserRoleEntity> userRoles, String requestUri) {
        return userRoles.stream()
                .<Optional<RoleEntity>>map(roleName -> roleRepository.findById(roleName.getRoleId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .anyMatch(role -> requestUri.startsWith(role.getUrl()));
    }

    private Authentication createAuthentication(Claims claims, List<UserRoleEntity> userRoles) {
        String username = claims.getSubject();
        List<SimpleGrantedAuthority> authorities = userRoles.stream()
                .map(userRoleEntity -> new SimpleGrantedAuthority(userRoleEntity.getRoleId().toString()))
                .collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(username, null, authorities);
    }
    private void handleError(HttpServletResponse response, String errorCode, String errorMessage) throws IOException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        Map<String, String> error = Map.of(
                "error", errorCode,
                "message", errorMessage
        );

        objectMapper.writeValue(response.getOutputStream(), error);
    }

    private void handleError(HttpServletResponse response, String errorCode) throws IOException {
        handleError(response, errorCode, "Authentication failed");
    }
}