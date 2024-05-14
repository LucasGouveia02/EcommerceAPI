package com.br.senac.EcommerceAPI.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "carrinho")
@Component
public class CarrinhoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioModel usuario_id;
    @Column(name = "quantidadeItens", nullable = false)
    private Integer quantidadeItens;
    @OneToMany(mappedBy = "id.carrinhoId", cascade = CascadeType.ALL)
    private List<CarrinhoProdutoModel> carrinhoProdutoModel;
}
