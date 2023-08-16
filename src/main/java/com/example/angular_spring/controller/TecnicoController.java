package com.example.angular_spring.controller;

import com.example.angular_spring.Domain.models.Tecnico;
import com.example.angular_spring.services.TecnicoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/tecnico")
@AllArgsConstructor
public class TecnicoController {

    private TecnicoService service;

    @GetMapping("/buscar")
    @ResponseStatus(value = HttpStatus.OK, reason = "Tecnico encontrado no banco de dados com sucesso")
    public Tecnico findById(@PathVariable Long id){
     return service.findById(id);
    }
}
