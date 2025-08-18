package com.ReZherk.foro_api.service;

import com.ReZherk.foro_api.domain.topico.Topico;
import com.ReZherk.foro_api.domain.usuario.Usuario;
import com.ReZherk.foro_api.dto.TopicoRequest;
import com.ReZherk.foro_api.dto.TopicoResponse;
import com.ReZherk.foro_api.repository.TopicoRepository;
import com.ReZherk.foro_api.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.List;

@Service
public class TopicoService {
    private final TopicoRepository topicos;
    private final UsuarioRepository usuarios;

    public TopicoService(TopicoRepository topicos, UsuarioRepository usuarios) {
        this.topicos = topicos;
        this.usuarios = usuarios;
    }

    public List<TopicoResponse> listar() {
        return topicos.findAll().stream().map(this::toDto).toList();
    }

    @Transactional
    public ResponseEntity<TopicoResponse> crear(TopicoRequest dto) {
        Usuario u = usuarios.findById(dto.idUsuario())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no existe"));
        Topico t = new Topico();
        t.setTitulo(dto.titulo());
        t.setMensaje(dto.mensaje());
        t.setNombreCurso(dto.nombreCurso());
        t.setUsuario(u);
        Topico saved = topicos.save(t);
        URI location = URI.create("/topicos/" + saved.getId());
        return ResponseEntity.created(location).body(toDto(saved));
    }

    @Transactional
    public TopicoResponse actualizar(Long id, TopicoRequest dto) {
        Topico t = topicos.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tópico no existe"));
        if (!t.getUsuario().getId().equals(dto.idUsuario())) {
            throw new IllegalArgumentException("El idUsuario no coincide con el creador del tópico");
        }
        t.setTitulo(dto.titulo());
        t.setMensaje(dto.mensaje());
        t.setNombreCurso(dto.nombreCurso());
        return toDto(t);
    }

    @Transactional
    public void eliminar(Long id) {
        Topico t = topicos.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tópico no existe"));
        topicos.delete(t);
    }

    private TopicoResponse toDto(Topico t) {
        return new TopicoResponse(
                t.getId(),
                t.getTitulo(),
                t.getMensaje(),
                t.getNombreCurso(),
                t.getFechaCreacion(),
                t.getUsuario().getId(),
                t.getUsuario().getNombre()
        );
    }
}
