package com.br.senac.EcommerceAPI.Models;

import com.br.senac.EcommerceAPI.DTO.ProdutoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity @Table(name = "produtos")
public class ProdutoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String categoria;

    public ProdutoModel(ProdutoDto dto) {
        this.nome = dto.getNome();
        this.categoria = dto.getCategoria();
    }
}
