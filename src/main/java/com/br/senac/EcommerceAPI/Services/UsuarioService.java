package com.br.senac.EcommerceAPI.Services;

import com.br.senac.EcommerceAPI.DTO.UsuarioDTO;
import com.br.senac.EcommerceAPI.Models.UsuarioModel;
import com.br.senac.EcommerceAPI.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ResponseEntity<UsuarioModel> criarUsuario(UsuarioDTO dto) throws ParseException {

        UsuarioModel usuario = new UsuarioModel(dto);

//        try {
//            if(dto.getDataNascimento() != null) {
//                dto.setDataNascimento(ajustarData(dto.getDataNascimento()));
//            }

            usuarioRepository.save(usuario);

            return new ResponseEntity<>(usuario, HttpStatus.CREATED);
//        } catch (ParseException e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
    }

    private Date ajustarData(Date data) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));

        String dataFormatada = formatter.format(data);

        return formatter.parse(dataFormatada);
    }

    public ResponseEntity<String> processarData(String dtNascimento) {
        return new ResponseEntity<>(dtNascimento, HttpStatus.CREATED);
    }

    public ResponseEntity<List<UsuarioModel>> listarUsuarios() {
        List<UsuarioModel> listaCliente = usuarioRepository.findAll();
        return new ResponseEntity<>(listaCliente, HttpStatus.OK);
    }

    public ResponseEntity<UsuarioModel> buscaPorId(Long id) throws Exception{
        UsuarioModel c = usuarioRepository.findById(id).orElseThrow(
                () -> new Exception("Cliente não encontrado"));
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    public ResponseEntity<UsuarioModel> buscaPorNome(String nome) throws Exception {
        UsuarioModel c = usuarioRepository.buscaPorNome(nome);
        if (c != null) {
            return new ResponseEntity<>(c, HttpStatus.OK);
        } else
            throw new Exception("Cliente não localizado");
    }

    public ResponseEntity<UsuarioModel> buscaPorCPF(String cpf) throws Exception {
        UsuarioModel c = usuarioRepository.buscaPorCPF(cpf);
        if (c != null) {
            return new ResponseEntity<>(c, HttpStatus.OK);
        } else
            throw new Exception("Cliente não encontrado");
    }

    public ResponseEntity<UsuarioModel> atualizarUsuario(Long id, UsuarioDTO dto) throws Exception {
        UsuarioModel cE = usuarioRepository.findById(id).orElseThrow(
                () -> new Exception("Cliente não encontrado"));
        cE.setNome(dto.getNome());
        cE.setEmail(dto.getEmail());
        cE.setTelefone(dto.getTelefone());
        cE.setCpf(dto.getCpf());
        cE.setDtNascimento(dto.getDtNascimento());

        usuarioRepository.save(cE);
        return new ResponseEntity<>(cE, HttpStatus.OK);
    }

    //TODO: Implementar método para inativar usuário. NENHUM usuário será excluído do sistema;
}
