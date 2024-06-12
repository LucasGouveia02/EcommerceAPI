package com.br.senac.EcommerceAPI.Repositories;

import com.br.senac.EcommerceAPI.Keys.CarrinhoProdutoKey;
import com.br.senac.EcommerceAPI.Models.CarrinhoModel;
import com.br.senac.EcommerceAPI.Models.CarrinhoProdutoModel;
import com.br.senac.EcommerceAPI.Models.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarrinhoRepository extends JpaRepository<CarrinhoModel, Long> {

}
