package com.bramosewerton.todolist.tarefa;

import java.util.UUID;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, UUID>{
    List<Tarefa> findByIdUsuario(UUID idUsuario);
}
