package com.br.senac.EcommerceAPI.Controllers;

import com.br.senac.EcommerceAPI.DTO.CredencialDTO;
import com.br.senac.EcommerceAPI.Services.CredencialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return credencialService.autenticarLogin(credencialDTO);
    }

    @GetMapping("/autenticacao")
    public ResponseEntity<CredencialService> verificarAutenticacao() {
        return new ResponseEntity<>(credencialServiceAutenticado, HttpStatus.OK);
    }
}
