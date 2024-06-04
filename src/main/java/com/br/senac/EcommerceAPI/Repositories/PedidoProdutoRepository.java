package com.br.senac.EcommerceAPI.Repositories;

import com.br.senac.EcommerceAPI.Keys.PedidoProdutoKey;
import com.br.senac.EcommerceAPI.Models.PedidoProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoProdutoRepository extends JpaRepository <PedidoProdutoModel, PedidoProdutoKey> {
}
