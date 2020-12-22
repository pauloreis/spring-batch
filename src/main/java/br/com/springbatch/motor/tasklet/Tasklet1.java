package br.com.springbatch.motor.tasklet;

import br.com.springbatch.motor.config.DataSourceConfig;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class Tasklet1 implements Tasklet {
    @Autowired
    DataSourceConfig dataSource;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        System.out.println("Step 1: Verifica se tem registro para ser processado");

        int count = 0;

        JdbcTemplate jdbc = new JdbcTemplate(dataSource.springDataSource());

        String sql = "select count(*) from auditor_logs where dtprocessado is null and status is null limit 1";
        count = jdbc.queryForObject(sql, Integer.class);

        return RepeatStatus.continueIf(count > 0);
    }
}
