package com.ReZherk.foro_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TopicoRequest(
        @NotNull Long idUsuario,
        @NotBlank @Size(min = 3, max = 150) String titulo,
        @NotBlank @Size(min = 3, max = 5000) String mensaje,
        @NotBlank @Size(min = 2, max = 100) String nombreCurso
) {}