package com.br.senac.EcommerceAPI.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Embeddable
public class CarrinhoProdutoKey {
    @ManyToOne
    @JoinColumn(name = "fk_produto_id")
    private ProdutoModel produtoId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_carrinho_id")
    private CarrinhoModel carrinhoId;
}
