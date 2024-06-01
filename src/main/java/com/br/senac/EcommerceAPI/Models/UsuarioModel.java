package com.br.senac.EcommerceAPI.Models;

import com.br.senac.EcommerceAPI.DTO.CadastroUsuarioDTO;
import com.br.senac.EcommerceAPI.DTO.UsuarioDTO;
import com.br.senac.EcommerceAPI.DTO.UsuarioInfoDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
@Table(name = "usuario")
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String telefone;
    @Column(nullable = false, unique = true)
    private String cpf;
    @Column(nullable = true, name = "dt_nascimento")
    private Date dtNascimento;
    @OneToOne(mappedBy = "usuario_id", cascade = CascadeType.ALL)
    private CarrinhoModel carrinho;

    public UsuarioModel(UsuarioDTO dto) {
        this.nome = dto.getNome();
        this.telefone = dto.getTelefone();
        this.cpf = dto.getCpf();
        this.dtNascimento = dto.getDtNascimento();
    }
    public UsuarioModel(CadastroUsuarioDTO dto) {
        this.nome = dto.getNome();
        this.telefone = dto.getTelefone();
        this.cpf = dto.getCpf();
        this.dtNascimento = dto.getDtNascimento();
    }
    public UsuarioModel(UsuarioInfoDTO dto) {
        this.nome = dto.getNome();
        this.cpf = dto.getCpf();
        this.dtNascimento = dto.getDtNascimento();
        this.telefone = dto.getTelefone();
    }
}
