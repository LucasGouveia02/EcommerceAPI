package com.br.senac.EcommerceAPI.Controllers;

import com.br.senac.EcommerceAPI.DTO.ProdutoDto;
import com.br.senac.EcommerceAPI.Models.ProdutoModel;
import com.br.senac.EcommerceAPI.Repositories.ProdutoRepository;
import com.br.senac.EcommerceAPI.Services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/produtos")
    public ResponseEntity<List<ProdutoModel>> listaProdutos() {
        return produtoService.listarTodos();
    }

    @PostMapping("/cadastrarProd")
    public ResponseEntity<ProdutoDto> cadastrarProd(@RequestBody ProdutoDto dto) {
        return produtoService.criarProduto(dto);
    }
}
