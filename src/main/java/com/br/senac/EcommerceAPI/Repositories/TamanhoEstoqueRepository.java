package com.br.senac.EcommerceAPI.Repositories;

import com.br.senac.EcommerceAPI.Models.ProdutoModel;
import com.br.senac.EcommerceAPI.Models.TamanhoEstoqueModel;
import com.br.senac.EcommerceAPI.Models.URLImagensModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TamanhoEstoqueRepository extends JpaRepository<TamanhoEstoqueModel, Long> {
    @Query("SELECT p FROM TamanhoEstoqueModel p WHERE p.produtoId = :produtoId")
    List<TamanhoEstoqueModel> buscarEstoques(@Param("produtoId") ProdutoModel produtoId);

    @Transactional
    @Modifying
    @Query("DELETE FROM TamanhoEstoqueModel p WHERE p.produtoId = :produtoId")
    void limparTamanhoEstoque(@Param("produtoId") ProdutoModel produtoId);
}
