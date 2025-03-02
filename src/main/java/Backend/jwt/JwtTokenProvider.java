package Backend.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.function.Function;

@Log4j2
@Component
public class JwtTokenProvider {

    private static final Key key;
    private static final String TOKEN_TYPE = "JWT";
    private static final String TOKEN_ISSUER = "TodoApp";
    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    static {
        String secretKey;
        try {
            Properties properties = new Properties();
            String activeProfile = System.getProperty("spring.profiles.active", "default");
            String propertiesPath = "application" + 
                (activeProfile.equals("default") ? "" : "-" + activeProfile) + 
                ".properties";
            
            InputStream inputStream = JwtTokenProvider.class.getClassLoader()
                    .getResourceAsStream(propertiesPath);
            
            if (inputStream != null) {
                properties.load(inputStream);
                secretKey = properties.getProperty("jwt.secret");
            } else {
                // 기본 시크릿 키 설정
                secretKey = "c3ByaW5nLWJvb3Qtc2VjdXJpdHktand0LXR1dG9yaWFsLWppd29vbi1zcHJpbmctYm9vdC1zZWN1cml0eS1qd3QtdHV0b3JpYWwK";
            }
            
            key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
            
        } catch (IOException e) {
            throw new RuntimeException("JWT 키 초기화 실패", e);
        }
    }

    // 토큰의 유효성 검증을 수행
    public static void validateToken(String token) throws JwtException {
        try {
            Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
        } catch (MalformedJwtException e) {
            throw new JwtException("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            throw new JwtException("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            throw new JwtException("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            throw new JwtException("JWT 토큰이 잘못되었습니다.");
        }
    }

    // 사용자 이름 및 권한으로 인증 토큰 생성
    public static String generateToken(Map<String, Object> claims, int expirationMinutes) {
        Date now = new Date();
        Date expiryDate = Date.from(ZonedDateTime.now()
                .plusMinutes(expirationMinutes)
                .toInstant());

        return Jwts.builder()
                .setHeaderParam("typ", TOKEN_TYPE)
                .setClaims(claims)
                .setIssuer(TOKEN_ISSUER)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SIGNATURE_ALGORITHM)
                .compact();
    }

    // 토큰에서 특정 클레임 추출
    public static <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // 토큰에서 모든 클레임 추출
    public static Claims extractAllClaims(String token) throws JwtException {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
