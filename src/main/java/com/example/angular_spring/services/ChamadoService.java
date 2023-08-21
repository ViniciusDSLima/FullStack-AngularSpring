package com.example.angular_spring.services;

import com.example.angular_spring.DTO.ChamadoDTO;
import com.example.angular_spring.DTO.ClienteDTO;
import com.example.angular_spring.Domain.enums.Prioridade;
import com.example.angular_spring.Domain.enums.Status;
import com.example.angular_spring.Domain.models.Chamado;
import com.example.angular_spring.Domain.models.Cliente;
import com.example.angular_spring.Domain.models.Tecnico;
import com.example.angular_spring.exceptions.ObjectNotFoundException;
import com.example.angular_spring.repository.ChamadoRepository;
import com.fasterxml.jackson.core.json.async.NonBlockingJsonParser;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ChamadoService {

    private ChamadoRepository chamadoRepository;
    private TecnicoService tecnicoService;
    private ClienteService clienteService;

    public Chamado findById(Long id){
        Optional<Chamado> obj = chamadoRepository.findById(id);
        return obj.orElseThrow( () -> new ObjectNotFoundException("Objeto nao encontrado"));
    }

    public List<Chamado> findAll() {
        return chamadoRepository.findAll();
    }

    @Transactional
    public Chamado create(ChamadoDTO chamadoDTO) {
        return chamadoRepository.save(novoChamado(chamadoDTO));
    }

    private Chamado novoChamado(ChamadoDTO chamadoDTO){
        Tecnico tecnico = tecnicoService.findById(chamadoDTO.getTecnico());
        Cliente cliente = clienteService.findById(chamadoDTO.getCliente());

        Chamado chamado = new Chamado();
        if(chamadoDTO.getId() != null){
            chamado.setId(chamado.getId());
        }

        if(chamadoDTO.getStatus() == 2){
            chamado.setFechamento(LocalDate.now());
        }
        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);
        chamado.setPrioridade(Prioridade.toEnum(chamadoDTO.getPrioridade()));
        chamado.setStatus(Status.toEnum(chamadoDTO.getStatus()));
        chamado.setTitulo(chamadoDTO.getTitulo());
        chamado.setObservacoes(chamadoDTO.getObservacoes());

        return chamado;
    }
    @Transactional
    public Chamado update(Long id, ChamadoDTO chamadoDTO) {
        chamadoDTO.setId(id);
        Chamado oldObj = findById(id);
        oldObj = novoChamado(chamadoDTO);

        return chamadoRepository.save(oldObj);
    }
}
