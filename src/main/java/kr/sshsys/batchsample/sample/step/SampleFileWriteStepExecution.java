package kr.sshsys.batchsample.sample.step;

import kr.sshsys.batchsample.sample.entity.Sample;
import kr.sshsys.batchsample.sample.util.SampleFileWriteItemReader;
import kr.sshsys.batchsample.sample.util.SampleFileWriteItemWriter;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * SampleFileWriteStepExecution
 * Step 설정을 담당하는 클래스
 *
 * @version 1.0.0
 * @since 2023-07-25
 * @auther sshsys
 *
 */
@Configuration
public class SampleFileWriteStepExecution {

    /** StepBuilderFactory */
    private final StepBuilderFactory stepBuilderFactory;

    /**
     * 생성자
     * @param stepBuilderFactory
     */
    public SampleFileWriteStepExecution(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    /**
     * sampleFileWriteStep
     * Step 설정을 담당하는 메소드
     * @param itemReader
     * @param itemWriter
     * @return Step
     */
    @Bean
    @JobScope
    public Step sampleFileWriteStep(SampleFileWriteItemReader<Sample> itemReader, SampleFileWriteItemWriter<Sample> itemWriter) {
        return stepBuilderFactory.get("SampleReaderStep")
                .<Sample, Sample>chunk(10)
                .reader(itemReader)
                .writer(itemWriter)
                .build();
    }
}
