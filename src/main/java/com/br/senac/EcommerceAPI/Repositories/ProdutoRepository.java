package com.br.senac.EcommerceAPI.Repositories;

import com.br.senac.EcommerceAPI.Models.ProdutoModel;
import org.springframework.data.repository.CrudRepository;

public interface ProdutoRepository extends CrudRepository<ProdutoModel, Integer> {
}
