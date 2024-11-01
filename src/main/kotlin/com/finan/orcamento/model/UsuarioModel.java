package com.finan.orcamento.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario")
public class UsuarioModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "nome_usuario")
    private String nomeUsuario;

    @NotBlank
    @Column(name = "email_usuario")
    private String emailUsuario;

    @NotBlank
    @Column(name = "cpf_usuario")
    private String cpfUsuario;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario") // Supondo que você tenha uma referência para UsuarioModel em OrcamentoModel
    private List<OrcamentoModel> orcamentos = new ArrayList<>();
}
