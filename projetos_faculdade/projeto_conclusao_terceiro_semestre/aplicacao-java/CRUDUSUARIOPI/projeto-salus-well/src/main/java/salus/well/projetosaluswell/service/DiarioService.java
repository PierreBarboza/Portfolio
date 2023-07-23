package salus.well.projetosaluswell.service;

import org.springframework.stereotype.Service;
import salus.well.projetosaluswell.domain.alimento.Alimento;
import salus.well.projetosaluswell.domain.diario.DiarioRepository;

import java.util.List;

@Service
public class DiarioService {

    private final DiarioRepository diarioRepository;

    public DiarioService(DiarioRepository diarioRepository) {
        this.diarioRepository = diarioRepository;
    }


 /*   public List<Alimento> findAlimentosByIdsInDiarios(List<Long> ids){
        return diarioRepository.findAlimentosByNomeInDiarios(ids);
    }*/
}
