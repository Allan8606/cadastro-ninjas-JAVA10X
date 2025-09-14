package br.com.allanisaacdev.CadastroDeNinjas.Missoes;


import org.springframework.stereotype.Component;

@Component
public class MissoesMapper {

    public MissoesModel map(MissoesDTO missoesDTO){
        MissoesModel missaoModel = new MissoesModel();

        missaoModel.setId(missoesDTO.getId());
        missaoModel.setNome(missoesDTO.getNome());
        missaoModel.setDificuldade(missoesDTO.getDificuldade());
        missaoModel.setNinjas(missoesDTO.getNinjas());

        return missaoModel;
    }

    public MissoesDTO map(MissoesModel missoesModel){
        MissoesDTO missoesDTO = new MissoesDTO();

        missoesDTO.setId(missoesModel.getId());
        missoesDTO.setNome(missoesModel.getNome());
        missoesDTO.setDificuldade(missoesModel.getDificuldade());
        missoesDTO.setNinjas(missoesModel.getNinjas());

        return missoesDTO;

    }


}
