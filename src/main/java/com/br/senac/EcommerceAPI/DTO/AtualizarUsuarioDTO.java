package com.br.senac.EcommerceAPI.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class AtualizarUsuarioDTO {

    private String nome;
    private String telefone;
    private String cpf;
    private Date dtNascimento;
}
