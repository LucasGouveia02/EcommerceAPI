package com.br.senac.EcommerceAPI.Controllers;

import com.br.senac.EcommerceAPI.DTO.PedidoDTO;
import com.br.senac.EcommerceAPI.Models.PedidoModel;
import com.br.senac.EcommerceAPI.Models.PedidoProdutoModel;
import com.br.senac.EcommerceAPI.Services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/gravar")
    public ResponseEntity<PedidoModel> gravarPedido (@RequestBody PedidoDTO dto) throws Exception {
        return pedidoService.gravarPedido(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<PedidoModel>> listaPedidos (@PathVariable (value = "id") Long id) throws Exception {
        return pedidoService.listarPedidosUsuario(id);

    }
    @GetMapping("/listar")
    public ResponseEntity<List<PedidoProdutoModel>> listarItens (@RequestParam (value = "id") Long id) throws Exception {
        return pedidoService.listarItens(id);
    }
}
