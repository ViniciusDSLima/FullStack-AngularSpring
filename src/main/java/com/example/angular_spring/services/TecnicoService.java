package com.example.angular_spring.services;

import com.example.angular_spring.DTO.TecnicoDTO;
import com.example.angular_spring.Domain.models.Pessoa;
import com.example.angular_spring.Domain.models.Tecnico;
import com.example.angular_spring.exceptions.ObjectNotFoundException;
import com.example.angular_spring.repository.PessoaRepository;
import com.example.angular_spring.repository.TecnicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TecnicoService {

    private TecnicoRepository tecnicoRepository;
    private PessoaRepository pessoaRepository;


    public Tecnico findById(Long id) {
        return tecnicoRepository.findById(id).orElseThrow( () ->
                new ObjectNotFoundException("Objeto nao encontrado no banco de dados" + id));
    }

    public List<Tecnico> findAll() {
        return tecnicoRepository.findAll();
    }

    public Tecnico create(TecnicoDTO tecnicoDTO) {
        tecnicoDTO.setId(null);
        validaPorCpfEmail(tecnicoDTO);
        Tecnico newObj = new Tecnico(tecnicoDTO);
        return tecnicoRepository.save(newObj);
    }

    @Transactional
    public Tecnico update(Long id, @Valid TecnicoDTO tecnicoDTO) {
        tecnicoDTO.setId(id);
        Tecnico oldObj = findById(id);

        validaPorCpfEmail(tecnicoDTO);
        oldObj = new TecnicoDTO(tecnicoDTO);

        return tecnicoRepository.save(oldObj);
    }

    private void validaPorCpfEmail(TecnicoDTO tecnicoDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(tecnicoDTO.getCpf());
        if(obj.isPresent() && obj.get().getId() != tecnicoDTO.getId()){
            throw new DataIntegrityViolationException("CPF ja cadastrado no sistema");
        }

        obj = pessoaRepository.findByEmail(tecnicoDTO.getEmail());
        if(obj.isPresent() && obj.get().getId() != tecnicoDTO.getId()){
            throw new DataIntegrityViolationException("Email ja cadastrado no sistema");
        }
    }


    public void delete(Long id) {
        Tecnico obj = findById(id);

        if(obj.getChamados().size() > 0){
            throw new DataIntegrityViolationException("Tecnico possui ordens de servico e nao podem ser deletados");
        }
        tecnicoRepository.deleteById(id);
    }
}
