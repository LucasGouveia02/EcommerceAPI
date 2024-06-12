package com.br.senac.EcommerceAPI.Repositories;

import com.br.senac.EcommerceAPI.Keys.CarrinhoProdutoKey;
import com.br.senac.EcommerceAPI.Models.CarrinhoModel;
import com.br.senac.EcommerceAPI.Models.CarrinhoProdutoModel;
import com.br.senac.EcommerceAPI.Models.ProdutoModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrinhoProdutoRepository extends JpaRepository<CarrinhoProdutoModel, Long> {
    @Query("SELECT p FROM CarrinhoProdutoModel p WHERE p.id = :id AND p.tamanho = :tamanho")
    CarrinhoProdutoModel buscarPorIdTamanho(CarrinhoProdutoKey id, String tamanho);

    @Transactional
    @Modifying
    @Query("DELETE FROM CarrinhoProdutoModel p WHERE p.id.carrinhoId = :idCarrinho")
    void limparCarrinho(@Param("idCarrinho") CarrinhoModel idCarrinho);

    @Transactional
    @Modifying
    @Query("DELETE FROM CarrinhoProdutoModel p WHERE p.id.produtoId = :idProduto")
    void limparProdutoCarrinhos(@Param("idProduto") ProdutoModel idProduto);
}
