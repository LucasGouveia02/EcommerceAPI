package com.br.senac.EcommerceAPI.Models;

import com.br.senac.EcommerceAPI.Keys.ProdutoUsuarioKey;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "favorito")
@Component
public class FavoritoModel {
    @EmbeddedId
    private ProdutoUsuarioKey id;
}
