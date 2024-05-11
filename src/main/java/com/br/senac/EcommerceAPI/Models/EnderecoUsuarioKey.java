package com.br.senac.EcommerceAPI.Models;

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
public class EnderecoUsuarioKey {

    @ManyToOne
    @JoinColumn(name = "fk_usuario_id")
    private UsuarioModel usuarioId;

    @ManyToOne
    @JoinColumn(name = "fk_endereco_id")
    private EnderecoModel enderecoId;

}
