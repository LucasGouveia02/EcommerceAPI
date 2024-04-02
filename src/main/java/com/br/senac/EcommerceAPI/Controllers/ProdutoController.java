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
            @RequestBody ProdutoDTO produtoDto,
            @RequestParam("imagem") MultipartFile arquivo,
            @RequestParam("imagem") MultipartFile arquivo2) throws Exception {
        return produtoService.criarProduto(produtoDto, arquivo, arquivo2);
    }
}
