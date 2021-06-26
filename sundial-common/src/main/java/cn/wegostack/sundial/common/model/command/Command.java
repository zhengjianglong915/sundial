package cn.wegostack.sundial.common.model.command;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhengjianglong
 * @since 2021-06-26
 */
@Data
public class Command implements Serializable {
    private String commandId;
}
