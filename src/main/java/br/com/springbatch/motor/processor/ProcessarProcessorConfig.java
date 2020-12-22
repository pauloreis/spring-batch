package br.com.springbatch.motor.processor;

import br.com.springbatch.motor.dominio.AuditorLogs;
import br.com.springbatch.motor.dominio.UserOdds;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProcessarProcessorConfig implements ItemProcessor<AuditorLogs, List<UserOdds>> {

    @Override
    public List<UserOdds> process(AuditorLogs item) throws Exception {
        List<UserOdds> userOddsList = new ArrayList<>();

        //Todo
        // Ir na base para pegar a premiação e inserir no objeto UserOdds aqui
        // para no writer já salvar o registro com a premiação.

        for(int i=0; i<item.getQtdRaspadinhas(); i++) {
            UserOdds userOdds = new UserOdds();
            userOdds.setIdUsuario(item.getIdUsuario());
            userOdds.setIdCamapanha(item.getIdCampanha());
            userOdds.setIdTransacao(item.getIdTransacao());
            userOdds.setIdAuditorLogs(item.getId());
            userOddsList.add(userOdds);
        }

        return userOddsList;
    }
}
