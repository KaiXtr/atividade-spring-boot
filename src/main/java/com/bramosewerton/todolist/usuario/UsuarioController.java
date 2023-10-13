package com.bramosewerton.todolist.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usrRepo;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody Usuario usr) {
        var usuario = this.usrRepo.findByNome(usr.getNome());

        if (usuario != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ALERTA: Usuário já existe!");
        }
        if (usr.getEmail().indexOf("@") == -1){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ALERTA: Insira um endereço de email válido.");
        }
        if (usr.getSenha().length() < 8){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ALERTA: Senha precisa ter pelo menos 8 caracteres.");
        }
        
        var senhaHash = BCrypt.withDefaults().hashToString(12,usr.getSenha().toCharArray());
        usr.setSenha(senhaHash);

        var novoUsuario = this.usrRepo.save(usr);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    @GetMapping("/")
    public ResponseEntity listar(HttpServletRequest request){
        var nome = request.getAttribute("nome");
        var usuario = this.usrRepo.findByNome((String) nome);
        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }
}
