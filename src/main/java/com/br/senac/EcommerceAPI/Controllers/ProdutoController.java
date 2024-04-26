package com.br.senac.EcommerceAPI.Controllers;

import com.br.senac.EcommerceAPI.DTO.ProdutoDTO;
import com.br.senac.EcommerceAPI.Models.ProdutoModel;
import com.br.senac.EcommerceAPI.Services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/produtos")
    public ResponseEntity<List<ProdutoModel>> listaProdutos() {
        return produtoService.listarTodos();
    }

    @PostMapping("/cadastrarProd")
    public ResponseEntity<ProdutoDTO> cadastrarProd(
            @RequestParam String produto,
            @RequestParam("imagem") MultipartFile imagem,
            @RequestParam("imagem2") MultipartFile imagem2,
            @RequestParam("imagem3") MultipartFile imagem3,
            @RequestParam("imagem4") MultipartFile imagem4) throws Exception {
        return produtoService.criarProduto(produto, imagem, imagem2, imagem3, imagem4);
    }
}
