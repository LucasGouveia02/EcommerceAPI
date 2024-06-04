package com.br.senac.EcommerceAPI.Keys;

import com.br.senac.EcommerceAPI.Models.EnderecoModel;
import com.br.senac.EcommerceAPI.Models.ProdutoModel;
import com.br.senac.EcommerceAPI.Models.UsuarioModel;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Embeddable
public class ProdutoUsuarioKey {
    @ManyToOne
    @JoinColumn(name = "fk_usuario_id")
    private UsuarioModel usuarioId;

    @ManyToOne
    @JoinColumn(name = "fk_produto_id")
    private ProdutoModel produtoId;
}
