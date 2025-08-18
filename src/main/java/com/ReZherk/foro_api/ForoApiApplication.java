package com.ReZherk.foro_api;

import com.ReZherk.foro_api.domain.usuario.Usuario;
import com.ReZherk.foro_api.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ForoApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(ForoApiApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Bean
	CommandLineRunner init(UsuarioRepository usuarios, PasswordEncoder encoder) {
		return args -> {
			if (usuarios.findByEmail("user@foro.com").isEmpty()) {
				Usuario u = new Usuario();
				u.setNombre("Usuario Demo");
				u.setEmail("user@foro.com");
				u.setPassword(encoder.encode("123456"));
				usuarios.save(u);
			}
		};
	}
}
