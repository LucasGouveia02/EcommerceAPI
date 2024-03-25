package com.br.senac.EcommerceAPI.Services;

import com.br.senac.EcommerceAPI.BlobsAzure.BlobStorageService;
import com.br.senac.EcommerceAPI.DTO.ProdutoDTO;
import com.br.senac.EcommerceAPI.Models.ProdutoModel;
import com.br.senac.EcommerceAPI.Repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private BlobStorageService blobStorageService;

    public ResponseEntity<ProdutoDTO> criarProduto(ProdutoDTO dto) throws Exception {
        ProdutoModel prd = new ProdutoModel(dto);
        this.produtoRepository.save(prd);
        ProdutoDTO prdDto = new ProdutoDTO(prd.getNome(), prd.getCategoria());
        String imageUrl = blobStorageService.uploadImage(dto.getImagem());
        return new ResponseEntity<>(prdDto, HttpStatus.CREATED);
    }

    public ResponseEntity<List<ProdutoModel>> listarTodos() {
        List<ProdutoModel> listaProduto = produtoRepository.findAll();
        return new ResponseEntity<>(listaProduto, HttpStatus.OK);
    }
}
