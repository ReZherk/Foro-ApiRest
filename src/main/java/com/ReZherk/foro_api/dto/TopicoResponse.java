package com.ReZherk.foro_api.dto;

import java.time.LocalDateTime;

public record TopicoResponse(
        Long id,
        String titulo,
        String mensaje,
        String nombreCurso,
        LocalDateTime fechaCreacion,
        Long idUsuario,
        String nombreUsuario
) {}
