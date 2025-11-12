package com.hascode88.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Dados completos de um usuário")
public class UserData {

    @Schema(description = "Identificador único do usuário", example = "1")
    private Long id;
    
    @Schema(description = "Nome completo do usuário", example = "João Silva")
    private String name;
    
    @Schema(description = "Endereço de email do usuário", example = "joao.silva@example.com")
    private String email;
    
    @Schema(description = "Data e hora de criação do usuário", example = "2024-01-15T10:30:00")
    private LocalDateTime createAt;

    public UserData(Long id, String name, String email, LocalDateTime createAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createAt = createAt;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }
}
