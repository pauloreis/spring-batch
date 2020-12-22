package br.com.springbatch.motor.writer;

import br.com.springbatch.motor.dominio.UserOdds;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Configuration
public class EscreverWriterConfig {

    public ItemWriter<List<UserOdds>> escreverWriter(
            @Qualifier("springDataSource") DataSource dataSource
    ){
        ListUnpackingItemWriter<UserOdds> listUnpackingItemWriter = new ListUnpackingItemWriter<UserOdds>();
        listUnpackingItemWriter.setDelegate(delegaWriter(dataSource));
        return listUnpackingItemWriter;
    }

    @Bean
    public JdbcBatchItemWriter<UserOdds> delegaWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<UserOdds>()
                .dataSource(dataSource)
                .sql("INSERT INTO user_odds(idusuario, idcampanha, idtransacao, idAuditorLogs) values (?,?,?,?)")
                .itemPreparedStatementSetter(itemPreparedStatementSetter())
                .build();
    }

    private ItemPreparedStatementSetter<UserOdds> itemPreparedStatementSetter() {
        return new ItemPreparedStatementSetter<UserOdds>() {
            @Override
            public void setValues(UserOdds item, PreparedStatement ps) throws SQLException {
                ps.setInt(1, item.getIdUsuario());
                ps.setInt(2, item.getIdCamapanha());
                ps.setInt(3, item.getIdTransacao());
                ps.setInt(4, item.getIdAuditorLogs());
            }
        };
    }
}
