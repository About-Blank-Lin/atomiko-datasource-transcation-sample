package com.example.demo.configuration;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.example.demo.Bean.Db1Database;
import com.example.demo.Bean.Db2Database;
import com.example.demo.Bean.Db3Database;
import com.example.demo.mapper.Db2Mapper;
import com.example.demo.mapper.Db3Mapper;
import com.example.demo.mapper.Db1Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.transaction.SystemException;
import java.util.Properties;

@Configuration
public class DataSourceConfig {

    @Autowired
    private Db1Database db1Database;

    @Autowired
    private Db2Database db2Database;

    @Autowired
    private Db3Database db3Database;

    @SuppressWarnings("Duplicates")
    @Bean
    @Primary
    @Qualifier("db1")
    public AtomikosDataSourceBean db1AtomikosDataSourceBean(){
        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();

        Properties properties = new Properties();
        properties.put("URL", db1Database.getUrl());
        properties.put("user", db1Database.getUser());
        properties.put("password", db1Database.getPassword());

        atomikosDataSourceBean.setXaProperties(properties);
        atomikosDataSourceBean.setUniqueResourceName(db1Database.getUniqueName());
        atomikosDataSourceBean.setXaDataSourceClassName("oracle.jdbc.xa.client.OracleXADataSource");

        atomikosDataSourceBean.setMinPoolSize(5);
        atomikosDataSourceBean.setMaxPoolSize(20);
        atomikosDataSourceBean.setReapTimeout(30000);

        return atomikosDataSourceBean;
    }

    @Autowired
    @Bean
    @Qualifier("db1sqlSessionFactory")
    public SqlSessionFactoryBean db1sqlSessionFactory(@Qualifier("db1")AtomikosDataSourceBean atomikosDataSourceBean) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

        sqlSessionFactoryBean.setDataSource(atomikosDataSourceBean);

        sqlSessionFactoryBean.setTransactionFactory(new SpringManagedTransactionFactory());

        return sqlSessionFactoryBean;
    }

    @Bean
    MapperFactoryBean<Db1Mapper> db1MapperFactoryBean(@Qualifier("db1sqlSessionFactory")SqlSessionFactory db1SqlSessionFactory){

        MapperFactoryBean<Db1Mapper> mapperFactoryBean = new MapperFactoryBean<>();
        mapperFactoryBean.setSqlSessionFactory(db1SqlSessionFactory);
        mapperFactoryBean.setMapperInterface(Db1Mapper.class);
        return mapperFactoryBean;
    }

/*************************************************************************************************************************************/

    @SuppressWarnings("Duplicates")
    @Bean
    @Qualifier("db2")
    public AtomikosDataSourceBean db2AtomikosDataSourceBean2(){

        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();

        Properties properties = new Properties();
        properties.put("URL", db2Database.getUrl());
        properties.put("user", db2Database.getUser());
        properties.put("password", db2Database.getPassword());

        atomikosDataSourceBean.setXaProperties(properties);
        atomikosDataSourceBean.setUniqueResourceName(db2Database.getUniqueName());
        atomikosDataSourceBean.setXaDataSourceClassName("oracle.jdbc.xa.client.OracleXADataSource");

        atomikosDataSourceBean.setMinPoolSize(5);
        atomikosDataSourceBean.setMaxPoolSize(20);
        atomikosDataSourceBean.setReapTimeout(30000);

        return atomikosDataSourceBean;
    }

    @Autowired
    @Bean
    @Qualifier("db2SqlSessionFactory")
    public SqlSessionFactoryBean db2SqlSessionFactory(@Qualifier("db2")AtomikosDataSourceBean atomikosDataSourceBean) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

        sqlSessionFactoryBean.setDataSource(atomikosDataSourceBean);

        return sqlSessionFactoryBean;
    }

    @Bean
    MapperFactoryBean<Db2Mapper> db2MapperFactoryBean(@Qualifier("db2SqlSessionFactory")SqlSessionFactory db2SqlSessionFactory){

        MapperFactoryBean<Db2Mapper> mapperFactoryBean = new MapperFactoryBean<Db2Mapper>();
        mapperFactoryBean.setSqlSessionFactory(db2SqlSessionFactory);
        mapperFactoryBean.setMapperInterface(Db2Mapper.class);
        return mapperFactoryBean;
    }


/*************************************************************************************************************************************/

    @SuppressWarnings("Duplicates")
    @Bean
    @Qualifier("db3")
    public AtomikosDataSourceBean db3AtomikosDataSourceBean3(){

        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();

        Properties properties = new Properties();
        properties.put("URL", db3Database.getUrl());
        properties.put("user", db3Database.getUser());
        properties.put("password", db3Database.getPassword());

        atomikosDataSourceBean.setXaProperties(properties);
        atomikosDataSourceBean.setUniqueResourceName(db3Database.getUniqueName());
        atomikosDataSourceBean.setXaDataSourceClassName("oracle.jdbc.xa.client.OracleXADataSource");

        atomikosDataSourceBean.setMinPoolSize(5);
        atomikosDataSourceBean.setMaxPoolSize(20);
        atomikosDataSourceBean.setReapTimeout(30000);

        return atomikosDataSourceBean;
    }

    @Autowired
    @Bean
    @Qualifier("db3SqlSessionFactory")
    public SqlSessionFactoryBean db3SqlSessionFactory(@Qualifier("db3")AtomikosDataSourceBean atomikosDataSourceBean) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

        sqlSessionFactoryBean.setDataSource(atomikosDataSourceBean);

        sqlSessionFactoryBean.setTransactionFactory(new SpringManagedTransactionFactory());

        return sqlSessionFactoryBean;
    }

    @Bean
    MapperFactoryBean<Db3Mapper> db3MapperFactoryBean(@Qualifier("db3SqlSessionFactory")SqlSessionFactory db3SqlSessionFactory){

        MapperFactoryBean<Db3Mapper> mapperFactoryBean = new MapperFactoryBean<>();
        mapperFactoryBean.setSqlSessionFactory(db3SqlSessionFactory);
        mapperFactoryBean.setMapperInterface(Db3Mapper.class);

        return mapperFactoryBean;
    }

    @Bean(destroyMethod = "close", initMethod = "init")
    @Qualifier("userTransactionManager")
    public UserTransactionManager userTransactionManager(){
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setForceShutdown(true);

        return userTransactionManager;
    }

    @Bean
    @Qualifier("userTransactionImp")
    UserTransactionImp userTransactionImp() throws SystemException {
        UserTransactionImp userTransactionImp = new UserTransactionImp();
        userTransactionImp.setTransactionTimeout(300);
        return userTransactionImp;
    }

    @Bean
    public JtaTransactionManager jtaTransactionManager(
            @Qualifier("userTransactionManager")UserTransactionManager userTransactionManager,
            @Qualifier("userTransactionImp")UserTransactionImp userTransactionImp
    ) throws SystemException {
        JtaTransactionManager jtaTransactionManager = new JtaTransactionManager();

        jtaTransactionManager.setTransactionManager(userTransactionManager);

        jtaTransactionManager.setUserTransaction(userTransactionImp);

        return jtaTransactionManager;
    }

}
