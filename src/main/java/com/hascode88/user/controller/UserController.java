package com.hascode88.user.controller;

import com.hascode88.user.dto.PageResponse;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
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
    @Operation(summary = "Listar usuários com paginação", description = "Retorna uma lista paginada de usuários cadastrados no sistema. Use os parâmetros page, size e sort para controlar a paginação. Exemplo: ?page=0&size=10&sort=name,asc. Campos válidos para ordenação: id, name, email, createAt")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista paginada de usuários retornada com sucesso",
                    content = @Content(schema = @Schema(implementation = PageResponse.class))),
            @ApiResponse(responseCode = "400", description = "Campo de ordenação inválido"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<PageResponse<UserData>> findAll(
            @Parameter(description = "Número da página (começa em 0)", example = "0")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Tamanho da página", example = "10")
            @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "Campo para ordenação (id, name, email, createAt) e direção (asc, desc). Ex: name,asc", required = false)
            @RequestParam(required = false) String sort) {
        
        // Campos válidos para ordenação
        List<String> validSortFields = Arrays.asList("id", "name", "email", "createAt");
        
        Pageable pageable;
        
        if (sort != null && !sort.isEmpty()) {
            String[] sortParts = sort.split(",");
            String sortField = sortParts[0].trim();
            String direction = sortParts.length > 1 ? sortParts[1].trim() : "asc";
            
            // Validar campo de ordenação
            if (!validSortFields.contains(sortField)) {
                throw new IllegalArgumentException("Campo de ordenação inválido. Campos válidos: " + validSortFields);
            }
            
            Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
            pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortField));
        } else {
            // Ordenação padrão por ID
            pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id"));
        }
        
        PageResponse<UserData> users = findUserService.findAll(pageable);
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
