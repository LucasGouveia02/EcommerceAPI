package com.br.senac.EcommerceAPI.Controllers;

import com.br.senac.EcommerceAPI.DTO.EstoqueDTO;
import com.br.senac.EcommerceAPI.DTO.FavoritoDTO;
import com.br.senac.EcommerceAPI.DTO.ProdutoDTO;
import com.br.senac.EcommerceAPI.Models.FavoritoModel;
import com.br.senac.EcommerceAPI.Models.ProdutoModel;
import com.br.senac.EcommerceAPI.Models.TamanhoEstoqueModel;
import com.br.senac.EcommerceAPI.Services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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

    @PutMapping("/estoque/alterar")
    public ResponseEntity<List<TamanhoEstoqueModel>> atualizarEstoque(@RequestBody EstoqueDTO estoqueDTO) throws Exception {
        return produtoService.atualizarEstoque(estoqueDTO);
    }

    @PutMapping("/desativar/{id}")
    public ResponseEntity<ProdutoModel> atualizarEstoque(@PathVariable("id") Long id) throws Exception {
        return produtoService.desativarProduto(id);
    }

    @PutMapping("/produto/alterar")
    public ResponseEntity<ProdutoModel> atualizarProduto(
            @RequestParam String produto,
            @RequestParam("imagem1") MultipartFile imagem,
            @RequestParam("imagem2") MultipartFile imagem2,
            @RequestParam("imagem3") MultipartFile imagem3,
            @RequestParam("imagem4") MultipartFile imagem4) throws Exception {
        return produtoService.atualizarProduto(produto, imagem, imagem2, imagem3, imagem4);
    }

    @GetMapping("/download")
    @ResponseBody
    public ResponseEntity<ByteArrayResource> obterImagem(@RequestParam("url") String imageUrl) throws IOException {
        // Baixar a imagem
        URL url = new URL(imageUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        InputStream inputStream = connection.getInputStream();
        byte[] bytes = inputStream.readAllBytes();
        inputStream.close();

        // Configurar os cabeçalhos da resposta
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(bytes.length);

        // Retornar a imagem como recurso
        ByteArrayResource resource = new ByteArrayResource(bytes);
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
}
