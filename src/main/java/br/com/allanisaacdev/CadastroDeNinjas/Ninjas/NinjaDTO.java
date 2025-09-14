package br.com.allanisaacdev.CadastroDeNinjas.Ninjas;

import br.com.allanisaacdev.CadastroDeNinjas.Missoes.MissoesModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class NinjaDTO {

    private long id;
    private String nome;
    private String email;
    private String imgUrl;
    private String rank;
    private int idade;
    private String sexo;
    private MissoesModel missoes;
}
