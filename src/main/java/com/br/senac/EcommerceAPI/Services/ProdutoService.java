package com.br.senac.EcommerceAPI.Services;

import com.br.senac.EcommerceAPI.BlobsAzure.BlobStorageService;
import com.br.senac.EcommerceAPI.DTO.EstoqueDTO;
import com.br.senac.EcommerceAPI.DTO.ProdutoAllInfoDTO;
import com.br.senac.EcommerceAPI.DTO.ProdutoDTO;
import com.br.senac.EcommerceAPI.Models.ProdutoModel;
import com.br.senac.EcommerceAPI.Models.TamanhoEstoqueModel;
import com.br.senac.EcommerceAPI.Models.URLImagensModel;
import com.br.senac.EcommerceAPI.Models.UsuarioModel;
import com.br.senac.EcommerceAPI.Repositories.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    private TamanhoEstoqueRepository tamanhoEstoqueRepository;

    @Autowired
    private CarrinhoProdutoRepository carrinhoProdutoRepository;

    @Autowired
    private FavoritoRepository favoritoRepository;

    public ResponseEntity<ProdutoDTO> criarProduto(String produto,
                                                   MultipartFile imagem,
                                                   MultipartFile imagem2,
                                                   MultipartFile imagem3,
                                                   MultipartFile imagem4) throws Exception {
        // Converte a string JSON em um objeto ProdutoDTO
        ObjectMapper mapper = new ObjectMapper();
        ProdutoDTO produtoDTO = mapper.readValue(produto, ProdutoDTO.class);
        ProdutoModel prd = new ProdutoModel(produtoDTO);
        prd.setAtivado(true);
        produtoModel = this.produtoRepository.save(prd);

        // Salvando os TamanhoEstoqueModel associados ao ProdutoModel
        for (TamanhoEstoqueModel tamanhoEstoque : produtoDTO.getTamanhosEstoque()) {
            // Definindo o ProdutoModel como proprietário do relacionamento
            tamanhoEstoque.setProdutoId(produtoModel);

            // Salvando o TamanhoEstoqueModel no banco de dados
            tamanhoEstoqueRepository.save(tamanhoEstoque);
        }
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

    public ResponseEntity<List<ProdutoModel>> listarPorCategoria(String category) {
        List<ProdutoModel> listaProduto = produtoRepository.findByCategoryName(category);
        return new ResponseEntity<>(listaProduto, HttpStatus.OK);
    }

    public ResponseEntity<ProdutoModel> buscaPorId(Long id) throws Exception{
        ProdutoModel c = produtoRepository.findById(id).orElseThrow(
                () -> new Exception("Produto não encontrado"));
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    public ResponseEntity<List<TamanhoEstoqueModel>> atualizarEstoque(EstoqueDTO estoqueDTO) throws Exception {
        ProdutoModel produtoId = produtoRepository.findById(estoqueDTO.getProductId()).orElseThrow(
                () -> new Exception("Produto não encontrado"));
        tamanhoEstoqueRepository.limparTamanhoEstoque(produtoId);

        for (TamanhoEstoqueModel tamanhoEstoque : estoqueDTO.getTamanhosEstoque()) {
            // Definindo o ProdutoModel como proprietário do relacionamento
            tamanhoEstoque.setProdutoId(produtoId);

            // Salvando o TamanhoEstoqueModel no banco de dados
            tamanhoEstoqueRepository.save(tamanhoEstoque);
        }
        return new ResponseEntity<>(tamanhoEstoqueRepository.buscarEstoques(produtoId), HttpStatus.OK);
    }

    public ResponseEntity<ProdutoModel> atualizarProduto(String produto,
                                                         MultipartFile imagem,
                                                         MultipartFile imagem2,
                                                         MultipartFile imagem3,
                                                         MultipartFile imagem4) throws Exception {
        // Converte a string JSON em um objeto ProdutoDTO
        ObjectMapper mapper = new ObjectMapper();
        ProdutoDTO produtoDTO = mapper.readValue(produto, ProdutoDTO.class);

        ProdutoModel produtoBanco = produtoRepository.findById(produtoDTO.getId()).orElseThrow(
                () -> new Exception("Produto não encontrado"));
        produtoBanco.setNome(produtoDTO.getNome());
        produtoBanco.setCategoria(produtoDTO.getCategoria());
        produtoBanco.setPreco(produtoDTO.getPreco());
        produtoBanco.setMarca(produtoDTO.getMarca());
        produtoBanco.setDescricao(produtoDTO.getDescricao());
        produtoBanco.setUnidade(produtoDTO.getUnidade());
        produtoRepository.save(produtoBanco);

        tamanhoEstoqueRepository.limparTamanhoEstoque(produtoBanco);
        for (TamanhoEstoqueModel tamanhoEstoque : produtoDTO.getTamanhosEstoque()) {
            tamanhoEstoque.setProdutoId(produtoBanco);

            tamanhoEstoqueRepository.save(tamanhoEstoque);
        }

        urlImagensRepository.limparURLs(produtoBanco);

        URLImagensModel urlImagensModel = new URLImagensModel();
        URLImagensModel urlImagensModel2 = new URLImagensModel();
        URLImagensModel urlImagensModel3 = new URLImagensModel();
        URLImagensModel urlImagensModel4 = new URLImagensModel();

        if (imagem != null){
            String imageUrl = blobStorageService.uploadImage(imagem);
            urlImagensModel.setUrl(imageUrl);
            urlImagensModel.setProdutoId(produtoBanco);
            urlImagensRepository.save(urlImagensModel);
        }
        if (imagem2 != null){
            String imageUrl = blobStorageService.uploadImage(imagem2);
            urlImagensModel2.setUrl(imageUrl);
            urlImagensModel2.setProdutoId(produtoBanco);
            urlImagensRepository.save(urlImagensModel2);
        }
        if (imagem3 != null){
            String imageUrl = blobStorageService.uploadImage(imagem3);
            urlImagensModel3.setUrl(imageUrl);
            urlImagensModel3.setProdutoId(produtoBanco);
            urlImagensRepository.save(urlImagensModel3);
        }
        if (imagem4 != null){
            String imageUrl = blobStorageService.uploadImage(imagem4);
            urlImagensModel4.setUrl(imageUrl);
            urlImagensModel4.setProdutoId(produtoBanco);
            urlImagensRepository.save(urlImagensModel4);
        }

        ProdutoModel produtoAlterado = produtoRepository.findById(produtoDTO.getId()).orElseThrow(
                () -> new Exception("Produto não encontrado"));
        return new ResponseEntity<>(produtoAlterado, HttpStatus.CREATED);
    }

    public ResponseEntity<ProdutoModel> desativarProduto(Long id) throws Exception {
        ProdutoModel produto = produtoRepository.findById(id).orElseThrow(
                () -> new Exception("Produto não encontrado"));
        carrinhoProdutoRepository.limparProdutoCarrinhos(produto);
        favoritoRepository.limparProdutoFavoritos(produto);
        produto.setAtivado(false);
        ProdutoModel produtoDesativado = produtoRepository.save(produto);
        return new ResponseEntity<>(produtoDesativado, HttpStatus.OK);
    }
}
