package com.br.senac.EcommerceAPI.Services;

import com.br.senac.EcommerceAPI.BlobsAzure.BlobStorageService;
import com.br.senac.EcommerceAPI.DTO.ProdutoAllInfoDTO;
import com.br.senac.EcommerceAPI.DTO.ProdutoDTO;
import com.br.senac.EcommerceAPI.Models.ProdutoModel;
import com.br.senac.EcommerceAPI.Models.URLImagensModel;
import com.br.senac.EcommerceAPI.Repositories.ProdutoRepository;
import com.br.senac.EcommerceAPI.Repositories.URLImagensRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.util.ArrayList;
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

    public ResponseEntity<ProdutoDTO> criarProduto(String produto,
                                                   MultipartFile imagem,
                                                   MultipartFile imagem2,
                                                   MultipartFile imagem3,
                                                   MultipartFile imagem4) throws Exception {
        // Converte a string JSON em um objeto ProdutoDTO
        ObjectMapper mapper = new ObjectMapper();
        ProdutoDTO produtoDTO = mapper.readValue(produto, ProdutoDTO.class);
        ProdutoModel prd = new ProdutoModel(produtoDTO);
        produtoModel = this.produtoRepository.save(prd);

        URLImagensModel urlImagensModel = new URLImagensModel();
        URLImagensModel urlImagensModel2 = new URLImagensModel();
        URLImagensModel urlImagensModel3 = new URLImagensModel();
        URLImagensModel urlImagensModel4 = new URLImagensModel();

        if (imagem != null){
            String imageUrl = blobStorageService.uploadImage(imagem);
            urlImagensModel.setUrl(imageUrl);
            urlImagensModel.setProdutoId(produtoModel);
            urlImagensRepository.save(urlImagensModel);
        }
        if (imagem2 != null){
            String imageUrl = blobStorageService.uploadImage(imagem2);
            urlImagensModel2.setUrl(imageUrl);
            urlImagensModel2.setProdutoId(produtoModel);
            urlImagensRepository.save(urlImagensModel2);
        }
        if (imagem3 != null){
            String imageUrl = blobStorageService.uploadImage(imagem3);
            urlImagensModel3.setUrl(imageUrl);
            urlImagensModel3.setProdutoId(produtoModel);
            urlImagensRepository.save(urlImagensModel3);
        }
        if (imagem4 != null){
            String imageUrl = blobStorageService.uploadImage(imagem4);
            urlImagensModel4.setUrl(imageUrl);
            urlImagensModel4.setProdutoId(produtoModel);
            urlImagensRepository.save(urlImagensModel4);
        }
        return new ResponseEntity<>(produtoDTO, HttpStatus.CREATED);
    }

    public ResponseEntity<List<ProdutoAllInfoDTO>> listarPorCategoria(String category) {
        List<ProdutoAllInfoDTO> listaAllProducts = new ArrayList<>();
        List<String> listaUrl = new ArrayList<>();
        List<ProdutoModel> listaProduto = produtoRepository.findByCategoryName(category);
        ProdutoAllInfoDTO produtoAllInfoDTO;
        for (ProdutoModel produtoModel : listaProduto) {
            List<URLImagensModel> listaUrlImagesModel = urlImagensRepository.findByProductId(produtoModel);
            produtoAllInfoDTO = new ProdutoAllInfoDTO(produtoModel.getNome(),
                    produtoModel.getPreco(), produtoModel.getCategoria(), produtoModel.getMarca(),
                    produtoModel.getTamanho(), produtoModel.getUnidade(), produtoModel.getEstoque(),
                    produtoModel.getDescricao());
            for (URLImagensModel urlImagensModel : listaUrlImagesModel) {
                listaUrl.add(urlImagensModel.getUrl());
            }
            produtoAllInfoDTO.setUrl(listaUrl);
            listaUrl = new ArrayList<>();
            listaAllProducts.add(produtoAllInfoDTO);
        }
        return new ResponseEntity<>(listaAllProducts, HttpStatus.OK);
    }
}
