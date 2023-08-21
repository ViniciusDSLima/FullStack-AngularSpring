package com.example.angular_spring.controller;

import com.example.angular_spring.DTO.ChamadoDTO;
import com.example.angular_spring.Domain.models.Chamado;
import com.example.angular_spring.services.ChamadoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/chamados")
@AllArgsConstructor
public class ChamadoController {

    private ChamadoService chamadoService;

    @GetMapping("/{id}")
    public ResponseEntity<ChamadoDTO> findById(@PathVariable Long id){
        Chamado obj = chamadoService.findById(id);
        return ResponseEntity.ok().body(new ChamadoDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<ChamadoDTO>> findAll(){
        List<Chamado> list = chamadoService.findAll();

        List<ChamadoDTO> listDTO = list.stream().map( obj -> new ChamadoDTO(obj)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Chamado> create(@RequestBody @Valid ChamadoDTO chamadoDTO){
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(chamadoService.create(chamadoDTO));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ChamadoDTO> update(@PathVariable Long id, @RequestBody @Valid ChamadoDTO chamadoDTO){
        Chamado chamado = chamadoService.update(id, chamadoDTO);
        return ResponseEntity.ok().body(new ChamadoDTO(chamado));
    }
}
