package com.br.senac.EcommerceAPI.Controllers;

import com.br.senac.EcommerceAPI.DTO.*;
import com.br.senac.EcommerceAPI.Models.CredencialModel;
import com.br.senac.EcommerceAPI.Models.EnderecoModel;
import com.br.senac.EcommerceAPI.Models.UsuarioModel;
import com.br.senac.EcommerceAPI.Services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/usuarios")
@Tag(name = "UsuárioController", description = "Endpoints para manipulação de Usuários")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/criar")
    @Operation(summary = "Adicionar novo usuário",
            description = "Adiciona um novo usuário através de uma representação JSON",
            tags = {"UsuárioController"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = UsuarioModel.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<UsuarioModel> criarCliente(@RequestBody CadastroUsuarioDTO dto) throws ParseException {
        return usuarioService.criarUsuario(dto);
    }
    @GetMapping
    @Operation(summary = "Retornar todos os usuários",
            description = "Retorna todos os usuários cadastrados no ecommerce",
            tags = {"UsuárioController"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = UsuarioModel.class))
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<List<UsuarioModel>> listarClientes() {
        return usuarioService.listarUsuarios();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Localizar usuário por ID",
            description = "Localiza um usuário pelo seu ID",
            tags = {"UsuárioController"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = UsuarioModel.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<UsuarioModel> buscarPorId(@PathVariable (value = "id")
                                                        Long id) throws Exception {
        return usuarioService.buscaPorId(id);
    }
    @GetMapping("/nome/{nome}")
    @Operation(summary = "Localizar usuário por nome",
            description = "Localiza um usuário pelo seu nome",
            tags = {"UsuárioController"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = UsuarioModel.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<UsuarioModel> buscaPorNome(@PathVariable (value = "nome")
                                                         String nome) throws Exception {
        return usuarioService.buscaPorNome(nome);
    }
    @GetMapping("/info")
    @Operation(summary = "Retornar dados do usuário",
            description = "Retorna informações básicas do usuário autenticado",
            tags = {"UsuárioController"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = UsuarioModel.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<UsuarioInfoDTO> retonaInfoUsuario(@RequestParam("id") Long id) throws Exception {
        return usuarioService.retonaDadosUsuario(id);
    }
    @PutMapping("/atualizardados")
    @Operation(summary = "Atualizar dados do usuário",
            description = "Atualiza as informações básicas do usuário através de uma representação JSON",
            tags = {"UsuárioController"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = UsuarioModel.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<UsuarioModel> atualizarUsuario(@RequestParam ("id") Long id,
                                                         @RequestBody AtualizarUsuarioDTO dto) throws Exception {
        return usuarioService.atualizarUsuario(id, dto);
    }
    @PutMapping("/atualizaremail")
    @Operation(summary = "Atualizar e-mail do usuário",
            description = "Atualiza o e-mail do usuário autenticado através de uma representação JSON",
            tags = {"UsuárioController"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = UsuarioModel.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<CredencialModel> atualizarEmail(@RequestParam ("id") Long id,
                                                               @RequestBody AtualizarCredencialDTO dto) {
        return usuarioService.atualizarEmail(id, dto);

    }
    @PutMapping("/atualizarsenha")
    @Operation(summary = "Atualizar senha do usuário",
            description = "Atualiza a senha do usuário autenticado através de uma representação JSON",
            tags = {"UsuárioController"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = UsuarioModel.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<CredencialModel> atualizarSenha(@RequestParam ("id") Long id,
                                                               @RequestBody AtualizarCredencialDTO dto) {
        return usuarioService.atualizarSenha(id, dto);

    }
}
