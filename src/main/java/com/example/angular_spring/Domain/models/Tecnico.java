package com.example.angular_spring.Domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tecnico extends Pessoa{

    private List<Chamado>  chamados = new ArrayList<>();
    public Tecnico(String nome, String cpf, String email, String senha) {
        super(nome, cpf, email, senha);
    }
}
