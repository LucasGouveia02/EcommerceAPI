package com.br.senac.EcommerceAPI.Controllers;

import com.br.senac.EcommerceAPI.Models.ProdutoModel;
import com.br.senac.EcommerceAPI.Repositories.IProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CadProdController {
    @Autowired
    private IProduto iProduto;

    @GetMapping("/produtos")
    public List<ProdutoModel> listaProdutos() {
        return (List<ProdutoModel>) iProduto.findAll();
    }

    @PostMapping("/cadastrarProd")
    public void cadastrarProd() {
    }
}
