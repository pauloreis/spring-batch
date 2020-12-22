package br.com.springbatch.motor.tasklet;

import br.com.springbatch.motor.config.DataSourceConfig;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class Tasklet2 implements Tasklet {
    @Autowired
    DataSourceConfig dataSource;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        System.out.println("Step 2: Limpando lixo de interrupções do job.");

        JdbcTemplate jdbc = new JdbcTemplate(dataSource.springDataSource());

        //Se o Job setar o status processando
        //Mas
        //Não é feito o chunck. (depende de setar o status processando)
        //Não muda o status para processado
        String sql1 = "WITH auxAuditorLogs AS (\n" +
                "   SELECT id          \n" +
                "   FROM   auditor_logs al \n" +
                "   WHERE  status ='processando' and dtprocessado is null\n" +
                "   order by id \n" +
                "   )\n" +
                "UPDATE auditor_logs s\n" +
                "SET    status = null \n" +
                "FROM   auxauditorlogs \n" +
                "WHERE  s.id = auxAuditorLogs.id";
        jdbc.execute(sql1);

        //Se o Job setar o status para processando
        //Rodar o chunck
        //Mas
        //Não deu update no status para processado com a data do processamento.
        String sql2 = "WITH auxAuditorLogs AS (\n" +
                "   select uo.idauditorlogs as id from \n" +
                "   user_odds uo, auditor_logs al \n" +
                "   where \n" +
                "   uo.idauditorlogs = al.id \n" +
                "   and al.status = 'processando' limit 1\n" +
                "   ) \n" +
                "update auditor_logs s \n" +
                "set status='processado', \n" +
                "dtprocessado = NOW()\n" +
                "from auxAuditorLogs\n" +
                "where s.id = auxauditorlogs.id";
        jdbc.execute(sql2);

        //Se o Job setar o status para processando
        //Mas
        // Parar no chuck, é feito o rollback.
        //Mas
        // O update do status para processado com a data do processamnto é feito.
        String sql3 = "WITH auxAuditorLogs AS (\n" +
                "   select al.id from auditor_logs al \n" +
                "   where\n" +
                "   al.dtprocessado is not null \n" +
                "   and dtprocessado is not null\n" +
                "   and al.id not in (select uo.idauditorlogs from user_odds uo)\n" +
                " )\n" +
                "update auditor_logs s\n" +
                "set status = null,\n" +
                "dtprocessado = null\n" +
                "from auxAuditorLogs\n" +
                "where s.id = auxauditorlogs.id";
        jdbc.execute(sql3);

        return RepeatStatus.FINISHED;
    }
}
