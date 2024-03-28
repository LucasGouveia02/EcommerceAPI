package com.br.senac.EcommerceAPI.Controllers;

import com.br.senac.EcommerceAPI.DTO.UsuarioDTO;
import com.br.senac.EcommerceAPI.Models.UsuarioModel;
import com.br.senac.EcommerceAPI.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/criar")
    public ResponseEntity<UsuarioModel> criarCliente(@RequestBody UsuarioDTO dto) throws ParseException {
        return usuarioService.criarUsuario(dto);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioModel>> listarClientes() {
        return usuarioService.listarUsuarios();
    }

    @GetMapping("{id}")
    public ResponseEntity<UsuarioModel> buscarPorId(@PathVariable (value = "id")
                                                        Long id) throws Exception {
        return usuarioService.buscaPorId(id);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<UsuarioModel> buscaPorNome(@PathVariable (value = "nome")
                                                         String nome) throws Exception {
        return usuarioService.buscaPorNome(nome);
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<UsuarioModel> buscarPorCPF(@PathVariable (value = "cpf")
                                                         String cpf) throws Exception {
        return usuarioService.buscaPorCPF(cpf);
    }

    @PutMapping("{id}")
    public ResponseEntity<UsuarioModel> atualizarUsuario(@PathVariable (value = "id") Long id,
                                                         @RequestBody UsuarioDTO usuario) throws Exception {
        return usuarioService.atualizarUsuario(id, usuario);
    }
}
