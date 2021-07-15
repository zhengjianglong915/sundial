package cn.wegostack.sundial.scheduler.core.cluster;

import cn.wegostack.sundial.common.constants.CommonConstants;
import cn.wegostack.sundial.common.utils.LocalServer;
import com.google.common.collect.Sets;

import java.util.Set;

/**
 * @author zhengjianglong
 * @since 2021-07-11
 */
public class SlotManager {
    /**
     * Local server slots,
     */
    private static byte[] slots = new byte[CommonConstants.SLOT_COUNT];

    /**
     * The set of slots which is belongs to local server
     */
    private static Set<Integer> slotSet = Sets.newHashSet();

    /**
     * refresh slot
     */
    public static void refresh() {
        synchronized (SlotManager.class) {
            slotSet = Sets.newHashSet();
            for (int i = 0; i < slots.length; i++) {
                String server = ConsistantHash.getServer(i);
                if (LocalServer.getIp().equals(server)) {
                    slots[i] = 1;
                    slotSet.add(i);
                }
            }
        }
    }

    /**
     * If special slot belongs to the local server
     *
     * @param slot
     * @return
     */
    public static boolean contains(int slot) {
        return slots[slot] == 1;
    }

    /**
     * Get all slot belongs to the local server
     *
     * @return
     */
    public static Set<Integer> getAllSlots() {
        synchronized (SlotManager.class) {
            return slotSet;
        }
    }
}
