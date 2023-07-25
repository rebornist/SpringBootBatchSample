package kr.sshsys.batchsample.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

/**
 *
 * SchedulingConfig
 * Scheduling 설정을 담당하는 클래스
 *
 * @version 1.0.0
 * @since 2023-07-25
 * @auther sshsys
 *
 */
@Configuration
@EnableBatchProcessing
public class SchedulingConfig implements SchedulingConfigurer {

    /** DefaultBatchConfig */
    private final DefaultBatchConfig defaultBatchConfig;

    /** Job */
    private final Job samplePostJob;

    /**
     * 생성자
     * @param defaultBatchConfig
     * @param samplePostJob
     */
    public SchedulingConfig(DefaultBatchConfig defaultBatchConfig, Job samplePostJob) {
        this.defaultBatchConfig = defaultBatchConfig;
        this.samplePostJob = samplePostJob;
    }

    /**
     * Scheduling 설정 메소드
     * @param scheduledTaskRegistrar
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addTriggerTask(
                /** Spring Batch에서 Cubrid 사용을 위해 커스텀한 Job Launcher 실행 */
                () -> defaultBatchConfig.runJob(samplePostJob),
                /** 동작 시간 설정 */
                triggerContext -> {
                    CronTrigger trigger = new CronTrigger("0 */30 * * * ?");
                    return trigger.nextExecutionTime(triggerContext);
                }
        );
    }

}
