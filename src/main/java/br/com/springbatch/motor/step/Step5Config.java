package br.com.springbatch.motor.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Step5Config {
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step step5(Tasklet tasklet5){
        return stepBuilderFactory
                .get("step5")
                .tasklet(tasklet5)
                .build();
    }
}
