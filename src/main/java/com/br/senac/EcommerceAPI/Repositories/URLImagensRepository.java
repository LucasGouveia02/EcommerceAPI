package com.br.senac.EcommerceAPI.Repositories;

import com.br.senac.EcommerceAPI.Models.URLImagensModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface URLImagensRepository extends JpaRepository<URLImagensModel, Long> {
}
