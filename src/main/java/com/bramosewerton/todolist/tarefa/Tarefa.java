package com.bramosewerton.todolist.tarefa;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "Tarefa")
public class Tarefa {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String descricao;

    @Column(length = 50)
    private String titulo;
    private LocalDateTime dataDeInicio;
    private LocalDateTime dataDeTermino;
    private String prioridade;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private UUID idUsuario;

    private void setTitulo(String valor) throws Exception{
        if (valor.length() >= 50){
            throw new Exception("O tamanho máximo para o título é de 50 caracteres.");
        }else{
            this.titulo = valor;
        }
    }
}