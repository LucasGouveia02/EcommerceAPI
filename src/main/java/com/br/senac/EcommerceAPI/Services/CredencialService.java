package com.br.senac.EcommerceAPI.Services;

import com.br.senac.EcommerceAPI.DTO.CredencialDTO;
import com.br.senac.EcommerceAPI.Models.CredencialModel;
import com.br.senac.EcommerceAPI.Repositories.CredencialRepository;
import com.br.senac.EcommerceAPI.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CredencialService {
    //injetar dependecia - instanciar
    @Autowired
    private CredencialRepository credencialRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private Boolean autenticado;

    public ResponseEntity<Boolean> autenticarLogin(CredencialDTO credencialDTO){
        CredencialModel login = credencialRepository.findByUsuario(credencialDTO.getIdUsuario());

        if(credencialDTO.getEmail().equalsIgnoreCase(login.getEmail()) && credencialDTO.getSenha().equalsIgnoreCase(login.getSenha())){
            autenticado = true;
            return new ResponseEntity<>(autenticado, HttpStatus.OK);
        }
        else{
            autenticado = false;
            return new ResponseEntity<>(autenticado, HttpStatus.FORBIDDEN);
        }

    }

}
