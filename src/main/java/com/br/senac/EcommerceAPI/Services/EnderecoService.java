package com.br.senac.EcommerceAPI.Services;

import com.br.senac.EcommerceAPI.DTO.EnderecoDTO;
import com.br.senac.EcommerceAPI.DTO.NovoEnderecoDTO;
import com.br.senac.EcommerceAPI.Keys.EnderecoUsuarioKey;
import com.br.senac.EcommerceAPI.Models.*;
import com.br.senac.EcommerceAPI.Repositories.EnderecoRepository;
import com.br.senac.EcommerceAPI.Repositories.EnderecoUsuarioRepository;
import com.br.senac.EcommerceAPI.Repositories.UsuarioRepository;
import com.br.senac.EcommerceAPI.ViaCep.ViaCepResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ViaCepService viaCepService;

    @Autowired
    private CredencialService credencialService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EnderecoUsuarioRepository enderecoUsuarioRepository;

    public ResponseEntity<EnderecoModel> criarEndereco(String cep, String numero) throws Exception {

        ViaCepResponse viaCepResponse = viaCepService.dadosCep(cep);

        if(viaCepResponse != null) {
            EnderecoModel endereco = new EnderecoModel();
            endereco.setCep(viaCepResponse.getCep());
            endereco.setLogradouro(viaCepResponse.getLogradouro());
            endereco.setComplemento(endereco.getComplemento());
            endereco.setBairro(viaCepResponse.getBairro());
            endereco.setNumero(numero);
            endereco.setCidade(viaCepResponse.getCidade());
            endereco.setUf(viaCepResponse.getUf());

            enderecoRepository.save(endereco);

            return new ResponseEntity<>(endereco, HttpStatus.CREATED);
        } else
            throw new Exception("Erro ao cadastrar o endereço");
    }

    public ResponseEntity<EnderecoModel> salvarNovoEndereco(NovoEnderecoDTO dto) throws Exception {

            EnderecoModel endereco = new EnderecoModel();

            endereco.setCep(dto.getCep());
            endereco.setLogradouro(dto.getLogradouro());
            endereco.setComplemento(dto.getComplemento());
            endereco.setBairro(dto.getBairro());
            endereco.setNumero(dto.getNumero());
            endereco.setCidade(dto.getCidade());
            endereco.setUf(dto.getUf());
            EnderecoModel enderecoSalvo = enderecoRepository.save(endereco);

            UsuarioModel usuarioModel = new UsuarioModel();
            usuarioModel.setId(dto.getIdUsuario());

            EnderecoUsuarioKey key = new EnderecoUsuarioKey(usuarioModel, enderecoSalvo);
            EnderecoUsuario eu = new EnderecoUsuario();
            eu.setId(key);
            enderecoUsuarioRepository.save(eu);

            return new ResponseEntity<>(enderecoSalvo, HttpStatus.CREATED);
    }

    public ResponseEntity<List<EnderecoModel>> listarEnderecos() {
        List<EnderecoModel> listaEndereco = enderecoRepository.findAll();
        return new ResponseEntity<>(listaEndereco, HttpStatus.OK);
    }

    public ResponseEntity<EnderecoModel> buscaPorId(Long id) throws Exception {
        EnderecoModel endereco = enderecoRepository.findById(id).orElseThrow(
                () -> new Exception("Endereço não localizado!"));
        return new ResponseEntity<>(endereco, HttpStatus.OK);
    }

    public ResponseEntity<EnderecoModel> buscaPorCep(String cep) throws Exception {
        EnderecoModel e = enderecoRepository.buscaPorCep(cep);
        if(e != null) {
            return new ResponseEntity<>(e, HttpStatus.OK);
        } else
            throw new Exception("Endereço não localizado");
    }

    public ResponseEntity<EnderecoModel> atualizarEndereco(Long id, EnderecoDTO dto) throws Exception {
        EnderecoModel eE = enderecoRepository.findById(id).orElseThrow(
                () -> new Exception("Endereço não encontrado"));
        eE.setCep(dto.getCep());
        eE.setLogradouro(dto.getLogradouro());
        eE.setComplemento(dto.getComplemento());
        eE.setBairro(dto.getBairro());
        eE.setNumero(dto.getNumero());
        eE.setCidade(dto.getCidade());
        eE.setUf(dto.getUf());

        enderecoRepository.save(eE);

        return new ResponseEntity<>(eE, HttpStatus.OK);
    }
}
