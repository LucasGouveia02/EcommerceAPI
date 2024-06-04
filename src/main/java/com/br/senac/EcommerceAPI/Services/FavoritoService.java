package com.br.senac.EcommerceAPI.Services;

import com.br.senac.EcommerceAPI.DTO.FavoritoDTO;
import com.br.senac.EcommerceAPI.Keys.ProdutoUsuarioKey;
import com.br.senac.EcommerceAPI.Models.FavoritoModel;
import com.br.senac.EcommerceAPI.Models.ProdutoModel;
import com.br.senac.EcommerceAPI.Models.UsuarioModel;
import com.br.senac.EcommerceAPI.Repositories.FavoritoRepository;
import com.br.senac.EcommerceAPI.Repositories.ProdutoRepository;
import com.br.senac.EcommerceAPI.Repositories.UsuarioRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoritoService {
    @Autowired
    private FavoritoRepository favoritoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ResponseEntity<FavoritoModel> criarFavorito(FavoritoDTO favoritoDTO) throws Exception {
        ProdutoUsuarioKey produtoUsuarioKey = new ProdutoUsuarioKey();
        ProdutoModel produtoModel = produtoRepository.findById(favoritoDTO.getProdutoId()).orElseThrow(
                () -> new Exception("Produto não encontrado com esse ID"));
        UsuarioModel usuario = usuarioRepository.findById(favoritoDTO.getUsuarioId()).orElseThrow(
                () -> new Exception("Usuário não encontrado!"));
        produtoUsuarioKey.setProdutoId(produtoModel);
        produtoUsuarioKey.setUsuarioId(usuario);
        FavoritoModel favoritoModel = new FavoritoModel(produtoUsuarioKey);
        favoritoRepository.save(favoritoModel);
        return new ResponseEntity<>(favoritoModel, HttpStatus.CREATED);
    }

    public ResponseEntity<List<ProdutoModel>> buscaFavorito(Long userId) throws Exception {
        UsuarioModel usuario = usuarioRepository.findById(userId).orElseThrow(
                () -> new Exception("Usuário não encontrado!"));
        List<ProdutoModel> listaFavoritos = favoritoRepository.findFavoritosByUserId(usuario);
        return new ResponseEntity<>(listaFavoritos, HttpStatus.OK);
    }
}
