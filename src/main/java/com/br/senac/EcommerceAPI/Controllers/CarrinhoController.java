package com.br.senac.EcommerceAPI.Controllers;

import com.br.senac.EcommerceAPI.DTO.CarrinhoDTO;
import com.br.senac.EcommerceAPI.Models.CarrinhoModel;
import com.br.senac.EcommerceAPI.Models.CarrinhoProdutoModel;
import com.br.senac.EcommerceAPI.Models.ProdutoModel;
import com.br.senac.EcommerceAPI.Services.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/carrinho")
public class CarrinhoController {

    @Autowired
    private CarrinhoService carrinhoService;

    @PostMapping("/adicionar")
    public ResponseEntity<CarrinhoProdutoModel> adicionarItemCarrinho(@RequestBody CarrinhoDTO dto) throws Exception {
        return carrinhoService.adicionarCarrinho(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarrinhoModel> listaCarrinho(@PathVariable (value = "id") Long id) throws Exception {
        return carrinhoService.listarCarrinho(id);
    }
}
