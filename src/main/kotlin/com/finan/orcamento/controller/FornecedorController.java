package com.finan.orcamento.controller;

import com.finan.orcamento.model.FornecedorModel;
import com.finan.orcamento.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/fornecedores")
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;

    @GetMapping
    public String getFornecedorPage(Model model) {
        model.addAttribute("fornecedorModel", new FornecedorModel());
        return "fornecedorPage";
    }

    @PostMapping
    public String cadastraFornecedor(@ModelAttribute FornecedorModel fornecedorModel) {
        fornecedorService.cadastrarFornecedor(fornecedorModel);
        return "redirect:/fornecedores/pesquisa";
    }

    @GetMapping("/pesquisa")
    public String listarFornecedores(Model model) {
        List<FornecedorModel> fornecedores = fornecedorService.buscarFornecedor();
        model.addAttribute("fornecedores", fornecedores);
        return "fornecedorPage";
    }

    @GetMapping("/{id}")
    public String editarFornecedor(@PathVariable Long id, Model model) {
        FornecedorModel fornecedor = fornecedorService.buscaId(id);
        model.addAttribute("fornecedorModel", fornecedor);
        return "fornecedorPage";
    }

    @PostMapping("/{id}")
    public String atualizaFornecedor(@ModelAttribute FornecedorModel fornecedorModel, @PathVariable Long id) {
        fornecedorService.atualizaFornecedor(fornecedorModel, id);
        return "redirect:/fornecedores/pesquisa";
    }

    @DeleteMapping("/{id}")
    public String deletaFornecedor(@PathVariable Long id) {
        fornecedorService.deletaFornecedor(id);
        return "redirect:/fornecedores/pesquisa";
    }
}
