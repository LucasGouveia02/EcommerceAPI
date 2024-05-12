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
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoModel>> listaProdutos(@RequestParam("category") String category) {
        return produtoService.listarPorCategoria(category);
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

    @GetMapping("{id}")
    public ResponseEntity<ProdutoModel> listarProdID(@PathVariable("id") Long id) throws Exception {
        return produtoService.buscaPorId(id);
    }
}
