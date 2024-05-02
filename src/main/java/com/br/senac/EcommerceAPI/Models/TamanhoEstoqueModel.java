package com.br.senac.EcommerceAPI.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "Tamanho_e_estoque")
public class TamanhoEstoqueModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "tamanho")
    @JsonProperty("tamanho")
    private String tamanho;
    @Column(nullable = false, name = "estoque")
    @JsonProperty("estoque")
    private int estoque;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private ProdutoModel produtoId;
}
