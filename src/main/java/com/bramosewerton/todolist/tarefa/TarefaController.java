package com.bramosewerton.todolist.tarefa;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bramosewerton.todolist.utils.Utils;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {
    
    @Autowired
    private TarefaRepository tarRepo;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody Tarefa tar,HttpServletRequest request) {
        var idUsuario = request.getAttribute("idUsuario");
        tar.setIdUsuario((UUID) idUsuario);

        var dataAtual = LocalDateTime.now();
        if (dataAtual.isAfter(tar.getDataDeInicio()) || dataAtual.isAfter(tar.getDataDeTermino())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("As datas de início e término devem ser maiores que a data atual.");
        }
        if (tar.getDataDeInicio().isAfter(tar.getDataDeTermino())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de início precisa ser anterior á data de término.");
        }

        var novaTarefa = this.tarRepo.save(tar);
        return ResponseEntity.status(HttpStatus.OK).body(novaTarefa);
    }

    @GetMapping("/")
    public List<Tarefa> listar(HttpServletRequest request){
        var idUsuario = request.getAttribute("idUsuario");
        var tarefas = this.tarRepo.findByIdUsuario((UUID) idUsuario);
        return tarefas;
    }

    @PutMapping("/{id}")
    public void atualizar(@RequestBody Tarefa tar, @PathVariable UUID id, HttpServletRequest request){

        var tarefa = this.tarRepo.findById(id).orElse(null);
        Utils.copiarPropriedadesNaoVazias(tar, tarefa);

        return this.tarRepo.save(tar);
    }
}
