package br.com.springbatch.motor.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.JobListenerFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class JobConfig {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job job(Step step2, Step step3, Step step4, Step step5){
        return jobBuilderFactory
                .get("job")
                .start(step2)
                .next(step3)
                .next(step4)
                .next(step5)
                .incrementer(new RunIdIncrementer())
                .listener(JobListenerFactoryBean.getListener(new JobListener()))
                .build();
    }
}
