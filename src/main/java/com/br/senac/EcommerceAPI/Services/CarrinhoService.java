package com.br.senac.EcommerceAPI.Services;

import com.br.senac.EcommerceAPI.DTO.CarrinhoDTO;
import com.br.senac.EcommerceAPI.Keys.CarrinhoProdutoKey;
import com.br.senac.EcommerceAPI.Models.*;
import com.br.senac.EcommerceAPI.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CarrinhoService {

    @Autowired
    private CarrinhoProdutoRepository carrinhoProdutoRepository;

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ResponseEntity<CarrinhoProdutoModel> adicionarCarrinho(CarrinhoDTO dto) throws Exception {
        try {
            ProdutoModel produtoModel = produtoRepository.findById(dto.getIdProduto()).orElseThrow(
                    () -> new Exception("Produto não encontrado com esse ID"));

            CarrinhoModel carrinhoModel = carrinhoRepository.findById(dto.getIdCarrinho()).orElseThrow(
                    () -> new Exception("Carrinho não encontrado com esse ID"));

            CarrinhoProdutoKey carrinhoProdutoKey = new CarrinhoProdutoKey(produtoModel, carrinhoModel);

            CarrinhoProdutoModel carrinhoProdutoModel = new CarrinhoProdutoModel();
            carrinhoProdutoModel.setId(carrinhoProdutoKey);
            carrinhoProdutoModel.setQtd(1);
            carrinhoProdutoModel.setTamanho(dto.getTamanho());
            carrinhoProdutoRepository.save(carrinhoProdutoModel);

            carrinhoModel.setQuantidadeItens(carrinhoModel.getCarrinhoProdutoModel().size());
            carrinhoRepository.save(carrinhoModel);
            return new ResponseEntity<>(carrinhoProdutoModel, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<CarrinhoModel> listarCarrinho(Long id) throws Exception {
        CarrinhoModel carrinhoModel = carrinhoRepository.findById(id).orElseThrow(
                () -> new Exception("Carrinho não encontrado com esse ID"));
        return new ResponseEntity<>(carrinhoModel, HttpStatus.OK);
    }
}
