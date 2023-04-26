package cn.crabapples.file.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

/**
 * TODO 任务调度器
 *
 * @author Mr.He
 * @description 2023/4/26 17:32
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name mshe
 */
//@Component
//@Configuration
public class DelFileTask implements SchedulingConfigurer {
    private final Logger logger = LoggerFactory.getLogger(DelFileTask.class);
    private final Map<String, ScheduledFuture<?>> SCHEDULED_FUTURE_MAP = new ConcurrentHashMap<>();
    private static final ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();

    public DelFileTask() {
        taskScheduler.initialize();
    }

    @Bean
    public DelFileTask getDelFileTask() {
        return new DelFileTask();
    }
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
//        taskRegistrar.addTriggerTask(new TriggerTask(run(), trigger()));
    }

    private void doDelete(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
        logger.info("任务执行，文件：[{}], 当前时间：[{}]", fileName, LocalDateTime.now());
    }

    /**
     * 添加延时任务
     *
     * @param taskName  任务名称
     * @param delayTime 延迟时间（单位：秒）
     */
    public void addTask(String taskName, long delayTime) {
        ScheduledFuture<?> future = taskScheduler.schedule(() -> doDelete(taskName),
                new Date(System.currentTimeMillis() + delayTime * 1000));
        SCHEDULED_FUTURE_MAP.put(taskName, future);
        logger.info("添加延时任务，文件：[{}], 执行时间：[{}]", taskName,
                LocalDateTime.ofInstant(new Date(System.currentTimeMillis() +
                        delayTime).toInstant(), ZoneId.systemDefault()));
    }

}

