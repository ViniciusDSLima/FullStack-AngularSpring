package com.example.angular_spring.Domain.models;

import com.example.angular_spring.Domain.enums.Perfil;
import jakarta.persistence.Entity;
import jakarta.persistence.MapKey;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Entity
@Data
@AllArgsConstructor
public class Tecnico extends Pessoa{
    @OneToMany(mappedBy = "tecnico")
    private List<Chamado>  chamados = new ArrayList<>();
    public Tecnico(String nome, String cpf, String email, String senha) {
        super(nome, cpf, email, senha);
    }

    public Tecnico(){
        super();
        addPerfis(Perfil.TECNICO);
    }
}
