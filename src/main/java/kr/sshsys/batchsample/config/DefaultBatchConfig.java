package kr.sshsys.batchsample.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Date;

/**
 *
 * DefaultBatchConfig
 * BatchConfig의 기본 설정을 담당하는 클래스
 *
 * @version 1.0.0
 * @since 2023-07-25
 * @auther sshsys
 *
 */
@Configuration
public class DefaultBatchConfig extends DefaultBatchConfigurer {

    /** DataSource */
    private final DataSource dataSource;

    /**
     * 생성자
     * @param dataSource
     */
    public DefaultBatchConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Job 실행 메소드
     * @param job
     */
    protected void runJob(Job job) {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addDate("date", new Date())
                    .addLong("JobId", System.currentTimeMillis())
                    .toJobParameters();
            createJobLauncher().run(job, jobParameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
    /**
     * JobLauncher 생성 메소드
     * @return JobLauncher
     * @throws Exception
     */
    @Override
    protected JobLauncher createJobLauncher() throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(createJobRepository());
        jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
        return jobLauncher;
    }
	
    /**
     * JobRepository 생성 메소드
     * cubrid 사용을 위한 databaseType 설정
     * @return JobRepository
     * @throws Exception
     */
    @Override
    protected JobRepository createJobRepository() throws Exception {
        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
        factory.setDataSource(dataSource);
        factory.setDatabaseType("oracle");
        factory.setTransactionManager(new DataSourceTransactionManager(dataSource));
        return factory.getObject();
    }

}
