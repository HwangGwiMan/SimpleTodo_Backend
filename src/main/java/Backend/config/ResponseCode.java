package Backend.config;

public enum ResponseCode {
    SUCCESS("200", "성공"),
    BAD_REQUEST("400", "잘못된 요청"),
    INTERNAL_SERVER_ERROR("500", "서버 오류"),
    FORBIDDEN("403", "접근 거부"),

    USER_EXISTS("400", "이미 존재하는 아이디입니다."),
    USER_NOT_FOUND("404", "존재하지 않는 아이디입니다."),
    USER_UNAUTHORIZED("401", "인증 실패"),
    USER_METHOD_NOT_ALLOWED("405", "허용되지 않는 메서드"),
    USER_NOT_ACCEPTABLE("406", "허용되지 않는 요청");

    private final String code;
    private final String message;

    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
