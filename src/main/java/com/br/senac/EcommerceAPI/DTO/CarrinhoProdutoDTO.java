package com.br.senac.EcommerceAPI.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarrinhoProdutoDTO {
    private Long idCarrinho;
    private Long idProduto;
    private String tamanho;
    private Integer quantidadeProduto;
}
