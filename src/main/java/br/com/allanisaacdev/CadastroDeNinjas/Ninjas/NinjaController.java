package br.com.allanisaacdev.CadastroDeNinjas.Ninjas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasvindas")
    @Operation(summary = "Menssagem de boas vindas", description = "Essa rota retorna uma menssagem de boas vindas ao usuário")
    public String boasVindas(){
        return "Bem-vindo ao Cadastro de Ninjas!";
    }

    //Adicionar Ninja (CREATE)
    @PostMapping("/criar")
    @Operation(summary = "Criar um novo ninja", description = "Essa rota cria um novo ninja no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ninja criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na criação do ninja")
    })//É a resposta que a API retorna .
    public ResponseEntity<String> criarNinja(
            @Parameter(description = "Usuario manda os dados do ninja no corpo da requisiçao")
            @RequestBody NinjaDTO ninjaModel) {
        NinjaDTO ninjaDTO = ninjaService.criarNinja(ninjaModel);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja criado com sucesso! Nome: " + ninjaDTO.getNome());
    }

    //Procurar todos os Ninjas (READ)
    @GetMapping("/listar")
    @Operation(summary = "Lista todos os ninjas", description = "Rota que lista os ninjas cadastrados no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Lista de ninjas retornada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Não foi possível retornar a lista de ninjas")
    })
    public ResponseEntity<List<NinjaDTO>> mostrarTodosOSNinjas() {
        List<NinjaDTO> listaNinja = ninjaService.listarNinjas();
        return ResponseEntity.ok(listaNinja);
    }

    //Mostrar Nnja por ID (READ)
    @GetMapping("/listar/{id}") //O @PathVariable é usado para extrair o valor do ID da URL.
    @Operation(summary = "Lista o ninja por id", description = "Rota que lista o ninja pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ninja encontrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Ninja não encontrado")
    })
    // O ? foi usado para indicar que o ResponseEntity pode retornar qualquer tipo de corpo.
    public ResponseEntity<?> listarNinjasPorId(
            @Parameter(description = "Usuario manda o id no caminho da requisiçao")
            @PathVariable Long id) {
        NinjaDTO ninjaPorId = ninjaService.listarNinjasPorId(id);

        if(ninjaPorId != null){
            return ResponseEntity.ok(ninjaPorId);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja não existe!");
        }

    }

    //Alterar Dados do Ninja (UPDATE)
    @PutMapping("/alterar/{id}")
    @Operation(summary = "Altera o ninja por Id", description = "Rota altera um ninja pelo seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja alterado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninja nao encontrado, nao foi possivel alterar")
    })
    public ResponseEntity<?> alterarNinjaPorId(
            @Parameter(description = "Usuario manda o id no caminho da requisiçao")
            @PathVariable Long id,
            @Parameter(description = "Usuario manda os dados do ninja a ser atualizado no corpo da requisiçao")
            @RequestBody NinjaDTO ninjaAtualizado) {

        NinjaDTO ninja = ninjaService.atualizarNinja(id, ninjaAtualizado);
        if (ninja != null) {
            return ResponseEntity.ok(ninja);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja com o id: " + id + " nao existe nos nossos registros");
        }
    }

    //Deletar Ninja (DELETE)
    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deleta um ninja", description = "Rota que deleta um ninja pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ninja deletado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Não foi possível deletar o ninja")
    })
    public ResponseEntity<String> deletarNinjaPorId(
            @Parameter(description = "Usuario manda o id no caminho da requisiçao")
            @PathVariable Long id) {

        if(ninjaService.listarNinjasPorId(id) != null){
            ninjaService.deletarNinjaPorId(id);
            return ResponseEntity.ok("Ninja deletado com sucesso! O ninja que foi deletado, tem o id: " + id );
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja não encontrado, impossível deletar!");
        }


    }



}