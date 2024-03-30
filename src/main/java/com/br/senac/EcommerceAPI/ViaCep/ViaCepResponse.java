package com.br.senac.EcommerceAPI.ViaCep;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class ViaCepResponse {

    private String cep;
    private String logradouro;
    private String bairro;
    private String numero;
    @JsonProperty("localidade")
    private String cidade;
    private String uf;
}
