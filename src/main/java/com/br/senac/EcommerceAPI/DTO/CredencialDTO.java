package com.br.senac.EcommerceAPI.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class CredencialDTO {
    private String email;
    private String senha;
    private Long idUsuario;
    private boolean admin;
}
