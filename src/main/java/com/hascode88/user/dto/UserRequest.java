package com.hascode88.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados de requisição para criação ou atualização de usuário")
public class UserRequest {

    @Schema(description = "Nome completo do usuário", example = "João Silva", required = true, minLength = 1)
    private String name;
    
    @Schema(description = "Endereço de email do usuário", example = "joao.silva@example.com", required = true, minLength = 1)
    private String email;

    public UserRequest(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
