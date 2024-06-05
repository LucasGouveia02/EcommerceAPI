package com.br.senac.EcommerceAPI.Keys;

import com.br.senac.EcommerceAPI.Models.PedidoModel;
import com.br.senac.EcommerceAPI.Models.ProdutoModel;
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
@Getter
@Setter
@Embeddable
public class PedidoProdutoKey {
    @ManyToOne
    @JoinColumn(name = "fk_pedido_id")
    private PedidoModel pedidoId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_produto_id")
    private ProdutoModel produtoId;
}
