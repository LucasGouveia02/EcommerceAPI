package com.br.senac.EcommerceAPI.Services;

import com.br.senac.EcommerceAPI.DTO.CredencialDTO;
import com.br.senac.EcommerceAPI.Models.*;
import com.br.senac.EcommerceAPI.Repositories.CredencialRepository;
import com.br.senac.EcommerceAPI.Repositories.EnderecoRepository;
import com.br.senac.EcommerceAPI.Repositories.UsuarioRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Getter @Setter
@Service
public class CredencialService {
    //injetar dependecia - instanciar
    @Autowired
    private CredencialRepository credencialRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    private Boolean autenticado = false;

    private CredencialModel credencialModel;

    public ResponseEntity<Boolean> autenticarLogin(CredencialDTO credencialDTO) {
        CredencialModel login = credencialRepository.findByUsuario(credencialDTO.getEmail());
        try {
            if (login != null) {
                if (credencialDTO.getEmail().equalsIgnoreCase(login.getEmail()) && credencialDTO.getSenha().equalsIgnoreCase(login.getSenha())) {
                    autenticado = true;
                    return new ResponseEntity<>(autenticado, HttpStatus.OK);
                } else {
                    autenticado = false;
                    return new ResponseEntity<>(autenticado, HttpStatus.FORBIDDEN);
                }
            } else {
                throw new Exception();//tratando uma excessão
            }

        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
        }//retorna o usuário não encontrado
    }

    public Boolean verificarAutenticacao(CredencialDTO credencialDTO) {
        CredencialModel login = credencialRepository.findByUsuario(credencialDTO.getEmail());
        if (login != null) {
            if (credencialDTO.getEmail().equalsIgnoreCase(login.getEmail()) && credencialDTO.getSenha().equalsIgnoreCase(login.getSenha())) {
                return autenticado = true;
            }
        }
        return false;
    }

    public CredencialModel retornaUsuario(CredencialDTO credencialDTO) {
        CredencialModel login = credencialRepository.findByUsuario(credencialDTO.getEmail());
        if (login != null) {
            if (credencialDTO.getEmail().equalsIgnoreCase(login.getEmail()) && credencialDTO.getSenha().equalsIgnoreCase(login.getSenha())) {
                return login;
            }
        }
        return null;
    }

    public ResponseEntity<EnderecoModel> buscarEnderecoUsuarioLogado(Long id) throws Exception {
        try {
            UsuarioModel usuarioModel = new UsuarioModel();
            usuarioModel.setId(id);

        EnderecoUsuario enderecoRetornado = enderecoRepository.retornoEnderecoPorUsuario(usuarioModel);
            EnderecoModel enderecoModel = enderecoRetornado.getId().getEnderecoId();
            return new ResponseEntity<>(enderecoModel, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Endereço não encontrado!");
        }
        return null;
    }
}
