package com.example.angular_spring.DTO;

import com.example.angular_spring.Domain.models.Chamado;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChamadoDTO {
    private Long id;
    @NotNull(message = "O campo prioridade e requerido")
    private int prioridade;
    @NotNull(message = "O campo status e requerido")
    private int status;
    @NotNull(message = "O campo titulo e requerido")
    private String titulo;
    @NotNull(message = "O campo observacoes e requerido")
    private String observacoes;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate abertura = LocalDate.now();
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechamento;
    @NotNull(message = "O campo tecnico e requerido")
    private Long tecnico;
    @NotNull(message = "O campo cliente e requerido")
    private Long cliente;
    @NotNull(message = "O campo Nome do tecnico e requerido")
    private String nomeTecnico;
    @NotNull(message = "O campo Nome do tecnico e requerido")
    private String nomeCliente;


    public ChamadoDTO(Chamado obj) {
        this.id = obj.getId();
        this.prioridade = obj.getPrioridade().getCodigo();
        this.status = obj.getStatus().getCodigo();
        this.titulo = obj.getTitulo();
        this.observacoes = obj.getObservacoes();
        this.abertura = obj.getAbertura();
        this.fechamento = obj.getFechamento();
        this.tecnico = obj.getTecnico().getId();
        this.cliente = obj.getCliente().getId();
        this.nomeTecnico = obj.getTecnico().getNome();
        this.nomeCliente = obj.getCliente().getNome();
    }
}
