package br.com.allanisaacdev.CadastroDeNinjas.Missoes;


import br.com.allanisaacdev.CadastroDeNinjas.Ninjas.NinjaRepository;
import br.com.allanisaacdev.CadastroDeNinjas.Ninjas.NinjaService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

@NoArgsConstructor
public class MissoesService {

    private MissoesRepository missoesRepository;

    private MissoesMapper missoesMapper;

    @Autowired
    public MissoesService(MissoesRepository missoesRepository, MissoesMapper missoesMapper) {
        this.missoesRepository = missoesRepository;
        this.missoesMapper = missoesMapper;
    }

    //Listar todas as missões
    public List<MissoesDTO> listarMissoes() {
        List<MissoesModel> missaoModel = missoesRepository.findAll();
        return missaoModel.stream().
                map(missoesMapper::map).
                collect(Collectors.toList());
    }

    //Mostrar Missar por ID
    public MissoesDTO listarMissoesPorId(Long id){
        Optional<MissoesModel> missaoPorId = missoesRepository.findById(id);
        return missaoPorId.map(missoesMapper::map).orElse(null);
    }

    //Criar Missão
    public MissoesDTO criarMissao(MissoesDTO missao){
        MissoesModel missaoModel = missoesMapper.map(missao);
        missaoModel = missoesRepository.save(missaoModel);
        MissoesDTO missaDTORetorno = missoesMapper.map(missaoModel);
        return missaDTORetorno;
    }

    //Deletar Missão
    public void deletarMissaoPorId(Long id){
        missoesRepository.deleteById(id);
    }

    //Atualizar Missao por ID
    public MissoesDTO atualizaMissao(Long id, MissoesDTO missaoAtualizada){
        Optional<MissoesModel> missaoExistente = missoesRepository.findById(id);

        if (missaoExistente.isPresent()){
            MissoesModel missaoAtualizadaModel = missoesMapper.map(missaoAtualizada);
            missaoAtualizadaModel.setId(id);
            MissoesModel missaoSalvo = missoesRepository.save(missaoAtualizadaModel);
            return  missoesMapper.map(missaoSalvo);
        }
        return null;
    }









}
