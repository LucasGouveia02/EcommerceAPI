package com.br.senac.EcommerceAPI.Services;

import com.br.senac.EcommerceAPI.DTO.*;
import com.br.senac.EcommerceAPI.Keys.EnderecoUsuarioKey;
import com.br.senac.EcommerceAPI.Models.*;
import com.br.senac.EcommerceAPI.Repositories.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private EnderecoUsuarioRepository enderecoUsuarioRepository;

    @Autowired
    private CredencialRepository credencialRepository;

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    private UsuarioModel usuario;
    private EnderecoUsuario endereco;

    /**
     * Metódo que retorna os dados do usuário que está autenticado
     * @param id ID do usuário autenticado no Ecommerce
     * @return ResponseEntity com os dados do usuário
     * */
    public ResponseEntity<UsuarioInfoDTO> retonaDadosUsuario(Long id) throws Exception {
        UsuarioModel usuario = usuarioRepository.findById(id).orElseThrow(
                () -> new Exception("Usuário não encontrado!"));

        CredencialModel credencial = credencialRepository.findByIdUsuario(id);
        UsuarioInfoDTO usuarioInfoDTO = new UsuarioInfoDTO();

        if (credencial != null) {
            usuarioInfoDTO.setNome(usuario.getNome());
            usuarioInfoDTO.setCpf(usuario.getCpf());
            usuarioInfoDTO.setDtNascimento(usuario.getDtNascimento());
            usuarioInfoDTO.setTelefone(usuario.getTelefone());
            usuarioInfoDTO.setEmail(credencial.getEmail());
            usuarioInfoDTO.setSenha(credencial.getSenha());
            return new ResponseEntity<>(usuarioInfoDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Cria um novo usuário a partir de um DTO com informações de dados pessoais," +
     * "informações de endereço e credenciais. Um carrinho é criado e atrelado no momento do cadastro do usuário.
     * @param dto Objeto de dado do usuário a ser criado
     * @return ResponseEntity com o usuário criado ou status de conflito se o usuário já existe
     * */
    public ResponseEntity<UsuarioModel> criarUsuario(CadastroUsuarioDTO dto) throws ParseException {

        try {
            if(dto.getDtNascimento() != null) {
                dto.setDtNascimento(ajustarData(dto.getDtNascimento()));
            }

            UsuarioModel usuarioExistente = usuarioRepository.buscaPorCPF(dto.getCpf());
            CredencialModel usuarioCredencial = credencialRepository.findByUsuario(dto.getEmail());

            if(usuarioExistente != null || usuarioCredencial != null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }

            UsuarioModel usuario = new UsuarioModel(dto);
            UsuarioModel usuarioSalvo = usuarioRepository.save(usuario);


            EnderecoDTO enderecoDTO = new EnderecoDTO();

            enderecoDTO.setCep(dto.getCep());
            enderecoDTO.setLogradouro(dto.getLogradouro());
            enderecoDTO.setComplemento(dto.getComplemento());
            enderecoDTO.setBairro(dto.getBairro());
            enderecoDTO.setNumero(dto.getNumero());
            enderecoDTO.setCidade(dto.getCidade());
            enderecoDTO.setUf(dto.getUf());

            EnderecoModel endereco = new EnderecoModel(enderecoDTO);

            CredencialModel credencialModel = new CredencialModel();
            credencialModel.setEmail(dto.getEmail());
            credencialModel.setSenha(dto.getSenha());
            credencialModel.setAdmin(false);
            credencialModel.setIdUsuario(usuarioSalvo.getId());
            credencialRepository.save(credencialModel);

            EnderecoModel em = enderecoRepository.save(endereco);

            EnderecoUsuarioKey euk = new EnderecoUsuarioKey(usuario, em);
            EnderecoUsuario eu = new EnderecoUsuario();
            eu.setId(euk);
            enderecoUsuarioRepository.save(eu);

            CarrinhoModel carrinho = new CarrinhoModel();
            carrinho.setUsuario_id(usuarioSalvo);
            carrinho.setQuantidadeItens(0);
            carrinhoRepository.save(carrinho);

            return new ResponseEntity<>(usuario, HttpStatus.CREATED);
        } catch (ParseException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Date ajustarData(Date data) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));

        String dataFormatada = formatter.format(data);

        return formatter.parse(dataFormatada);
    }

    /**
     * Retorna uma lista de usuários cadastrado
     * @return ResponseEntity com a lista de usuários
     * */
    public ResponseEntity<List<UsuarioModel>> listarUsuarios() {
        List<UsuarioModel> listaCliente = usuarioRepository.findAll();
        return new ResponseEntity<>(listaCliente, HttpStatus.OK);
    }

    /**
     * Busca um usuário pelo ID e verifica o quantidade de itens no carrinho do usuário
     * @param id ID do usuário a ser encontrado
     * @return ResponseEntity com o usuário encontrado e o tamanho do carrinho
     * */
    public ResponseEntity<UsuarioModel> buscaPorId(Long id) throws Exception{
        UsuarioModel c;
        c = usuarioRepository.findById(id).orElseThrow(() -> new Exception("Cliente não encontrado"));
        if (c.getCarrinho().getQuantidadeItens() != c.getCarrinho().getCarrinhoProdutoModel().size()) {
            c.getCarrinho().setQuantidadeItens(c.getCarrinho().getCarrinhoProdutoModel().size());
            c = usuarioRepository.save(c);
        }
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    /**
     * Busca um usuário pelo Nome e verifica o quantidade de itens no carrinho do usuário
     * @param nome Nome do usuário a ser encontrado
     * @return ResponseEntity com o usuário encontrado e o tamanho do carrinho
     * */
    public ResponseEntity<UsuarioModel> buscaPorNome(String nome) throws Exception {
        UsuarioModel c;
        c = usuarioRepository.buscaPorNome(nome);
        if (c.getCarrinho().getQuantidadeItens() != c.getCarrinho().getCarrinhoProdutoModel().size()) {
            c.getCarrinho().setQuantidadeItens(c.getCarrinho().getCarrinhoProdutoModel().size());
            c = usuarioRepository.save(c);
        }
        if (c != null) {
            return new ResponseEntity<>(c, HttpStatus.OK);
        } else
            throw new Exception("Cliente não localizado");
    }

    /**
     * Busca um usuário pelo CPF e verifica o quantidade de itens no carrinho do usuário
     * @param cpf CPF do usuário a ser encontrado
     * @return ResponseEntity com o usuário encontrado e o tamanho do carrinho
     * */
    public ResponseEntity<UsuarioModel> buscaPorCPF(String cpf) throws Exception {
        UsuarioModel c;
        c = usuarioRepository.buscaPorCPF(cpf);
        if (c.getCarrinho().getQuantidadeItens() != c.getCarrinho().getCarrinhoProdutoModel().size()) {
            c.getCarrinho().setQuantidadeItens(c.getCarrinho().getCarrinhoProdutoModel().size());
            c = usuarioRepository.save(c);
        }
        if (c == null) {
            return new ResponseEntity<>(c, HttpStatus.OK);
        } else
            throw new Exception("Cliente já cadastrado.");
    }

    /**
     * Atualiza os dados pessoais do usuário autenticado
     * @param id ID do usuário a autenticado
     * @param dto Objeto com os dados a serem alterados
     * @return ResponseEntity com o usuário alterado
     * */
    public ResponseEntity<UsuarioModel> atualizarUsuario(Long id, AtualizarUsuarioDTO dto) throws Exception {
        UsuarioModel usuario = usuarioRepository.findById(id).orElseThrow(
                () -> new Exception("Usuário não encontrado!"));

        if(usuario != null) {
            usuario.setNome(dto.getNome());
            if (dto.getCpf() != null && !dto.getCpf().equals(usuario.getCpf())) {
                UsuarioModel usuarioExistente = usuarioRepository.buscaPorCPF(dto.getCpf());
                if (usuarioExistente != null && !usuarioExistente.getId().equals(id)) {
                    return new ResponseEntity<>(HttpStatus.CONFLICT);
                }
            }
            usuario.setCpf(dto.getCpf());
            usuario.setDtNascimento(dto.getDtNascimento());
            usuario.setTelefone(dto.getTelefone());
            usuarioRepository.save(usuario);
        }
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    /**
     * Atualiza o email do usuário autenticado
     * @param id ID do usuário a autenticado
     * @param dto Objeto com os dados a serem alterados
     * @return ResponseEntity com o usuário alterado
     * */
    public ResponseEntity<CredencialModel> atualizarEmail(Long id, AtualizarCredencialDTO dto) {
        CredencialModel credencial = credencialRepository.findByIdUsuario(id);

        if (credencial != null) {
            if (dto.getEmail() != null &&!dto.getEmail().equals(credencial.getEmail())) {
                CredencialModel emailExistente = credencialRepository.findByUsuario(dto.getEmail());
                if (emailExistente != null && !emailExistente.getIdUsuario().equals(id)) {
                    return new ResponseEntity<>(HttpStatus.CONFLICT);
                }
            }
            credencial.setEmail(dto.getEmail());

            credencialRepository.save(credencial);
            return new ResponseEntity<>(credencial, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    /**
     * Atualiza a senha do usuário autenticado
     * @param id ID do usuário a autenticado
     * @param dto Objeto com os dados a serem alterados
     * @return ResponseEntity com o usuário alterado
     * */
    public ResponseEntity<CredencialModel> atualizarSenha(Long id, AtualizarCredencialDTO dto) {
        CredencialModel credencial = credencialRepository.findByIdUsuario(id);

        if (credencial != null) {
            if (dto.getSenhaAtual() != null && dto.getSenha() != null) {
                if (dto.getSenhaAtual().equals(credencial.getSenha())) {
                    credencial.setSenha(dto.getSenha());
                } else {
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            }

            credencialRepository.save(credencial);
            return new ResponseEntity<>(credencial, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    /**
     * Atualiza o endereço do usuário autenticado
     * @param id ID do endereço a ser alterado
     * @param dto Objeto com os dados do endereço a ser alterado
     * @return ResponseEntity com o endereço alterado
     * */
    public ResponseEntity<EnderecoModel> atualizarEndereco(Long id, AtualizarEnderecoDTO dto) throws Exception {
        EnderecoModel endereco = enderecoRepository.findById(id).orElseThrow(
                () -> new Exception("Endereço não encontrado!"));

        if (endereco != null) {
            endereco.setCep(dto.getCep());
            endereco.setLogradouro(dto.getLogradouro());
            endereco.setComplemento(dto.getComplemento());
            endereco.setNumero(dto.getNumero());
            endereco.setBairro(dto.getBairro());
            endereco.setCidade(dto.getCidade());
            endereco.setUf(dto.getUf());
            enderecoRepository.save(endereco);
            return new ResponseEntity<>(endereco, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * Exclui o endereço escolhido pelo usuário autenticado
     * @param id ID do usuário a autenticado
     * @return ResponseEntity com o StatusCode 200 (OK)
     * */
    @Transactional
    public ResponseEntity<?> excluirEndereco(Long id) throws Exception {
        EnderecoModel endereco = enderecoRepository.findById(id).orElseThrow(
                () -> new Exception("Endereço não localizado!"));

        enderecoUsuarioRepository.deletarEndereco(endereco);
        enderecoRepository.deleteById(endereco.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Adiciona um novo endereço atrelado ao usuário autenticado
     * @param id ID do usuário a autenticado
     * @param dto Objeto com os dados do endereço a ser adicionado
     * @return ResponseEntity com o novo endereço adicionado
     * */
    public ResponseEntity<EnderecoModel> novoEndereco(Long id, EnderecoDTO dto) throws Exception {
        UsuarioModel usuario = usuarioRepository.findById(id).orElseThrow(
                () -> new Exception("Usuário não localizado!"));

        if (usuario != null) {
            EnderecoModel endereco = new EnderecoModel();
            endereco.setCep(dto.getCep());
            endereco.setLogradouro(dto.getLogradouro());
            endereco.setComplemento(dto.getComplemento());
            endereco.setNumero(dto.getNumero());
            endereco.setBairro(dto.getBairro());
            endereco.setCidade(dto.getCidade());
            endereco.setUf(dto.getUf());

            EnderecoModel enderecoSalvo = enderecoRepository.save(endereco);

            EnderecoUsuarioKey euk = new EnderecoUsuarioKey(usuario, enderecoSalvo);
            EnderecoUsuario eu = new EnderecoUsuario();
            eu.setId(euk);
            enderecoUsuarioRepository.save(eu);

            return new ResponseEntity<>(enderecoSalvo, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
