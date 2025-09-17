package br.com.allanisaacdev.CadastroDeNinjas.Ninjas;


import org.springframework.stereotype.Component;

@Component
public class NinjaMapper {

    public NinjaModel map(NinjaDTO ninjaDTO) {
        //Instancia um novo Objeto NinjaModel
        NinjaModel ninjaModel = new NinjaModel();

        //Pega esse novo Objeto NinjaModel e atribui os valores do ninjaDTO
        ninjaModel.setId(ninjaDTO.getId());
        ninjaModel.setNome(ninjaDTO.getNome());
        ninjaModel.setEmail(ninjaDTO.getEmail());
        ninjaModel.setImgUrl(ninjaDTO.getImgUrl());
        ninjaModel.setRank(ninjaDTO.getRank());
        ninjaModel.setIdade(ninjaDTO.getIdade());
        ninjaModel.setSexo(ninjaDTO.getSexo());
        ninjaModel.setMissoes(ninjaDTO.getMissoes());

        return ninjaModel;
    }

    public NinjaDTO map(NinjaModel ninjaModel){
        //Instancia o novo Objeto NinjaDTO
        NinjaDTO ninjaDTO = new NinjaDTO();
        //Pega esse novo Objeto NinjaDTO e atribui os valores do ninjaModel
        ninjaDTO.setId(ninjaModel.getId());
        ninjaDTO.setNome(ninjaModel.getNome());
        ninjaDTO.setEmail(ninjaModel.getEmail());
        ninjaDTO.setImgUrl(ninjaModel.getImgUrl());
        ninjaDTO.setRank(ninjaModel.getRank());
        ninjaDTO.setIdade(ninjaModel.getIdade());
        ninjaDTO.setSexo(ninjaModel.getSexo());
        ninjaDTO.setMissoes(ninjaModel.getMissoes());

        return ninjaDTO;
    }


}