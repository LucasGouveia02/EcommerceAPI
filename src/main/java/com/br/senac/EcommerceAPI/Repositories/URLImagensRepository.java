package com.br.senac.EcommerceAPI.Repositories;

import com.br.senac.EcommerceAPI.Models.ProdutoModel;
import com.br.senac.EcommerceAPI.Models.URLImagensModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface URLImagensRepository extends JpaRepository<URLImagensModel, Long> {
    @Transactional
    @Modifying
    @Query("DELETE FROM URLImagensModel p WHERE p.produtoId = :produtoId")
    void limparURLs(@Param("produtoId") ProdutoModel produtoId);
}
