package kr.sshsys.batchsample.sample.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * SampleJobExecution
 * SampleJobExecution 설정을 담당하는 클래스
 *
 * @version 1.0.0
 * @since 2023-07-25
 * @auther sshsys
 *
 */
@Configuration
@EnableBatchProcessing
public class SampleJobExecution {

    /** JobBuilderFactory */
    public final JobBuilderFactory jobBuilderFactory;

    /**
     * 생성자
     * @param jobBuilderFactory
     */
    public SampleJobExecution(JobBuilderFactory jobBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
    }

    /**
     * samplePostJob
     * Job 설정을 담당하는 메소드
     * @param sampleReaderStep
     * @return Job
     */
    @Bean
    public Job samplePostJob(Step sampleReaderStep) {
        return jobBuilderFactory.get("SamplePostJob")
                .incrementer(new RunIdIncrementer())
                .start(sampleReaderStep)
                .build();
    }

}
