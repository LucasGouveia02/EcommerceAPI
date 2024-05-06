package com.br.senac.EcommerceAPI.Models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class CarrinhoProdutoKey {
    @ManyToOne
    @JoinColumn(name = "fk_produto_id")
    private ProdutoModel produtoId;

    @ManyToOne
    @JoinColumn(name = "fk_carrinho_id")
    private CarrinhoModel carrinhoId;
}
