package com.br.senac.EcommerceAPI.DTO;

import com.br.senac.EcommerceAPI.Models.UsuarioModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PedidoDTO {

    private Long idUsuario;
    private Date dataPedido;
    private String tipoEnvio;
    private String enderecoEnvio;
    private String formaPagamento;
    private Double totalPedido;
}
