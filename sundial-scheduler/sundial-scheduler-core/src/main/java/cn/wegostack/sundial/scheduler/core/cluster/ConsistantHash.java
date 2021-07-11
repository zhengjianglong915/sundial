package cn.wegostack.sundial.scheduler.core.cluster;

import cn.wegostack.sundial.common.utils.HashCodeUtil;
import org.apache.commons.collections.CollectionUtils;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author zhengjianglong
 * @since 2021-07-11
 */
public class ConsistantHash {
    private static Set<String> serverSet = new HashSet<>();
    private static HashItem[] items;
    private static final int BUCKET = 8;
    private static AtomicBoolean isInited = new AtomicBoolean(false);

    private static class HashItem implements Comparable<Object> {
        private int hashCode;
        private String server;

        public HashItem(int hashCode, String server) {
            this.hashCode = hashCode;
            this.server = server;
        }

        @Override
        public int compareTo(Object o) {
            int hashcode = 0;
            if (o instanceof HashItem) {
                hashcode = ((HashItem) o).hashCode;
            } else if (o instanceof Integer) {
                hashcode = ((Integer) o).intValue();
            }

            if (this.hashCode < hashcode) {
                return -1;
            } else if (this.hashCode > hashcode) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    public static void load(Set<String> serverSet) throws UnsupportedEncodingException {
        synchronized (ConsistantHash.class) {
            ConsistantHash.serverSet = serverSet;
            int idx = 0;
            items = new HashItem[serverSet.size() * BUCKET];
            for (String server : serverSet) {
                for (int count = 0; count < BUCKET; count++) {
                    int hashCode = HashCodeUtil.hashCode(server + "-" + count);
                    items[idx++] = new HashItem(hashCode, server);
                }
            }
            Arrays.sort(items);
            isInited.set(true);
        }
    }

    public static String getServer(int hashCode) {
        synchronized (ConsistantHash.class) {
            if (!isInited.get() || CollectionUtils.isEmpty(serverSet)) {
                return null;
            }

            int idx = Arrays.binarySearch(items, hashCode);
            if (idx < 0) {
                idx = Math.abs(idx) - 1;
            }

            if (idx >= items.length) {
                idx = items.length - 1;
            }
            return items[idx].server;
        }
    }
}
