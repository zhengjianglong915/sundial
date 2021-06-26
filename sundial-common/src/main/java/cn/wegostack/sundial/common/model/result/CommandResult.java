package cn.wegostack.sundial.common.model.result;

import java.io.Serializable;

/**
 * @author zhengjianglong
 * @since 2021-06-26
 */
public class CommandResult implements Serializable {
    private String status;

    private String resultCode;

    private String resultMsg;
}
