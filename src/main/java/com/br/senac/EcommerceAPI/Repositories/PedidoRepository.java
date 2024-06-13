package com.br.senac.EcommerceAPI.Repositories;

import com.br.senac.EcommerceAPI.Models.PedidoModel;
import com.br.senac.EcommerceAPI.Models.PedidoProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoModel, Long> {
    @Query("SELECT p FROM PedidoProdutoModel p WHERE p.id.pedidoId = :pedidoId ")
     List<PedidoProdutoModel>  listapedidos(@Param("pedidoId") PedidoModel pedidoId);
}
