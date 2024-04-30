package com.br.senac.EcommerceAPI.Services;

import com.br.senac.EcommerceAPI.DTO.CredencialDTO;
import com.br.senac.EcommerceAPI.DTO.EnderecoDTO;
import com.br.senac.EcommerceAPI.Models.CredencialModel;
import com.br.senac.EcommerceAPI.Models.EnderecoModel;
import com.br.senac.EcommerceAPI.Models.UsuarioModel;
import com.br.senac.EcommerceAPI.Repositories.CredencialRepository;
import com.br.senac.EcommerceAPI.Repositories.EnderecoRepository;
import com.br.senac.EcommerceAPI.Repositories.UsuarioRepository;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
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



//    public ResponseEntity<EnderecoModel> buscarEnderecoUsuario(CredencialDTO credencialDTO, EnderecoDTO dto) {
//        ResponseEntity<Boolean> autenticacao = autenticarLogin(credencialDTO);
//        if (autenticacao == true) {
//            UsuarioModel usuarioModel = usuarioRepository.findById(credencialDTO.getIdUsuario()).orElse(null);
//            if (usuarioModel != null && usuarioModel.getId() == credencialDTO.getIdUsuario()) {
//                EnderecoModel endereco = enderecoRepository.findByUsuarioId(credencialDTO.getIdUsuario());
//                if (endereco != null) {
//                    StringBuilder enderecoCompleto = new StringBuilder();
//                    enderecoCompleto.append(endereco.getLogradouro());
//                    enderecoCompleto.append(", ");
//                    enderecoCompleto.append(endereco.getNumero());
//                    enderecoCompleto.append(" - ");
//                    enderecoCompleto.append(endereco.getBairro());
//                    return new ResponseEntity<>(enderecoCompleto, HttpStatus.OK);
//                } else {
//                    return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Endereço não encontrado
//                }
//            } else {
//                return new ResponseEntity<>(HttpStatus.FORBIDDEN); // Usuário não autorizado
//            }
//        } else {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // Falha na autenticação
//        }
//    }
//
}
