package com.br.senac.EcommerceAPI.Services;

import com.br.senac.EcommerceAPI.BlobsAzure.BlobStorageService;
import com.br.senac.EcommerceAPI.DTO.ProdutoDTO;
import com.br.senac.EcommerceAPI.Models.ProdutoModel;
import com.br.senac.EcommerceAPI.Models.URLImagensModel;
import com.br.senac.EcommerceAPI.Repositories.ProdutoRepository;
import com.br.senac.EcommerceAPI.Repositories.URLImagensRepository;
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

    @Autowired
    private URLImagensRepository urlImagensRepository;

    @Autowired
    private ProdutoModel produtoModel;

    public ResponseEntity<ProdutoDTO> criarProduto(ProdutoDTO dto,
                                                   MultipartFile arquivo1,
                                                   MultipartFile arquivo2) throws Exception {
        ProdutoModel prd = new ProdutoModel(dto);
        produtoModel = this.produtoRepository.save(prd);

        URLImagensModel urlImagensModel = new URLImagensModel();
        if (arquivo1 != null){
            String imageUrl = blobStorageService.uploadImage(arquivo1);
            urlImagensModel.setUrl(imageUrl);
            urlImagensModel.setId(produtoModel.getId());
            urlImagensRepository.save(urlImagensModel);
        }
        if (arquivo2 != null){
            String imageUrl = blobStorageService.uploadImage(arquivo2);
            urlImagensModel.setUrl(imageUrl);
            urlImagensModel.setId(produtoModel.getId());
            urlImagensRepository.save(urlImagensModel);
        }
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    public ResponseEntity processarArquivo(MultipartFile imagem) throws Exception {



        return new ResponseEntity(HttpStatus.CREATED);
    }

    public ResponseEntity<List<ProdutoModel>> listarTodos() {
        List<ProdutoModel> listaProduto = produtoRepository.findAll();
        return new ResponseEntity<>(listaProduto, HttpStatus.OK);
    }
}
