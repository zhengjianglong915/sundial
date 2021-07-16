package cn.wegostack.sundial.common.model;

import cn.wegostack.sundial.common.enums.ServerStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Server {
    private String ip;

    private String hostname;

    private ServerStatus status;

    private Date heartbeat;

    private String cluster;
}
