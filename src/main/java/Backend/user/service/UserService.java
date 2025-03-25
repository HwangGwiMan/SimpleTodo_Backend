package Backend.user.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import Backend.common.util.DateUtil;
import Backend.role.entity.UserRoleEntity;
import Backend.role.repository.UserRoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import Backend.config.ResponseCode;
import Backend.jwt.JwtTokenProvider;
import Backend.role.service.RoleService;
// import Backend.role.service.RoleService;
import Backend.user.dto.UserRequestDto;
import Backend.user.repository.UserRepository;
// import Backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import Backend.user.conv.UserConv;
import Backend.user.entity.UserEntity;

@Service
@RequiredArgsConstructor
public class UserService {
    private final RoleService roleService;

    private final UserConv userConv;
    private final UserRepository userRepository;

    private final UserRoleRepository userRoleRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public ResponseEntity<Map<String, Object>> signup(UserRequestDto user) {
        // username 중복 체크
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.status(400)
                .body(Map.of("code", ResponseCode.USER_EXISTS.getCode(), "message", ResponseCode.USER_EXISTS.getMessage()));
        } 
        else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            UserEntity userEntity = userConv.toEntity(user);
            userRepository.save(userEntity);
            
            // 기본 권한 부여
            roleService.getAllRoles().forEach(role -> {
                UserRoleEntity userRoleEntity = new UserRoleEntity();
                userRoleEntity.setUser_id(userEntity.getId());
                userRoleEntity.setRole_id(role.getId());
                userRoleRepository.save(userRoleEntity);
            });

            return ResponseEntity.status(200)
                .body(Map.of("code", ResponseCode.SUCCESS.getCode(), "message", ResponseCode.SUCCESS.getMessage()));
        }
    }

    public ResponseEntity<Map<String, Object>> login(UserRequestDto user) {        
        // UserRequestDto loginUser = userRepository.findByUsername(user.getUsername());
        // if (loginUser == null) {
        //     return ResponseEntity.status(400)
        //         .body(Map.of("code", ResponseCode.USER_NOT_FOUND.getCode(), "message", ResponseCode.USER_NOT_FOUND.getMessage()));
        // }

        // if (!passwordEncoder.matches(user.getPassword(), loginUser.getPassword())) {
        //     return ResponseEntity.status(400)
        //         .body(Map.of("code", ResponseCode.USER_NOT_FOUND.getCode(), "message", ResponseCode.USER_NOT_FOUND.getMessage()));
        // } else {
        //     Map<String, Object> claims = new HashMap<>();
        //     claims.put("username", loginUser.getUsername());
        //     claims.put("userId", loginUser.getId());

        //     claims.put("roleNames", roleService.getRolesByUserId(loginUser.getId()));


        //     String token = JwtTokenProvider.generateToken(claims, 30);
        //     String refreshToken = JwtTokenProvider.generateToken(claims, 60);
        //     return ResponseEntity.status(200)
        //         .body(Map.of(
        //             "code", ResponseCode.SUCCESS.getCode(), 
        //             "message", ResponseCode.SUCCESS.getMessage(), 
        //             "accessToken", token, 
        //             "refreshToken", refreshToken,
        //             "username", loginUser.getUsername(),
        //             "userId", loginUser.getId()
        //         ));
        // }
        return null;
    }

} 