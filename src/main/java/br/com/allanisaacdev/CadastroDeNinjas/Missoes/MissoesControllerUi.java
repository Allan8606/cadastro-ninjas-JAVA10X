package br.com.allanisaacdev.CadastroDeNinjas.Missoes;

import br.com.allanisaacdev.CadastroDeNinjas.Ninjas.NinjaDTO;
import br.com.allanisaacdev.CadastroDeNinjas.Ninjas.NinjaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("missoes/ui")
public class MissoesControllerUi {

    private final MissoesService missoesService;
    private final NinjaService ninjaService;

    public MissoesControllerUi(MissoesService missoesService, NinjaService ninjaService){
        this.missoesService = missoesService;
        this.ninjaService = ninjaService;
    }

    @GetMapping("/listar")
    public String listarMissoes(Model model){
        List<MissoesDTO> listaMissoes = missoesService.listarMissoes();
        model.addAttribute("missoes", listaMissoes);
        return "listarMissoes.html";
    }

    @GetMapping("/listar/{id}")
    public String listarMissaoPorId(@PathVariable Long id, Model model) {
        MissoesDTO missaoPorId = missoesService.listarMissoesPorId(id);
        if (missaoPorId != null) {
            model.addAttribute("missao", missaoPorId);
            return "detalhesmissao";
        } else {
            model.addAttribute("menssagem", "Missão não encontrada!");
            return "listarmissoes";
        }
    }

    @GetMapping("/deletar/{id}")
    public String deletarMissao(@PathVariable Long id) {
        missoesService.deletarMissaoPorId(id);
        return "redirect:/missoes/ui/listar";
    }

    @GetMapping("/adicionar")
    public String mostrarFormularioAdicionarMissao(Model model) {
        List<NinjaDTO> ninjasList = ninjaService.listarNinjas();
        model.addAttribute("ninjasList", ninjasList);
        model.addAttribute("missao", new MissoesDTO());
        return "adicionarMissao";
    }

    @PostMapping("/salvar")
    public String salvarMissao(@ModelAttribute MissoesDTO missao, RedirectAttributes redirectAttributes) {
        missoesService.criarMissao(missao);
        redirectAttributes.addFlashAttribute("mensagem", "Missão cadastrada com sucesso!");
        return "redirect:/missoes/ui/listar";
    }
}
