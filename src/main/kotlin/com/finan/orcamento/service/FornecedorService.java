package com.finan.orcamento.service;

import com.finan.orcamento.model.FornecedorModel;
import com.finan.orcamento.repositories.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FornecedorService {
    @Autowired
    private FornecedorRepository fornecedorRepository;

    public List<FornecedorModel> buscarFornecedor() {
        return fornecedorRepository.findAll();
    }

    public FornecedorModel buscaId(Long id) {
        return fornecedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));
    }

    public FornecedorModel cadastrarFornecedor(FornecedorModel fornecedorModel) {
        return fornecedorRepository.save(fornecedorModel);
    }

    public FornecedorModel atualizaFornecedor(FornecedorModel fornecedorModel, Long id) {
        FornecedorModel newFornecedorModel = buscaId(id);
        newFornecedorModel.setRazaoSocial(fornecedorModel.getRazaoSocial());
        newFornecedorModel.setNomeFantasia(fornecedorModel.getNomeFantasia());
        newFornecedorModel.setCnpj(fornecedorModel.getCnpj());
        newFornecedorModel.setInscricaoEstadual(fornecedorModel.getInscricaoEstadual());
        newFornecedorModel.setEstado(fornecedorModel.getEstado());
        return fornecedorRepository.save(newFornecedorModel);
    }

    public void deletaFornecedor(Long id) {
        fornecedorRepository.deleteById(id);
    }
}
