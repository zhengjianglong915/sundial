package cn.wegostack.sundial.scheduler.dal.convector;

import cn.wegostack.sundial.common.enums.ServerStatus;
import cn.wegostack.sundial.common.model.Server;
import cn.wegostack.sundial.scheduler.dal.entity.ServerDO;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class ServerConvector {

    public static Server convect(ServerDO source) {
        if (null == source) {
            return null;
        }

        Server target = new cn.wegostack.sundial.common.model.Server();
        BeanUtils.copyProperties(source, target, new String[]{"status"});
        target.setStatus(ServerStatus.of(source.getStatus()));
        return target;
    }

    public static ServerDO convect(Server source) {
        if (null == source) {
            return null;
        }

        ServerDO target = new ServerDO();
        BeanUtils.copyProperties(source, target, new String[]{"status"});
        if (null != source.getStatus()) {
            target.setStatus(source.getStatus().name());
        }
        return target;
    }

    public static List<Server> convect(List<ServerDO> sourceList) {
        List<Server> targetList = Lists.newArrayList();
        if (CollectionUtils.isEmpty(sourceList)) {
            return targetList;
        }

        for (ServerDO serverDO : sourceList) {
            Server server = convect(serverDO);
            targetList.add(server);
        }
        return targetList;
    }
}
