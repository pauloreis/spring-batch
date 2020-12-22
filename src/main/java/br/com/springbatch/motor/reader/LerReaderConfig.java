package br.com.springbatch.motor.reader;

import br.com.springbatch.motor.dominio.AuditorLogs;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Configuration
public class LerReaderConfig {

    @Bean
    public JdbcCursorItemReader<AuditorLogs> lerReader(
            @Qualifier("springDataSource") DataSource dataSource
    ) {
        return new JdbcCursorItemReaderBuilder<AuditorLogs>()
                .name("lerReader")
                .dataSource(dataSource)
                .sql("select * from auditor_logs where dtprocessado is null and status = 'processando' limit 1")
                .rowMapper(rowMapper())
                .build();
    }

    private RowMapper<AuditorLogs> rowMapper() {
        return new RowMapper<AuditorLogs>() {
            @Override
            public AuditorLogs mapRow(ResultSet rs, int i) throws SQLException {
                AuditorLogs auditorLogs = new AuditorLogs();
                auditorLogs.setId(rs.getInt("id"));
                auditorLogs.setQtdRaspadinhas(rs.getInt("qtdRaspadinhas"));
                auditorLogs.setIdTransacao(rs.getInt("idTransacao"));
                auditorLogs.setIdCampanha(rs.getInt("idCampanha"));
                auditorLogs.setIdUsuario(rs.getInt("idUsuario"));
                return auditorLogs;
            }
        };
    }
}
