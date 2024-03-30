package com.br.senac.EcommerceAPI.Controllers;

import com.br.senac.EcommerceAPI.DTO.EnderecoRequestDTO;
import com.br.senac.EcommerceAPI.Services.EnderecoService;
import com.br.senac.EcommerceAPI.Services.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private ViaCepService viaCepService;

    @PostMapping("/criar")
    public ResponseEntity<String> criarEndereco(@RequestBody EnderecoRequestDTO requestDTO) throws Exception {
        var response = enderecoService.criarEndereco(requestDTO.getCep(), requestDTO.getNumero());
        return new ResponseEntity<String>(String.valueOf(response), HttpStatus.CREATED);

    }
}
