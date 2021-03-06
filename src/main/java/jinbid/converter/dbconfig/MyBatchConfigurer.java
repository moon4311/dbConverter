package jinbid.converter.dbconfig;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


@Configuration
public class MyBatchConfigurer extends DefaultBatchConfigurer{

	@Bean(name = "db1DataSource")
    @ConfigurationProperties(prefix = "spring.db1.datasource")
    public DataSource db1DataSource() {
    	return DataSourceBuilder.create().build();
    }

	@Primary
	@Bean(name = "db2DataSource")
	@ConfigurationProperties("spring.db2.datasource")
	public DataSource secondDataSource() {
		return DataSourceBuilder.create().build();
	}
	
    @Value("${spring.db1.datasource.resource}")
	public String db1_resource;
	
    @Value("${spring.db2.datasource.resource}")
	public String db2_resource;
	  
	
    @Bean(name = "db1SqlSessionFactory")
    public SqlSessionFactory db1SqlSessionFactory(@Qualifier("db1DataSource") DataSource db1DataSource, ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(db1DataSource);
        sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:jinbid/mybatis-config.xml"));
//        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:jinbid/converter/comn/mapper/*-mapper.xml"));
//        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:jinbid/converter3/comn/mapper/*-mapper.xml"));
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources(db1_resource));
        return sqlSessionFactoryBean.getObject();
    }
	
    @Primary
    @Bean(name = "db2SqlSessionFactory")
    public SqlSessionFactory db2SqlSessionFactory(@Qualifier("db2DataSource") DataSource db2DataSource, ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(db2DataSource);
        sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:jinbid/mybatis-config.xml"));
//        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:jinbid/converter/comn/mapper106/*-mapper.xml"));
//        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:jinbid/converter3/comn/mapper106/*-mapper.xml"));
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources(db2_resource));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "db1SqlSessionTemplate")
    public SqlSessionTemplate db1SqlSessionTemplate(SqlSessionFactory db1SqlSessionFactory){
        return new SqlSessionTemplate(db1SqlSessionFactory);
    }
    
    @Primary
    @Bean(name = "db2SqlSessionTemplate")
    public SqlSessionTemplate db2SqlSessionTemplate(SqlSessionFactory db2SqlSessionFactory){
        return new SqlSessionTemplate(db2SqlSessionFactory);
    }
}
