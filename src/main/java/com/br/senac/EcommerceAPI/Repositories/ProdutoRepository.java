package com.br.senac.EcommerceAPI.Repositories;

import com.br.senac.EcommerceAPI.DTO.ProdutoAllInfoDTO;
import com.br.senac.EcommerceAPI.Models.CarrinhoModel;
import com.br.senac.EcommerceAPI.Models.ProdutoModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {
    @Query("SELECT p FROM ProdutoModel p WHERE p.categoria = :categoryName AND p.ativado = true")
    List<ProdutoModel> findByCategoryName(@Param("categoryName") String categoryName);
}
