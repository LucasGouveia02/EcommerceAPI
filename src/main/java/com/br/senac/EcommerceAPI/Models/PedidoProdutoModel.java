package com.br.senac.EcommerceAPI.Models;

import com.br.senac.EcommerceAPI.Keys.PedidoProdutoKey;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pedido_produto")
@Component

public class PedidoProdutoModel {

    @EmbeddedId
    private PedidoProdutoKey id;

    @Column(nullable = false, name = "quantidade")
    private Integer qtd;

    @Column(nullable = false, name = "tamanho")
    private String tamanho;
}
