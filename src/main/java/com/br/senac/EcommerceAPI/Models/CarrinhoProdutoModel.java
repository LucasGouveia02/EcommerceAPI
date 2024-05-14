package com.br.senac.EcommerceAPI.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
    private Integer qtd;
    @Column(nullable = false, name = "tamanho")
    private String tamanho;
}
