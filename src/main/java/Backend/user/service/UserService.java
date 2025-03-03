package Backend.user.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import Backend.config.ResponseCode;
import Backend.jwt.JwtTokenProvider;
import Backend.role.service.RoleService;
import Backend.user.dto.UserRequestDto;
import Backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final RoleService roleService;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<Map<String, Object>> signup(UserRequestDto user) {
        // username 중복 체크
        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity.status(400)
                .body(Map.of("code", ResponseCode.USER_EXISTS.getCode(), "message", ResponseCode.USER_EXISTS.getMessage()));
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return ResponseEntity.status(200)
                .body(Map.of("code", ResponseCode.SUCCESS.getCode(), "message", ResponseCode.SUCCESS.getMessage()));
        }
    }

    public ResponseEntity<Map<String, Object>> login(UserRequestDto user) {        
        UserRequestDto loginUser = userRepository.findByUsername(user.getUsername());
        if (loginUser == null) {
            return ResponseEntity.status(400)
                .body(Map.of("code", ResponseCode.USER_NOT_FOUND.getCode(), "message", ResponseCode.USER_NOT_FOUND.getMessage()));
        }

        if (!passwordEncoder.matches(user.getPassword(), loginUser.getPassword())) {
            return ResponseEntity.status(400)
                .body(Map.of("code", ResponseCode.USER_NOT_FOUND.getCode(), "message", ResponseCode.USER_NOT_FOUND.getMessage()));
        } else {
            Map<String, Object> claims = new HashMap<>();
            claims.put("username", loginUser.getUsername());
            claims.put("userId", loginUser.getId());

            claims.put("roleNames", roleService.getRolesByUserId(loginUser.getId()));


            String token = JwtTokenProvider.generateToken(claims, 30);
            String refreshToken = JwtTokenProvider.generateToken(claims, 60);
            return ResponseEntity.status(200)
                .body(Map.of(
                    "code", ResponseCode.SUCCESS.getCode(), 
                    "message", ResponseCode.SUCCESS.getMessage(), 
                    "accessToken", token, 
                    "refreshToken", refreshToken,
                    "username", loginUser.getUsername(),
                    "userId", loginUser.getId()
                ));
        }
    }

} 