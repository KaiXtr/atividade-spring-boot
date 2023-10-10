package com.bramosewerton.todolist.usuario;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID>{
    Usuario encontrarPorNomeDeUsuario(String nomeUsuario);
}