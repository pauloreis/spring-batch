package br.com.springbatch.motor.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Step2Config {
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step step2(Tasklet tasklet2){
        return stepBuilderFactory
                .get("step2")
                .tasklet(tasklet2)
                .build();
    }
}
