package com.br.senac.EcommerceAPI.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class ProdutoAllInfoDTO {
    private String nome;
    private BigDecimal preco;
    private String categoria;
    private String marca;
    private String tamanho;
    private String unidade;
    private Integer estoque;
    private String descricao;
    private List<String> url;

    public ProdutoAllInfoDTO(String nome, BigDecimal preco, String categoria,
                             String marca, String tamanho, String unidade,
                             Integer estoque, String descricao) {
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
        this.marca = marca;
        this.tamanho = tamanho;
        this.unidade = unidade;
        this.estoque = estoque;
        this.descricao = descricao;
    }
}
