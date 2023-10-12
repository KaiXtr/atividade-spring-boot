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

    public void setIdUsuario(UUID v){
        this.idUsuario = v;
    }
    public void setTitulo(String v){
        this.titulo = v;
    }
    public void setDescricao(String v){
        this.descricao = v;
    }
    public void setDataDeInicio(LocalDateTime v){
        this.dataDeInicio = v;
    }
    public void setDataDeTermino(LocalDateTime v){
        this.dataDeTermino = v;
    }

    public UUID getIdUsuario(){
        return this.idUsuario;
    }
    public String getTitulo(){
        return this.titulo;
    }
    public String getDescricao(){
        return this.descricao;
    }
    public LocalDateTime getDataDeInicio(){
        return this.dataDeInicio;
    }
    public LocalDateTime getDataDeTermino(){
        return this.dataDeTermino;
    }
}