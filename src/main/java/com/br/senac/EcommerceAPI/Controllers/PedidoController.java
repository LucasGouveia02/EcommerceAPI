package com.br.senac.EcommerceAPI.Controllers;

import com.br.senac.EcommerceAPI.DTO.CarrinhoDTO;
import com.br.senac.EcommerceAPI.DTO.PedidoDTO;
import com.br.senac.EcommerceAPI.Models.CarrinhoProdutoModel;
import com.br.senac.EcommerceAPI.Models.PedidoModel;
import com.br.senac.EcommerceAPI.Services.CarrinhoService;
import com.br.senac.EcommerceAPI.Services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
