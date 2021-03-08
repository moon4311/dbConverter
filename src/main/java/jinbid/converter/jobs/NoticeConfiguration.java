package jinbid.converter.jobs;


import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jinbid.converter.jobs.listener.BaseChunkListener;
import jinbid.converter.jobs.listener.BaseJobListener;
import jinbid.converter.jobs.listener.BaseStepListener;
import jinbid.converter.jobs.processor.BaseItemProcessor;
import jinbid.converter.jobs.writer.BaseItemWriter;


@Configuration
@EnableBatchProcessing
public class NoticeConfiguration {

	private final static int MaxCount = 100;
	
	@Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    
    @Resource(name="db1SqlSessionFactory")
    private SqlSessionFactory db1SqlSessionFactory;

    @Bean("test_job")
    public Job job() {
        return  jobBuilderFactory.get("test_Job")
                .incrementer(new RunIdIncrementer())
                .listener(baseJobListener())
                .start(step1())
                .build();
    }
   
   
    @Bean("step1")
    public Step step1() {
       return stepBuilderFactory.get("step1")
                .<Map, Map>chunk(MaxCount)
                .reader(getItemReader())
                .processor(baseItemProcessor())
                .writer(baseItemWriter())
                .build();
    }

    

    private MyBatisPagingItemReader<Map> getItemReader(){
    	MyBatisPagingItemReader<Map> itemReader = new MyBatisPagingItemReader<>();
        itemReader.setSqlSessionFactory(db1SqlSessionFactory);
        
        try {
//        Map<String,Object> params = new HashMap<>();
//        	itemReader.setParameterValues(params);
        	itemReader.setQueryId("mapper.from.oracle-mapper.selectList");
			itemReader.afterPropertiesSet();
			itemReader.setPageSize(MaxCount);	//리스트크기
		} catch (Exception e) {
			e.printStackTrace();
		}
        return itemReader;
    }
    

	@Bean("baseItemProcessor")
	public ItemProcessor<Map, Map> baseItemProcessor() {
		return  new BaseItemProcessor();
	}

	
	@Bean("baseItemWriter")
	public ItemWriter<Map> baseItemWriter() {
		return new BaseItemWriter();
	}

	@Bean("baseStepListener")
	public StepExecutionListener baseStepListener(){
		return new BaseStepListener();
	}

	@Bean("baseChunkListener")
	public ChunkListener baseChunkListener(){
		return new BaseChunkListener();
	}

	@Bean("baseJobListener")
	public BaseJobListener baseJobListener(){
		return new BaseJobListener();
	}
	
}