package com.finan.orcamento.controller;

import com.finan.orcamento.model.OrcamentoModel;
import com.finan.orcamento.model.UsuarioModel;
import com.finan.orcamento.service.OrcamentoService;
import com.finan.orcamento.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orcamentos")
public class OrcamentoController {

    @Autowired
    private OrcamentoService orcamentoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String getOrcamentoPage(Model model) {
        model.addAttribute("orcamentoModel", new OrcamentoModel());
        List<UsuarioModel> usuarios = usuarioService.buscarUsuario();
        model.addAttribute("usuarios", usuarios); // Passa a lista de usuários
        return "orcamentoPage"; // Retorna a página de orçamento
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String cadastraOrcamento(@ModelAttribute OrcamentoModel orcamentoModel, Model model) {
        orcamentoService.cadastrarOrcamento(orcamentoModel); // Chamada para o serviço para cadastrar o orçamento
        return "redirect:/orcamentos/pesquisa"; // Redireciona para a página de orçamentos
    }

    @GetMapping("/pesquisa")
    public String listarOrcamentos(Model model) {
        List<OrcamentoModel> orcamentos = orcamentoService.buscarCadastro();
        model.addAttribute("orcamentos", orcamentos);
        model.addAttribute("orcamentoModel", new OrcamentoModel()); // Reinicia o formulário
        List<UsuarioModel> usuarios = usuarioService.buscarUsuario();
        model.addAttribute("usuarios", usuarios); // Passa a lista de usuários
        return "orcamentoPage"; // Retorna a página de orçamento
    }
}
