package com.br.senac.EcommerceAPI.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class EnderecoDTO {

    private String cep;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String uf;
    private String numero;

}
