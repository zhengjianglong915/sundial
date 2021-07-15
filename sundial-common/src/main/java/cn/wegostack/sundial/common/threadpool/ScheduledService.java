package cn.wegostack.sundial.common.threadpool;

import cn.wegostack.sundial.common.utils.LogUtils;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhengjianglong
 * @since 2021-06-27
 */
public class ScheduledService {
    private String threadName;
    private Runnable runnable;
    private long initialDelay;
    private long period;
    private TimeUnit unit;
    private ScheduledFuture future;

    private volatile boolean started;

    private volatile ScheduledExecutorService scheduledExecutorService;

    public ScheduledService(String threadName, Runnable runnable, long initialDelay,
                            long period, TimeUnit unit) {
        this.threadName = threadName;
        this.runnable = runnable;
        this.initialDelay = initialDelay;
        this.period = period;
        this.unit = unit;
    }

    public synchronized ScheduledService start() {
        if (started) {
            return this;
        }

        if (scheduledExecutorService == null) {
            scheduledExecutorService = new ScheduledThreadPoolExecutor(1,
                    new NamedThreadFactory(threadName));
        }

        scheduledExecutorService.scheduleWithFixedDelay(runnable, initialDelay, period, unit);
        if (future != null) {
            this.future = future;
            started = true;
        } else {
            started = false;
        }
        return this;
    }

    public synchronized void stop() {
        if (!started) {
            return;
        }

        try {
            if (future != null) {
                future.cancel(true);
                future = null;
            }

            if (scheduledExecutorService != null) {
                scheduledExecutorService.shutdown();
                scheduledExecutorService = null;
            }
        } catch (Throwable t) {
            LogUtils.error("[ScheduledService] ", t);
        } finally {
            started = false;
        }
    }
}
