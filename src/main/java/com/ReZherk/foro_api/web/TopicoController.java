package com.ReZherk.foro_api.web;

import com.ReZherk.foro_api.dto.TopicoRequest;
import com.ReZherk.foro_api.dto.TopicoResponse;
import com.ReZherk.foro_api.service.TopicoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoService service;

    public TopicoController(TopicoService service) { this.service = service; }

    @Operation(summary = "Listar tópicos (público)")
    @GetMapping
    public ResponseEntity<List<TopicoResponse>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @Operation(summary = "Crear tópico (protegido)")
    @PostMapping
    public ResponseEntity<TopicoResponse> crear(@Valid @RequestBody TopicoRequest request) {
        return service.crear(request);
    }

    @Operation(summary = "Actualizar tópico (protegido)")
    @PutMapping("/{id}")
    public ResponseEntity<TopicoResponse> actualizar(@PathVariable Long id, @Valid @RequestBody TopicoRequest request) {
        return ResponseEntity.ok(service.actualizar(id, request));
    }

    @Operation(summary = "Eliminar tópico (protegido)")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok().body("Tópico eliminado");
    }
}
