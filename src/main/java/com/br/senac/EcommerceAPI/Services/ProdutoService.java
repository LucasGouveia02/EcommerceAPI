package com.br.senac.EcommerceAPI.Services;

import com.br.senac.EcommerceAPI.BlobsAzure.BlobStorageService;
import com.br.senac.EcommerceAPI.DTO.ProdutoDto;
import com.br.senac.EcommerceAPI.Models.ProdutoModel;
import com.br.senac.EcommerceAPI.Repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private BlobStorageService blobStorageService;

    public ResponseEntity<ProdutoDto> criarProduto(ProdutoDto dto) throws Exception {
        ProdutoModel prd = new ProdutoModel(dto);
        this.produtoRepository.save(prd);
        ProdutoDto prdDto = new ProdutoDto(prd.getNome(), prd.getCategoria());
        return new ResponseEntity<>(prdDto, HttpStatus.CREATED);
    }

    public ResponseEntity processarArquivo(MultipartFile imagem) throws Exception {
        String imageUrl = blobStorageService.uploadImage(imagem);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    public ResponseEntity<List<ProdutoModel>> listarTodos() {
        List<ProdutoModel> listaProduto = produtoRepository.findAll();
        return new ResponseEntity<>(listaProduto, HttpStatus.OK);
    }
}
