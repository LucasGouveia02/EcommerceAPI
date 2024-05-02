package com.br.senac.EcommerceAPI.Repositories;

import com.br.senac.EcommerceAPI.Models.TamanhoEstoqueModel;
import com.br.senac.EcommerceAPI.Models.URLImagensModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TamanhoEstoqueRepository extends JpaRepository<TamanhoEstoqueModel, Long> {
}
