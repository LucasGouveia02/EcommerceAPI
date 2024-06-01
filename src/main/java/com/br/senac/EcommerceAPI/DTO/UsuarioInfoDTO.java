package com.br.senac.EcommerceAPI.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class UsuarioInfoDTO {

    private String nome;
    private Date dtNascimento;
    private String cpf;
    private String telefone;
    private String email;
    private String senha;
}
