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

import java.util.List;

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
    private CarrinhoProdutoRepository carrinhoProdutoRepository;

    @Autowired
    private CarrinhoService carrinhoService;

    public ResponseEntity<PedidoModel> gravarPedido(PedidoDTO dto) throws Exception {
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

            for (CarrinhoProdutoModel item : carrinhoModel.getCarrinhoProdutoModel()) {
                PedidoProdutoKey pedidoProdutoKey = new PedidoProdutoKey(novoPedido, item.getId().getProdutoId());

                PedidoProdutoModel pedidoProduto = new PedidoProdutoModel();
                pedidoProduto.setId(pedidoProdutoKey);
                pedidoProduto.setQtd(item.getQtd());
                pedidoProduto.setTamanho(item.getTamanho());
                pedidoProdutoRepository.save(pedidoProduto);
            }

            // zerar os produtos do carrinho
            carrinhoProdutoRepository.limparCarrinho(carrinhoModel);
            carrinhoModel.setQuantidadeItens(0);
            carrinhoRepository.save(carrinhoModel);

            return new ResponseEntity<>(novoPedido, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<PedidoModel>> listarPedidosUsuario(Long id) throws Exception {
        UsuarioModel usuario = usuarioRepository.findById(id).orElseThrow(
                () -> new Exception("Usuário não encontrado com esse ID"));

        List<PedidoModel> pedidos = usuario.getPedidos();

        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

    public ResponseEntity<List<PedidoProdutoModel>> listarItens(Long id) throws Exception {
        try {
            PedidoModel p = new PedidoModel();
            p.setId(id);
            List<PedidoProdutoModel> pedidoProdutoModels = pedidoRepository.listapedidos(p);

            if (pedidoProdutoModels != null && !pedidoProdutoModels.isEmpty()) {
                return new ResponseEntity<>(pedidoProdutoModels, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar itens do pedido: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
