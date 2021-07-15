package cn.wegostack.sundial.common.exception;

/**
 * @author zhengjianglong
 * @since 2021-07-11
 */
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
