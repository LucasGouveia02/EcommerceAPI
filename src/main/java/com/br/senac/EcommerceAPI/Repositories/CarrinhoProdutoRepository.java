package com.br.senac.EcommerceAPI.Repositories;

import com.br.senac.EcommerceAPI.Keys.CarrinhoProdutoKey;
import com.br.senac.EcommerceAPI.Models.CarrinhoProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrinhoProdutoRepository extends JpaRepository<CarrinhoProdutoModel, Long> {
    @Query("SELECT p FROM CarrinhoProdutoModel p WHERE p.id = :id AND p.tamanho = :tamanho")
    CarrinhoProdutoModel buscarPorIdTamanho(CarrinhoProdutoKey id, String tamanho);
}
