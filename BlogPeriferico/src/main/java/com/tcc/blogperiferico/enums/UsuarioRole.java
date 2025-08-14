package com.tcc.blogperiferico.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum UsuarioRole {
    ROLE_VISITANTE,
    ROLE_USUARIO,
    ROLE_ADMINISTRADOR;

    @JsonCreator
    public static UsuarioRole fromString(String value) {
        return switch (value.toUpperCase()) {
            case "ADMINISTRADOR", "ROLE_ADMINISTRADOR" -> ROLE_ADMINISTRADOR;
            case "USUARIO", "ROLE_USUARIO" -> ROLE_USUARIO;
            case "VISITANTE", "ROLE_VISITANTE" -> ROLE_VISITANTE;
            default -> throw new IllegalArgumentException("Role inválida: " + value);
        };
    }
}
