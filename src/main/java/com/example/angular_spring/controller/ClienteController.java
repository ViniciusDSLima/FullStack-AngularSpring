package com.example.angular_spring.controller;

import com.example.angular_spring.DTO.ClienteDTO;
import com.example.angular_spring.DTO.TecnicoDTO;
import com.example.angular_spring.Domain.models.Cliente;
import com.example.angular_spring.Domain.models.Tecnico;
import com.example.angular_spring.services.ClienteService;
import com.example.angular_spring.services.TecnicoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/clientes")
@AllArgsConstructor
public class ClienteController {

    private ClienteService service;

    @GetMapping("/buscar/{id}")
    @ResponseStatus(value = HttpStatus.OK, reason = "Tecnico encontrado no banco de dados com sucesso")
    public ClienteDTO TecnicoDTOfindById(@PathVariable Long id){
        Cliente cliente = service.findById(id);
        return new ClienteDTO(cliente);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK, reason = "Lista de tecnicos encontrada com sucesso")
    public ResponseEntity<List<ClienteDTO>> findAll(){
        List<Cliente> list = service.findAll();
        List<ClienteDTO> listDTO = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> create(@Validated @RequestBody ClienteDTO clienteDTO){
        Cliente obj = service.create(clienteDTO);

        return ResponseEntity.status(HttpStatus.CREATED.value()).body(clienteDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Long id, @Validated @RequestBody ClienteDTO clienteDTO){
        Cliente oldObj = service.update(id, clienteDTO);
        return ResponseEntity.ok().body(new ClienteDTO(oldObj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClienteDTO> delete(@PathVariable Long id){
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
