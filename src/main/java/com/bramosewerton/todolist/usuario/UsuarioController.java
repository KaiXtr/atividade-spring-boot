package com.bramosewerton.todolist.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usrRepo;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody Usuario usr) {
        var usuario = this.usrRepo.encontrarPorNomeDeUsuario(usr.getNome());

        if (usuario != null){
            System.out.println("ALERTA: Usuário já existe!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe!");
        }

        var novoUsuario = this.usrRepo.save(usr);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }
}
