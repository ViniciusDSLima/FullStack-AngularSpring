package com.example.angular_spring.Domain.models;

import com.example.angular_spring.Domain.enums.Perfil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.ls.LSException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Set;
@Entity(name = "cliente")
@AllArgsConstructor
@Data
public class Cliente extends Pessoa{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "cliente")
    private List<Chamado> chamados = new ArrayList<>();

    public Cliente(Long id, String nome, String cpf, String email, String senha, Set<Integer> perfis, LocalDate criacao) {
        super(id, nome, cpf, email, senha, perfis, criacao);
    }

    public Cliente() {
        super();
        addPerfis(Perfil.CLIENTE);
    }
}
