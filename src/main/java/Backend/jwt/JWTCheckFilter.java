package Backend.jwt;

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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
public class JWTCheckFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper = new ObjectMapper();
    // private final RoleRepository roleRepository;

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
            String token = extractToken(request);
            if (token != null) {
                JwtTokenProvider.validateToken(token);

                Claims claims = JwtTokenProvider.extractAllClaims(token);
                
                Object roleNamesObject = claims.get("roleNames", List.class);
                if (!(roleNamesObject instanceof List<?> rolesList) || rolesList.isEmpty() || !(rolesList.get(0) instanceof String)) {
                    log.error("Invalid roleNames format");
                    handleError(response);
                    return;
                }
                    // 현재 요청 경로
                String requestPath = request.getRequestURI();

                // 현재 요청 경로가 사용자 권한목록에 포함되어 있는지 확인
                if (!rolesList.stream().anyMatch(role -> requestPath.startsWith((String) role))) {
                    handleError(response);
                    return;
                }

                filterChain.doFilter(request, response);
            } else {
                handleError(response);
            }
        } catch (Exception e) {
            handleError(response);
        }
    }

    private String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    private void handleError(HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        Map<String, String> error = Map.of("error", "ERROR_ACCESS_TOKEN");
        objectMapper.writeValue(response.getOutputStream(), error);
    }
}