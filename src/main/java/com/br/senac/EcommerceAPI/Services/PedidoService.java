package com.br.senac.EcommerceAPI.Services;

import com.br.senac.EcommerceAPI.DTO.CarrinhoDTO;
import com.br.senac.EcommerceAPI.DTO.PedidoDTO;
import com.br.senac.EcommerceAPI.Keys.CarrinhoProdutoKey;
import com.br.senac.EcommerceAPI.Keys.PedidoProdutoKey;
import com.br.senac.EcommerceAPI.Models.*;
import com.br.senac.EcommerceAPI.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoProdutoRepository pedidoProdutoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private CarrinhoService carrinhoService;

    public ResponseEntity<PedidoModel> gravarPedido (PedidoDTO dto) throws Exception {
        try {
            UsuarioModel usuario = usuarioRepository.findById(dto.getIdUsuario()).orElseThrow(
                    () -> new Exception("Usuário não encontrado!"));

            PedidoModel novoPedidoModel = new PedidoModel();
            novoPedidoModel.setUsuario_id(usuario);
            novoPedidoModel.setDataPedido(dto.getDataPedido());
            novoPedidoModel.setTipoEnvio(dto.getTipoEnvio());
            novoPedidoModel.setEnderecoEnvio(dto.getEnderecoEnvio());
            novoPedidoModel.setFormaPagamento(dto.getFormaPagamento());
            novoPedidoModel.setTotalPedido(dto.getTotalPedido());
            PedidoModel novoPedido = pedidoRepository.save(novoPedidoModel);

            CarrinhoModel carrinhoModel = carrinhoRepository.findById(dto.getIdUsuario()).orElseThrow(
                    () -> new Exception("Carrinho não encontrado com esse ID"));

            for(CarrinhoProdutoModel item : carrinhoModel.getCarrinhoProdutoModel()) {
                PedidoProdutoKey pedidoProdutoKey = new PedidoProdutoKey(novoPedido, item.getId().getProdutoId());

                PedidoProdutoModel pedidoProduto = new PedidoProdutoModel();
                pedidoProduto.setId(pedidoProdutoKey);
                pedidoProduto.setQtd(item.getQtd());
                pedidoProduto.setTamanho(item.getTamanho());
                PedidoProdutoModel pedido = pedidoProdutoRepository.save(pedidoProduto);
            }

            PedidoModel pedidoComProduto = pedidoRepository.findById(novoPedido.getId()).orElseThrow(
                    () -> new Exception("Pedido não encontrado com esse ID"));

            return new ResponseEntity<>(pedidoComProduto, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
