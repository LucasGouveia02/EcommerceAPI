package com.br.senac.EcommerceAPI.Controllers;

import com.br.senac.EcommerceAPI.DTO.ProdutoDto;
import com.br.senac.EcommerceAPI.Models.ProdutoModel;
import com.br.senac.EcommerceAPI.Repositories.ProdutoRepository;
import com.br.senac.EcommerceAPI.Services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
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
    public ResponseEntity<ProdutoDto> cadastrarProd(@RequestBody ProdutoDto produtoDto) throws Exception {
//        ProdutoDto produtoDto = new ProdutoDto(nome, categoria, imagem);
        return produtoService.criarProduto(produtoDto);
    }

    @PostMapping("/upload")
    public ResponseEntity uploadArquivo(@RequestParam("imagem") MultipartFile arquivo) throws Exception {
        return produtoService.processarArquivo(arquivo);
    }
}
