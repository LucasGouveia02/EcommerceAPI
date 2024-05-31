package com.br.senac.EcommerceAPI.Controllers;

import com.br.senac.EcommerceAPI.DTO.CredencialDTO;
import com.br.senac.EcommerceAPI.Models.EnderecoModel;
import com.br.senac.EcommerceAPI.Services.CredencialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/login")
public class CredencialController {

    @Autowired
    private CredencialService credencialService;

    private final CredencialService credencialServiceAutenticado = new CredencialService();

    @PostMapping
    public ResponseEntity<Boolean> autenticarLogin(@RequestBody CredencialDTO credencialDTO){
        credencialServiceAutenticado.setAutenticado(credencialService.verificarAutenticacao(credencialDTO));
        credencialServiceAutenticado.setCredencialModel(credencialService.retornaUsuario(credencialDTO));
        return credencialService.autenticarLogin(credencialDTO);
    }

    @GetMapping("/autenticacao")
    public ResponseEntity<CredencialService> verificarAutenticacao() {
        return new ResponseEntity<>(credencialServiceAutenticado, HttpStatus.OK);
    }

    @GetMapping("endereco")
    public ResponseEntity<List<EnderecoModel>> retornoEnderecoUsuario(@RequestParam("id") Long id) throws Exception {
        return credencialService.buscarEnderecoUsuarioLogado(id);
    }

    @PostMapping("/logout")
    public ResponseEntity<Boolean> logout() {
        return credencialService.logout();
    }
}
