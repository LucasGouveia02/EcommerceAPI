package com.br.senac.EcommerceAPI.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class CarrinhoDTO {

    private long idUsuario;
    private long idCarrinho;
    private long idProduto;
    private String tamanho;
    private int qtd;

}
