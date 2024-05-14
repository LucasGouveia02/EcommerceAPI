package com.br.senac.EcommerceAPI.Repositories;

import com.br.senac.EcommerceAPI.Models.CarrinhoProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrinhoProdutoRepository extends JpaRepository<CarrinhoProdutoModel, Long> {
}
