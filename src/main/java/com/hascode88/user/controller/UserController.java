package com.hascode88.user.controller;

import com.hascode88.user.dto.UserData;
import com.hascode88.user.dto.UserRequest;
import com.hascode88.user.entity.UserEntity;
import com.hascode88.user.entity.UserException;
import com.hascode88.user.usercase.CreateUserService;
import com.hascode88.user.usercase.DeleteUserService;
import com.hascode88.user.usercase.FindUserService;
import com.hascode88.user.usercase.UpdateUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "Users", description = "Operações relacionadas ao gerenciamento de usuários")
public class UserController {

    @Autowired
    CreateUserService createUserService;
    @Autowired
    FindUserService findUserService;
    @Autowired
    UpdateUserService updateUserService;
    @Autowired
    DeleteUserService deleteUserService;

    @PostMapping
    @Operation(summary = "Criar um novo usuário", description = "Cria um novo usuário no sistema com os dados fornecidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário criado com sucesso",
                    content = @Content(schema = @Schema(implementation = UserData.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida. Nome ou email não podem estar vazios"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<UserData> createUser(@RequestBody UserRequest request) throws UserException {
        UserData response = createUserService.create(UserEntity.create(request.getName(), request.getEmail(), LocalDateTime.now()));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar usuário por ID", description = "Retorna os dados de um usuário específico através do seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso",
                    content = @Content(schema = @Schema(implementation = UserData.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<UserData> findById(
            @Parameter(description = "ID do usuário a ser buscado", required = true, example = "1")
            @PathVariable Long id) throws UserException {
        UserData userData = findUserService.findByid(id);
        return ResponseEntity.ok(userData);
    }

    @GetMapping
    @Operation(summary = "Listar todos os usuários", description = "Retorna uma lista com todos os usuários cadastrados no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso",
                    content = @Content(schema = @Schema(implementation = UserData.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<List<UserData>> findAll() {
        List<UserData> users = findUserService.findAll();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar usuário", description = "Atualiza os dados de um usuário existente através do seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso",
                    content = @Content(schema = @Schema(implementation = UserData.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida. Nome ou email não podem estar vazios"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<UserData> update(
            @Parameter(description = "ID do usuário a ser atualizado", required = true, example = "1")
            @PathVariable Long id,
            @RequestBody UserRequest request) throws UserException {
        UserData updated = updateUserService.update(request.getName(), request.getEmail(), id);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar usuário", description = "Remove um usuário do sistema através do seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID do usuário a ser deletado", required = true, example = "1")
            @PathVariable Long id) throws UserException {
        deleteUserService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
