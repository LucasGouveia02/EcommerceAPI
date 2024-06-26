package com.br.senac.EcommerceAPI.Models;

import com.br.senac.EcommerceAPI.DTO.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
@Table(name = "endereco")
public class EnderecoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String cep;
    @Column(nullable = false)
    private String logradouro;
    private String complemento;
    @Column(nullable = false)
    private String bairro;
    @Column(nullable = false)
    private String numero;
    @Column(nullable = false)
    private String cidade;
    @Column(nullable = false)
    private String uf;

    public EnderecoModel(EnderecoDTO dto) {
        this.cep = dto.getCep();
        this.logradouro = dto.getLogradouro();
        this.complemento = dto.getComplemento();
        this.bairro = dto.getBairro();
        this.numero = dto.getNumero();
        this.cidade = dto.getCidade();
        this.uf = dto.getUf();
    }
    public EnderecoModel(CadastroUsuarioDTO dto) {
        this.cep = dto.getCep();
        this.logradouro = dto.getLogradouro();
        this.complemento = dto.getComplemento();
        this.bairro = dto.getBairro();
        this.numero = dto.getNumero();
        this.cidade = dto.getCidade();
        this.uf = dto.getUf();
    }
    public EnderecoModel(NovoEnderecoDTO dto) {
        this.cep = dto.getCep();
        this.logradouro = dto.getLogradouro();
        this.complemento = dto.getComplemento();
        this.bairro = dto.getBairro();
        this.numero = dto.getNumero();
        this.cidade = dto.getCidade();
        this.uf = dto.getUf();
    }
    public EnderecoModel(AtualizarEnderecoDTO dto) {
        this.cep = dto.getCep();
        this.logradouro = dto.getLogradouro();
        this.complemento = dto.getComplemento();
        this.numero = dto.getNumero();
        this.bairro = dto.getBairro();
        this.cidade = dto.getCidade();
        this.uf = dto.getUf();
    }
}
