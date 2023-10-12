package com.bramosewerton.todolist.filtro;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bramosewerton.todolist.usuario.UsuarioRepository;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FiltroTarefaAuth extends OncePerRequestFilter {

    @Autowired
    private UsuarioRepository usrRepo;

    @Override
    public void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain chain)
        throws ServletException,IOException {

            var servletPath = request.getServletPath();
            
            if (servletPath.startsWith("/tarefas/")){

                var autorizar = request.getHeader("Authorization");
                var usrSenha = autorizar.substring(5).trim();
                byte[] authDecode = Base64.getDecoder().decode(usrSenha);
                var authString = new String(authDecode);
                String[] usrDados = authString.split(":");
                
                var validarUsuario = this.usrRepo.findByNome(usrDados[0]);
                if (validarUsuario == null){
                    response.sendError(401, "ACESSO NEGADO: Usuário não autorizado.");
                }else{
                    var validarSenha = BCrypt.verifyer().verify(usrDados[1].toCharArray(), validarUsuario.getSenha());
                    if (validarSenha.verified){
                        request.setAttribute("idUser", validarUsuario.getId());
                        chain.doFilter(request,response);
                    }else{
                        response.sendError(401, "ACESSO NEGADO: Senhas não combinam.");
                    }
                }
            }else{
                chain.doFilter(request, response);
            }
        }
}
