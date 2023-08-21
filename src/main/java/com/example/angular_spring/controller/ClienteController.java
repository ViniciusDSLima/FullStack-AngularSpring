package com.example.angular_spring.controller;

import com.example.angular_spring.DTO.TecnicoDTO;
import com.example.angular_spring.Domain.models.Tecnico;
import com.example.angular_spring.services.TecnicoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/tecnicos")
@AllArgsConstructor
public class TecnicoController {

    private TecnicoService service;

    @GetMapping("/buscar/{id}")
    @ResponseStatus(value = HttpStatus.OK, reason = "Tecnico encontrado no banco de dados com sucesso")
    public TecnicoDTO TecnicoDTOfindById(@PathVariable Long id){
        Tecnico tecnico = service.findById(id);
        return new TecnicoDTO(tecnico);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK, reason = "Lista de tecnicos encontrada com sucesso")
    public ResponseEntity<List<TecnicoDTO>> findAll(){
        List<Tecnico> list = service.findAll();
        List<TecnicoDTO> listDTO = list.stream().map(obj -> new TecnicoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<TecnicoDTO> create(@Validated @RequestBody TecnicoDTO tecnicoDTO){
        Tecnico obj = service.create(tecnicoDTO);

        return ResponseEntity.status(HttpStatus.CREATED.value()).body(tecnicoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TecnicoDTO> update(@PathVariable Long id, @Validated @RequestBody TecnicoDTO tecnicoDTO){
        Tecnico oldObj = service.update(id, tecnicoDTO);
        return ResponseEntity.ok().body(new TecnicoDTO(tecnicoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TecnicoDTO> delete(@PathVariable Long id){
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
