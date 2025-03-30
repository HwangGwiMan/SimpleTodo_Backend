package Backend.user.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import Backend.common.util.DateUtil;
import Backend.role.entity.UserRoleEntity;
import Backend.role.repository.UserRoleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import Backend.config.ResponseCode;
import Backend.jwt.JwtTokenProvider;
import Backend.role.service.RoleService;
import Backend.user.dto.UserRequestDto;
import Backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import Backend.user.conv.UserConv;
import Backend.user.entity.UserEntity;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final RoleService roleService;

    private final UserConv userConv;
    private final UserRepository userRepository;

    private final UserRoleRepository userRoleRepository;

    private final PasswordEncoder passwordEncoder;

    /**
     * 회원가입 메서드
     * 
     * @param user 회원가입 정보
     * @return 회원가입 결과
     */
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
                userRoleEntity.setUserId(userEntity.getId());
                userRoleEntity.setRoleId(role.getId());
                userRoleRepository.save(userRoleEntity);
            });

            return ResponseEntity.status(200)
                .body(Map.of("code", ResponseCode.SUCCESS.getCode(), "message", ResponseCode.SUCCESS.getMessage()));
        }
    }

    /**
     * 로그인 메서드
     * 
     * @param user 로그인 정보
     * @return 로그인 결과
     */
    @Transactional(readOnly = true)
    public ResponseEntity<Map<String, Object>> login(UserRequestDto user) {        
         UserEntity loginUser = userRepository.findByUsername(user.getUsername());
         if (loginUser == null) {
             return ResponseEntity.status(400)
                 .body(Map.of("code", ResponseCode.USER_NOT_FOUND.getCode(), "message", ResponseCode.USER_NOT_FOUND.getMessage()));
         }

         if (!passwordEncoder.matches(user.getPassword(), loginUser.getPassword())) {
             return ResponseEntity.status(400)
                 .body(Map.of("code", ResponseCode.USER_NOT_FOUND.getCode(), "message", ResponseCode.USER_NOT_FOUND.getMessage()));
         }
         else {
             Map<String, Object> claims = new HashMap<>();
             claims.put("userName", loginUser.getUsername());
             claims.put("userId", loginUser.getId());

             claims.put("roleNames", roleService.getRolesByUserId(loginUser.getId()));

             String accessToken = JwtTokenProvider.generateToken(claims, 30);
             String refreshToken = JwtTokenProvider.generateToken(claims, 60);
             return ResponseEntity.status(200)
                 .body(Map.of(
                     "code", ResponseCode.SUCCESS.getCode(),
                     "message", ResponseCode.SUCCESS.getMessage(),
                     "accessToken", accessToken,
                     "refreshToken", refreshToken,
                     "userName", loginUser.getUsername(),
                     "userId", loginUser.getId()
                 ));
         }
    }
}