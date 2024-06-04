package com.br.senac.EcommerceAPI.Controllers;

import com.br.senac.EcommerceAPI.DTO.CarrinhoProdutoDTO;
import com.br.senac.EcommerceAPI.DTO.FavoritoDTO;
import com.br.senac.EcommerceAPI.Models.FavoritoModel;
import com.br.senac.EcommerceAPI.Models.ProdutoModel;
import com.br.senac.EcommerceAPI.Services.FavoritoService;
import com.br.senac.EcommerceAPI.Services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/favoritos")
public class FavoritoController {
    @Autowired
    private FavoritoService favoritoService;

    @PostMapping("/cadastrar")
    public ResponseEntity<FavoritoModel> cadastrarFavorito(@RequestBody FavoritoDTO favoritoDTO) throws Exception {
        return favoritoService.criarFavorito(favoritoDTO);
    }
    @GetMapping("/buscar")
    public ResponseEntity<List<ProdutoModel>> listarFavorito(@RequestParam("id") Long id) throws Exception {
        return favoritoService.buscaFavorito(id);
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<?> deleteFavorito(@RequestBody FavoritoDTO favoritoDTO) throws Exception {
        return favoritoService.excluirFavorito(favoritoDTO);
    }
}
