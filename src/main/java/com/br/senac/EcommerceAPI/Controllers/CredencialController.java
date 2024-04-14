package com.br.senac.EcommerceAPI.Controllers;

import com.br.senac.EcommerceAPI.DTO.CredencialDTO;
import com.br.senac.EcommerceAPI.Services.CredencialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/login")
public class CredencialController {

    @Autowired
    private CredencialService credencialService;

    @GetMapping
    public ResponseEntity<Boolean> autenticarLogin(CredencialDTO credencialDTO){
        return credencialService.autenticarLogin(credencialDTO);
    }
}
