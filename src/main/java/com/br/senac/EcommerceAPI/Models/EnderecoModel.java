package com.br.senac.EcommerceAPI.Models;

import com.br.senac.EcommerceAPI.DTO.EnderecoDTO;
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
    @Column(nullable = false)
    private String numero;
    @Column(nullable = false)
    private String bairro;
    @Column(nullable = true)
    private String complemento;
    @Column(nullable = false)
    private String cidade;
    @Column(nullable = false)
    private String uf;

    public EnderecoModel(EnderecoDTO dto) {
        this.cep = dto.getCep();
        this.logradouro = dto.getLogradouro();
        this.bairro = dto.getBairro();
        this.numero = dto.getNumero();
        this.cidade = dto.getCidade();
        this.uf = dto.getUf();
    }
}
