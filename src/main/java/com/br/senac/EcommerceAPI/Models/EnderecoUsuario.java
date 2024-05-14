package com.br.senac.EcommerceAPI.Models;

import com.br.senac.EcommerceAPI.Keys.EnderecoUsuarioKey;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
@Table(name = "endereco_usuario")
public class EnderecoUsuario {

    @EmbeddedId
    private EnderecoUsuarioKey id;
}
