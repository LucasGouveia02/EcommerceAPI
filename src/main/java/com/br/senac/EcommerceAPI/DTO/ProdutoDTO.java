package com.br.senac.EcommerceAPI.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {
    private String nome;
    private String categoria;
    private MultipartFile imagem;

    public ProdutoDTO(String nome, String categoria) {
        this.nome = nome;
        this.categoria = categoria;
    }
}
