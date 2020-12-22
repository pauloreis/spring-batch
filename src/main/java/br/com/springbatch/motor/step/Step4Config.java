package br.com.springbatch.motor.step;

import br.com.springbatch.motor.dominio.AuditorLogs;
import br.com.springbatch.motor.dominio.UserOdds;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class Step4Config {
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step step4(
            ItemReader<AuditorLogs> lerReader,
            ItemProcessor<AuditorLogs, List<UserOdds>> processarProcessor,
            ItemWriter<List<UserOdds>> escreverWriter
    ){
        return stepBuilderFactory
                .get("step4")
                .<AuditorLogs, List<UserOdds>>chunk(1)
                .reader(lerReader)
                .processor(processarProcessor)
                .writer(escreverWriter)
                .startLimit(1)
                .build();
    }
}
