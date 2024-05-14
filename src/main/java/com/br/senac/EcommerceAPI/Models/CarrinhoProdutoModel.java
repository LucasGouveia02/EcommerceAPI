package com.br.senac.EcommerceAPI.Models;

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
@Table(name = "carrinho_produto")
@Component
public class CarrinhoProdutoModel {
    @EmbeddedId
    private CarrinhoProdutoKey id;
    @Column(nullable = false, name = "quantidade")
    private int qtd;
}
