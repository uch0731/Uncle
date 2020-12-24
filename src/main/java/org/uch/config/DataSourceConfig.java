package org.uch.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.concurrent.Executor;


@Slf4j
@Lazy
@Configuration
@MapperScan(basePackages = "org.uch.mapper", annotationClass = Mapper.class, sqlSessionFactoryRef = "sqlSessionFactory")
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public HikariConfig hikariConfigMaster() {
        return new HikariConfig();
    }

    @Bean
    public DataSource dataSource(HikariConfig hikariConfigMaster) {
        return new HikariDataSource(hikariConfigMaster);
    }


    /**
     * https://stackoverflow.com/questions/37310550/spring-transaction-management-not-working-with-spring-boot-mybatis
     * As Kazuki correctly mentioned, you need to explicitly declare that rollbacks need to happen for checked exceptions using the @Transactional(rollbackFor = Exception.class) annotation.
     " Transaction boundaries are only created when properly annotated methods are called through a Spring proxy. This means that you need to call your annotated method directly through an @Autowired bean or the transaction will never start." (reference to this source below)
     */
    @Primary
    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) throws IOException {
        DataSourceTransactionManager dataSourceTXManager = new DataSourceTransactionManager();
        dataSourceTXManager.setDataSource(dataSource);
        return dataSourceTXManager;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlFactory = new SqlSessionFactoryBean();
        sqlFactory.setDataSource(dataSource);
        sqlFactory.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        sqlFactory.setTypeAliasesPackage("org.uch.entity");
        sqlFactory.setVfs(SpringBootVFS.class);
        return sqlFactory.getObject();
    }

    @Bean
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(4);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("AsyncExecutor-");
        executor.initialize();
        return executor;
    }
}