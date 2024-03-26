package com.br.senac.EcommerceAPI.Services;

import com.br.senac.EcommerceAPI.DTO.EnderecoDTO;
import com.br.senac.EcommerceAPI.Models.EnderecoModel;
import com.br.senac.EcommerceAPI.Repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public ResponseEntity<EnderecoModel> criarEndereco(EnderecoDTO dto) {
        EnderecoModel endereco = new EnderecoModel(dto);
        enderecoRepository.save(endereco);
        return new ResponseEntity<>(endereco, HttpStatus.CREATED);
    }

    public ResponseEntity<List<EnderecoModel>> listarEnderecos() {
        List<EnderecoModel> listaEndereco = enderecoRepository.findAll();
        return new ResponseEntity<>(listaEndereco, HttpStatus.OK);
    }

    public ResponseEntity<EnderecoModel> atualizarEndereco(Long id, EnderecoDTO dto) throws Exception {
        EnderecoModel eE = enderecoRepository.findById(id).orElseThrow(
                () -> new Exception("Endereço não encontrado"));
        eE.setCep(dto.getCep());
        eE.setLogradouro(dto.getLogradouro());
        eE.setBairro(dto.getBairro());
        eE.setNumero(dto.getNumero());
        eE.setCidade(dto.getCidade());
        eE.setUf(dto.getUf());

        enderecoRepository.save(eE);

        return new ResponseEntity<>(eE, HttpStatus.OK);
    }
}
