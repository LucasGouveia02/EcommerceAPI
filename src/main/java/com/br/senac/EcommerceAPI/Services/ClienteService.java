package com.br.senac.EcommerceAPI.Services;

import com.br.senac.EcommerceAPI.DTO.ClienteDTO;
import com.br.senac.EcommerceAPI.Models.ClienteModel;
import com.br.senac.EcommerceAPI.Repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ResponseEntity<?> criarCliente(ClienteDTO dto) {
        ClienteModel cliente = new ClienteModel(dto);
        clienteRepository.save(cliente);
        return new ResponseEntity<>(cliente, HttpStatus.CREATED);
    }

    public ResponseEntity<List<ClienteModel>> listarClientes() {
        List<ClienteModel> listaCliente = clienteRepository.findAll();
        return new ResponseEntity<>(listaCliente, HttpStatus.OK);
    }

    public ResponseEntity<ClienteModel> buscaPorId(Long id) throws Exception{
        ClienteModel c = clienteRepository.findById(id).orElseThrow(
                () -> new Exception("Cliente não encontrado"));
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    public ResponseEntity<ClienteModel> buscaPorNome(String nome) throws Exception {
        ClienteModel c = clienteRepository.buscaPorNome(nome);
        if (c != null) {
            return new ResponseEntity<>(c, HttpStatus.OK);
        } else
            throw new Exception("Cliente não localizado");
    }

    public ResponseEntity<ClienteModel> buscaPorCPF(String cpf) throws Exception {
        ClienteModel c = clienteRepository.buscaPorCPF(cpf);
        if (c != null) {
            return new ResponseEntity<>(c, HttpStatus.OK);
        } else
            throw new Exception("Cliente não encontrado");
    }

    public ResponseEntity<ClienteModel> alterarCliente(Long id, ClienteDTO dto) throws Exception {
        ClienteModel cE = clienteRepository.findById(id).orElseThrow(
                () -> new Exception("Cliente não encontrado"));
        cE.setNome(dto.getNome());
        cE.setEmail(dto.getEmail());
        cE.setTelefone(dto.getTelefone());
        cE.setCpf(dto.getCpf());
        cE.setDataNascimento(dto.getDataNascimento());

        clienteRepository.save(cE);
        return new ResponseEntity<>(cE, HttpStatus.OK);
    }
}
