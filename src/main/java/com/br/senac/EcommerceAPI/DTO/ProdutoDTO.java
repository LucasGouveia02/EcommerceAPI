package com.br.senac.EcommerceAPI.DTO;

import com.br.senac.EcommerceAPI.Models.TamanhoEstoqueModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO implements Serializable {

    private Long id;
    private String nome;
    private BigDecimal preco;
    private String categoria;
    private String marca;
    private List<TamanhoEstoqueModel> tamanhosEstoque;
    private String unidade;
    private String descricao;
}
