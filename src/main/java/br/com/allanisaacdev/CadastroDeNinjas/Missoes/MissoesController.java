package br.com.allanisaacdev.CadastroDeNinjas.Missoes;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missoes")
public class MissoesController {

    private MissoesService missoesService;

    public MissoesController(MissoesService missoesService){
        this.missoesService = missoesService;
    }

    @GetMapping("/listar")
    public List<MissoesDTO> listarMissoes(){
        return missoesService.listarMissoes();
    }

    @GetMapping("/listar/{id}")
    public MissoesDTO listarMissaoPorId(@PathVariable Long id){
        return missoesService.listarMissoesPorId(id);
    }


    @PostMapping("/criar")
    public MissoesDTO criarMissao(@RequestBody MissoesDTO missaoDTO){
        return missoesService.criarMissao(missaoDTO);
    }

    //Alterar Missao
    @PutMapping("/alterar/{id}")
    public MissoesModel alterarMissaoPorId(@PathVariable Long id, @RequestBody MissoesModel missaoAtualizada){
        return missoesService.atualizaMissao(id, missaoAtualizada);
    }

    //Deletar Missao
    @DeleteMapping("/deletar/{id}")
    public void deletarMissao(@PathVariable Long id){
        missoesService.deletarMissaoPorId(id);
    }

}
