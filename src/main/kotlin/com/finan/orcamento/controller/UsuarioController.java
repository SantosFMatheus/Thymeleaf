package com.finan.orcamento.controller;

import com.finan.orcamento.model.UsuarioModel;
import com.finan.orcamento.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String getUsuarioPage(Model model) {
        model.addAttribute("usuarioModel", new UsuarioModel());
        return "usuarioPage";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String cadastraUsuario(@ModelAttribute UsuarioModel usuarioModel, Model model) {
        usuarioService.cadastrarUsuario(usuarioModel); // Chamada para o serviço para cadastrar o usuário
        return "redirect:/usuarios/pesquisa"; // Redireciona para o método listarUsuarios
    }

    @GetMapping("/pesquisa")
    public String listarUsuarios(Model model) {
        List<UsuarioModel> usuarios = usuarioService.buscarUsuario();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("usuarioModel", new UsuarioModel()); // Reinicia o formulário
        return "usuarioPage";
    }
}
