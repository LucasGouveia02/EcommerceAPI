package com.br.senac.EcommerceAPI.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pedido")
@Component
public class PedidoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioModel usuario_id;

    @Column(name = "dataPedido", nullable = false)
    private Date dataPedido;

    @Column(name = "tipoEnvio", nullable = false)
    private String tipoEnvio;

    @Column(name = "enderecoEnvio", nullable = false)
    private String enderecoEnvio;

    @Column(name = "formaPagamento", nullable = false)
    private String formaPagamento;

    @Column(name = "totalPedido", nullable = false)
    private Double totalPedido;

    @OneToMany(mappedBy = "id.pedidoId", cascade = CascadeType.ALL)
    private List<PedidoProdutoModel> pedidoProdutoModel;
}
