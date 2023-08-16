package com.example.angular_spring.services;

import com.example.angular_spring.Domain.models.Tecnico;
import com.example.angular_spring.repository.TecnicoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class TecnicoService {

    private TecnicoRepository tecnicoRepository;

    public Tecnico findById(Long id) {
        return tecnicoRepository.findById(id).orElseThrow( () ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
