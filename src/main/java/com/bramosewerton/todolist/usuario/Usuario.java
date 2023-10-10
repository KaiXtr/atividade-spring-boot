package com.bramosewerton.todolist.usuario;

import java.util.UUID;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name="Usuario")
public class Usuario {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(unique = true)
    private String nome;
    private String email;
    private String senha;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public void setNome(String v){
        this.nome = v;
    }
    public void setEmail(String v){
        this.email = v;
    }
    public void setSenha(String v){
        this.senha = v;
    }
    public String getNome(){
        return this.nome;
    }
    public String getEmail(){
        return this.email;
    }
    public String getSenha(){
        return this.senha;
    }
}