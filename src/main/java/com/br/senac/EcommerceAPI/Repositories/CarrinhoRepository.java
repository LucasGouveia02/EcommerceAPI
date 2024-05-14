package com.br.senac.EcommerceAPI.Repositories;

import com.br.senac.EcommerceAPI.Models.CarrinhoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrinhoRepository extends JpaRepository<CarrinhoModel, Long> {

}
