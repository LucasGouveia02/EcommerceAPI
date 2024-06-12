package com.br.senac.EcommerceAPI.Repositories;

import com.br.senac.EcommerceAPI.Keys.ProdutoUsuarioKey;
import com.br.senac.EcommerceAPI.Models.CredencialModel;
import com.br.senac.EcommerceAPI.Models.FavoritoModel;
import com.br.senac.EcommerceAPI.Models.ProdutoModel;
import com.br.senac.EcommerceAPI.Models.UsuarioModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoritoRepository extends JpaRepository<FavoritoModel, ProdutoUsuarioKey> {
    @Query("SELECT u.id.produtoId FROM FavoritoModel u WHERE u.id.usuarioId = ?1")
    List<ProdutoModel> findFavoritosByUserId(UsuarioModel id);
    @Query("SELECT u FROM FavoritoModel u WHERE u.id.usuarioId = :userId AND u.id.produtoId = :productId")
    FavoritoModel findFavoritoObjectByUserId(UsuarioModel userId, ProdutoModel productId);

    @Transactional
    @Modifying
    @Query("DELETE FROM FavoritoModel p WHERE p.id.produtoId = :idProduto")
    void limparProdutoFavoritos(@Param("idProduto") ProdutoModel idProduto);
}
