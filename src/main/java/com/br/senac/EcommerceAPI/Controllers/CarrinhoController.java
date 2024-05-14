package com.br.senac.EcommerceAPI.Controllers;

import com.br.senac.EcommerceAPI.DTO.CadastroUsuarioDTO;
import com.br.senac.EcommerceAPI.Models.UsuarioModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/carrinho")
public class CarrinhoController {

//    @PostMapping("/criar")
//    public ResponseEntity<UsuarioModel> criarCliente(@RequestBody CadastroUsuarioDTO dto) throws ParseException {
//        return usuarioService.criarUsuario(dto);
//    }
}
