package com.br.senac.EcommerceAPI.DTO;

import com.br.senac.EcommerceAPI.Models.TamanhoEstoqueModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstoqueDTO {
    private Long productId;
    private List<TamanhoEstoqueModel> tamanhosEstoque;
}
