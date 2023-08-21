package com.example.angular_spring.Domain.models;

import com.example.angular_spring.DTO.TecnicoDTO;
import com.example.angular_spring.Domain.enums.Perfil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Entity(name = "tecnico")
@Data
@AllArgsConstructor
public class Tecnico extends Pessoa{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "tecnico")
    private List<Chamado>  chamados = new ArrayList<>();
    public Tecnico(String nome, String cpf, String email, String senha) {
        super(nome, cpf, email, senha);
    }

    public Tecnico(){
        super();
        addPerfis(Perfil.TECNICO);
    }

    public Tecnico(TecnicoDTO tecnicoDTO) {
        this.nome = tecnicoDTO.getNome();
        this.email = tecnicoDTO.getEmail();
        this.senha = tecnicoDTO.getSenha();
        this.cpf = tecnicoDTO.getCpf();
    }
}
