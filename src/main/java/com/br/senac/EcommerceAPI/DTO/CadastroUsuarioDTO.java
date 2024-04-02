package com.br.senac.EcommerceAPI.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class CadastroUsuarioDTO {

    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private Date dtNascimento;
    private String cep;
    private String logradouro;
    private String bairro;
    private String numero;
    private String cidade;
    private String uf;
//    private Long usuarioId;
}