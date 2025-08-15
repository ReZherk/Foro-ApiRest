## Cómo ejecutar

1. Requisitos: Java 24+, Maven 3.5+.
2. En la carpeta del proyecto: `mvn spring-boot:run`.
3. Swagger UI: `http://localhost:8080/swagger-ui.html`.
4. H2 Console: `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:forodb`).

Usuario de prueba creado al iniciar:
- email: `user@foro.com`
- password: `123456`

Proyecto:
```
foro-api/
├─ pom.xml
├─ src/
│  ├─ main/
│  │  ├─ java/
│  │  │  └─ com/example/foro/
│  │  │     ├─ ForoApiApplication.java
│  │  │     ├─ config/
│  │  │     │  ├─ SecurityConfig.java
│  │  │     │  └─ SwaggerConfig.java
│  │  │     ├─ domain/
│  │  │     │  ├─ topico/Topico.java
│  │  │     │  └─ usuario/Usuario.java
│  │  │     ├─ dto/
│  │  │     │  ├─ AuthRequest.java
│  │  │     │  ├─ AuthResponse.java
│  │  │     │  ├─ TopicoRequest.java
│  │  │     │  └─ TopicoResponse.java
│  │  │     ├─ repository/
│  │  │     │  ├─ TopicoRepository.java
│  │  │     │  └─ UsuarioRepository.java
│  │  │     ├─ service/
│  │  │     │  ├─ AuthService.java
│  │  │     │  ├─ JwtTokenService.java
│  │  │     │  └─ TopicoService.java
│  │  │     ├─ web/
│  │  │     │  ├─ AuthController.java
│  │  │     │  └─ TopicoController.java
│  │  │     └─ web/error/RestExceptionHandler.java
│  │  └─ resources/
│  │     ├─ application.yml
│  │     └─ banner.txt (opcional)
│  └─ test/
│     └─ java/ (vacío)
```
