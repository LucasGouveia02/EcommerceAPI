package com.br.senac.EcommerceAPI.DTO;

import com.br.senac.EcommerceAPI.Models.ProdutoModel;
import com.br.senac.EcommerceAPI.Models.TamanhoEstoqueModel;
import com.br.senac.EcommerceAPI.Models.URLImagensModel;
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
    private List<TamanhoEstoqueModel> tamanhosEstoque;
    private List<URLImagensModel> urlImagensModels;
    private String unidade;
    private String descricao;
}
