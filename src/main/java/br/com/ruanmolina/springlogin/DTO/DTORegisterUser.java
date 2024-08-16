package br.com.ruanmolina.springlogin.DTO;

import jakarta.validation.constraints.NotNull;

public record DTORegisterUser(
        @NotNull String name,
        @NotNull String password,
        @NotNull String email) {
}
