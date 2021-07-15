package cn.wegostack.sundial.scheduler.model.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhengjianglong
 * @since 2021-07-11
 */
@Data
public class Result<T> implements Serializable {
    public static final String OK = "OK";
    private boolean isSuccess;
    private String resultCode;
    private String resultMsg;
    private String detailMsg;
    private T data;

    public static Result success() {
        Result result = new Result();
        result.setSuccess(true);
        result.setResultCode(OK);
        return result;
    }

    public boolean isSuccess() {
        return isSuccess || OK.equals(resultCode);
    }
}
