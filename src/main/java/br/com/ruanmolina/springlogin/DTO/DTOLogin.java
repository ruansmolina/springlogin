package br.com.ruanmolina.springlogin.DTO;

import jakarta.validation.constraints.NotNull;

public record DTOLogin(@NotNull String email,@NotNull String password) {
}
