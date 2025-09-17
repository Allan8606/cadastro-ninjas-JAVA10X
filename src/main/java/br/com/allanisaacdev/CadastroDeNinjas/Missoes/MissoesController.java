package br.com.allanisaacdev.CadastroDeNinjas.Missoes;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @Operation(summary = "Lista todas as missão", description = "Rota que lista as missão que estão cadastradas no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Missões encontradas sucesso"),
            @ApiResponse(responseCode = "400", description = "Não foi possível encontrar as missão")
    })
    public ResponseEntity<List<MissoesDTO>> listarMissoes(){
        List<MissoesDTO> listaMissoes = missoesService.listarMissoes();

        return ResponseEntity.ok(listaMissoes);
    }

    @GetMapping("/listar/{id}")
    @Operation(summary = "Lista a missão pelo seu id", description = "Rota que lisra missão pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Missão encontrada sucesso"),
            @ApiResponse(responseCode = "400", description = "Não foi possível encontrar a missão")
    })
    public ResponseEntity<?> listarMissaoPorId(
            @Parameter(description = "Usuario manda o id no caminho da requisiçao")
            @PathVariable Long id){
        MissoesDTO missaoPorID = missoesService.listarMissoesPorId(id);

        if (missaoPorID != null){
            return ResponseEntity.ok(missaoPorID);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Missão não encontrada!");
        }

    }


    @PostMapping("/criar")
    @Operation(summary = "Cria uma nova missão", description = "Rota que cria uma nova missão")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Missão criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Não foi possível criar a missão")
    })
    public ResponseEntity<String> criarMissao(
            @Parameter(description = "Usuario manda os dados da missão no corpo da requisiçao")
            @RequestBody MissoesDTO missaoDTO){
        MissoesDTO missoesDTO = missoesService.criarMissao(missaoDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Missão criada com sucesso! Nome: " + missoesDTO.getNome());

    }

    //Alterar Missao
    @PutMapping("/alterar/{id}")
    @Operation(summary = "Altera missão pelo id", description = "Rota que vai alterar um ninja pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A missão foi criada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Missãp não encontrada para atualização")
    })
    public ResponseEntity<?> alterarMissaoPorId(
            @Parameter(description = "Usuario manda o id no caminho da requisiçao")
            @PathVariable Long id,
            @Parameter(description = "Usuario manda qual a missão deseja atualizar no corpo da requisiçao")
            @RequestBody MissoesDTO missaoAtualizada){
        MissoesDTO missaoAlterada = missoesService.atualizaMissao(id, missaoAtualizada);

        if (missaoAlterada != null){
            return ResponseEntity.ok(missaoAlterada);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão não encontrada para atualização!");
        }
    }

    //Deletar Missao
    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deleta uma missão", description = "Rota que deleta uma missão pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Missão deletada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Não foi possível deletar a missão")
    })
    public ResponseEntity<String> deletarMissao(
            @Parameter(description = "Usuario manda o id no caminho da requisiçao")
            @PathVariable Long id){
        if (missoesService.listarMissoesPorId(id) != null){
            missoesService.deletarMissaoPorId(id);
            return ResponseEntity.ok("Missão deletada com sucesso!");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Missão não encontrada para deletar!");
        }

    }

}
