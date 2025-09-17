package br.com.allanisaacdev.CadastroDeNinjas.Ninjas;




import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("ninjas/ui") //Passar a rota
public class NinjaControllerUi {
    private final NinjaService ninjaService;

    public NinjaControllerUi(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/listar")
    public String mostrarTodosOSNinjas(Model model) {
        List<NinjaDTO> listaNinja = ninjaService.listarNinjas();
        model.addAttribute("ninjas", listaNinja);
        //Tem que retornar o nome do arquivo HTML (sem a extensão .html)
        return "listarNinjas.html";
    }


    @GetMapping("/deletar/{id}")
    public String deletarNinjaPorId(@PathVariable Long id) {

        //Vai deletar e vai retonar para a página de listagem
        ninjaService.deletarNinjaPorId(id);
        return "redirect:/ninjas/ui/listar";
    }

    @GetMapping("/listar/{id}")
    public String listarNinjasPorId(@PathVariable Long id, Model model) {
        NinjaDTO ninjaPorId = ninjaService.listarNinjasPorId(id);
        if (ninjaPorId != null) {
            model.addAttribute("ninjas", ninjaPorId);
            return "detalhesninja.html";
        } else {
            model.addAttribute("menssagem", "Ninja não encontrado!");
            return "listarNinjas.html";
        }
    }

    @GetMapping("/adicionar")
    public String mostrarFormularioAdicionarNinja(Model model) {
        model.addAttribute("ninja", new NinjaDTO());
        return "adicionarNinja";
    }

    @PostMapping("/salvar")
    public String salvarNinja(@ModelAttribute NinjaDTO ninja, RedirectAttributes redirectAttributes) {
        ninjaService.criarNinja(ninja);
        redirectAttributes.addFlashAttribute("mensagem", "Ninja cadastrado com sucesso!");
        return "redirect:/ninjas/ui/listar";
    }
}