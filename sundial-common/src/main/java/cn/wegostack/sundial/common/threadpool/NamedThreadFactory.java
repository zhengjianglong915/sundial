package cn.wegostack.sundial.common.threadpool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhengjianglong
 * @since 2021-06-27
 */
public class NamedThreadFactory implements ThreadFactory {

    private static final AtomicInteger POOL_COUNT = new AtomicInteger();

    private final AtomicInteger threadCount = new AtomicInteger();

    private final ThreadGroup threadGroup;

    private final String namePrefix;

    private final boolean isDaemon;

    private final String firstPrefix = "Sundial-";

    public NamedThreadFactory(String secondPrefix) {
        this(secondPrefix, false);
    }

    public NamedThreadFactory(String secondPrefix, boolean isDaemon) {
        SecurityManager manager = System.getSecurityManager();
        threadGroup = (manager != null) ? manager.getThreadGroup()
                : Thread.currentThread().getThreadGroup();
        this.namePrefix = firstPrefix + secondPrefix + "-" + POOL_COUNT.getAndDecrement() + "-T";
        this.isDaemon = isDaemon;
    }

    @Override
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(threadGroup, runnable, namePrefix + threadCount.getAndDecrement(), 0);
        thread.setDaemon(isDaemon);
        if (thread.getPriority() != Thread.NORM_PRIORITY) {
            thread.setPriority(Thread.NORM_PRIORITY);
        }
        return thread;
    }
}
