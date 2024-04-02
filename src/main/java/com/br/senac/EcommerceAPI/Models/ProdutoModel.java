package com.br.senac.EcommerceAPI.Models;

import com.br.senac.EcommerceAPI.DTO.ProdutoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Objects;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "produto")
@Component
public class ProdutoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(nullable = false, name = "nome")
    private String nome;
    @Column(nullable = false, name = "categoria")
    private String categoria;

    public ProdutoModel(ProdutoDTO dto) {
        this.nome = dto.getNome();
        this.categoria = dto.getCategoria();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProdutoModel that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getNome(), that.getNome()) && Objects.equals(getCategoria(), that.getCategoria());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getCategoria());
    }
}
