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
public class Tasklet5 implements Tasklet {
    @Autowired
    DataSourceConfig dataSource;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        System.out.println("Step 4: Executado.");
        System.out.println("Step 5: setando o auditor_logs processado e data de processamento");

        JdbcTemplate jdbc = new JdbcTemplate(dataSource.springDataSource());

        String sql = "WITH auxAuditorLogs AS (\n" +
                "   SELECT id          \n" +
                "   FROM   auditor_logs al \n" +
                "   WHERE  status = 'processando'\n" +
                "   AND dtprocessado is null" +
                "   order by id \n" +
                "   LIMIT  1   \n" +
                "   )\n" +
                "UPDATE auditor_logs s\n" +
                "SET    status = 'processado', \n" +
                "dtprocessado = NOW()" +
                "FROM   auxauditorlogs \n" +
                "WHERE  s.id = auxAuditorLogs.id";

        jdbc.execute(sql);

        return RepeatStatus.FINISHED;
    }
}
