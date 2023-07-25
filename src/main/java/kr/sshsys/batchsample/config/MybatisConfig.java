package kr.sshsys.batchsample.config;

import org.apache.ibatis.session.SqlSessionFactory;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 *
 * MybatisConfig
 * Mybatis 설정을 담당하는 클래스
 *
 * @version 1.0.0
 * @since 2023-07-25
 * @auther sshsys
 *
 */
@Configuration
public class MybatisConfig {

    /** ApplicationContext */
    private final ApplicationContext applicationContext;

    /** DataSource */
    private final DataSource dataSource;

    /**
     * 생성자
     * @param applicationContext
     * @param dataSource
     */
    public MybatisConfig(ApplicationContext applicationContext, DataSource dataSource) {
        this.applicationContext = applicationContext;
        this.dataSource = dataSource;
    }

    /**
     * SqlSessionFactory 생성 메소드
     * @return SqlSessionFactory
     * @throws Exception
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        
        /** SqlSessionFactoryBean 생성 */
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        
        /** Mybatis Mapper Location 설정 */
        factoryBean.setMapperLocations(
                applicationContext.getResources("classpath:/mapper/**/*.xml"));

        /** DataSource 설정 */
        factoryBean.setDataSource(dataSource);

        /** Transaction 설정 */
        factoryBean.setTransactionFactory(new SpringManagedTransactionFactory());

        return factoryBean.getObject();
    }

    /**
     * SqlSessionTemplate 생성 메소드
     * @return SqlSessionTemplate
     * @throws Exception
     */
    @Bean
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }

}
