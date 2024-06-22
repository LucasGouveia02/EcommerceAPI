package com.br.senac.EcommerceAPI.Controllers;

import com.br.senac.EcommerceAPI.DTO.*;
import com.br.senac.EcommerceAPI.Models.EnderecoModel;
import com.br.senac.EcommerceAPI.Services.EnderecoService;
import com.br.senac.EcommerceAPI.Services.UsuarioService;
import com.br.senac.EcommerceAPI.Services.ViaCepService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/enderecos")
@Tag(name = "EndereçoController", description = "Endpoints para manipulação de Endereços")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private ViaCepService viaCepService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/criar")
    @Operation(summary = "Adicionar endereço",
            description = "Adiciona um endereço para o usuário no ato do cadastro através de uma representação JSON",
            tags = {"EndereçoController"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = EnderecoModel.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<String> criarEndereco(@RequestBody EnderecoRequestDTO requestDTO) throws Exception {
        var response = enderecoService.criarEndereco(requestDTO.getCep(), requestDTO.getNumero());
        return new ResponseEntity<String>(String.valueOf(response), HttpStatus.CREATED);
    }

    @PostMapping("/novo")
    @Operation(summary = "Adicionar endereço",
            description = "Adiciona um endereço para o usuário autenticado no ato do checkout da compra, através " +
                    "de uma representação JSON",
            tags = {"EndereçoController"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = EnderecoModel.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<EnderecoModel> salvarNovoEndereco(@RequestBody NovoEnderecoDTO dto) throws Exception {
        return enderecoService.salvarNovoEndereco(dto);
    }

    @PutMapping("/atualizarenderecos")
    @Operation(summary = "Atualizar endereço do usuário",
            description = "Atualiza um dos endereços do usuário autenticado através de uma representação JSON",
            tags = {"Usuário"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = EnderecoModel.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<EnderecoModel> atualizarEndereco(@RequestParam ("id") Long id,
                                                           @RequestBody AtualizarEnderecoDTO dto) throws Exception {
        return usuarioService.atualizarEndereco(id, dto);
    }
    @DeleteMapping("/deletarendereco")
    @Operation(summary = "Deletar endereço do usuário",
            description = "Apaga um dos endereços do usuário autenticado",
            tags = {"Usuário"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<?> deletarEndereco(@RequestParam ("id") Long id) throws Exception {
        return usuarioService.excluirEndereco(id);
    }

    @PostMapping("/novoendereco")
    @Operation(summary = "Adicionar endereço do usuário",
            description = "Adiciona um endereço para o usuário autenticado através de uma representação JSON",
            tags = {"Usuário"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = EnderecoModel.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<EnderecoModel> novoEndereco(@RequestParam ("id") Long id,
                                                      @RequestBody EnderecoDTO dto) throws Exception {
        return usuarioService.novoEndereco(id, dto);
    }
}
