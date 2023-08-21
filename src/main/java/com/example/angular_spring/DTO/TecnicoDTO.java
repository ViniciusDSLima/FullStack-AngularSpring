package com.example.angular_spring.DTO;

import com.example.angular_spring.Domain.enums.Perfil;
import com.example.angular_spring.Domain.models.Tecnico;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
@Data
@AllArgsConstructor
public class TecnicoDTO extends Tecnico{
    protected Long id;
    @NotBlank(message = "O nome é obrigatorio ")
    protected String nome;
    @NotBlank(message = "O email é obrigatorio ")
    protected String email;
    @NotBlank(message = "A senha é obrigatoria ")
    protected String senha;
    @NotBlank(message = "O cpf é obrigatorio ")
    @UniqueElements
    @CPF
    protected String cpf;
    protected Set<Integer> perfis = new HashSet<>();

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate criacao = LocalDate.now();

    public TecnicoDTO(){
        super();
        addPerfis(Perfil.TECNICO);
    }

    public TecnicoDTO(Tecnico tecnico) {
        tecnico.getNome();
        tecnico.getEmail();
        tecnico.getCpf();
        tecnico.getPerfis();
        tecnico.getCriacao();
        tecnico.getSenha();
    }
}
