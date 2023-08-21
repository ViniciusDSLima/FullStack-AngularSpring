package com.example.angular_spring.services;

import com.example.angular_spring.DTO.ClienteDTO;
import com.example.angular_spring.DTO.TecnicoDTO;
import com.example.angular_spring.Domain.models.Cliente;
import com.example.angular_spring.Domain.models.Pessoa;
import com.example.angular_spring.Domain.models.Tecnico;
import com.example.angular_spring.exceptions.ObjectNotFoundException;
import com.example.angular_spring.repository.ClienteRepository;
import com.example.angular_spring.repository.PessoaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClienteService {

    private ClienteRepository clienteRepository;
    private PessoaRepository pessoaRepository;


    public Cliente findById(Long id) {
        return clienteRepository.findById(id).orElseThrow( () ->
                new ObjectNotFoundException("Objeto nao encontrado no banco de dados" + id));
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente create(ClienteDTO clienteDTO) {
        clienteDTO.setId(null);
        validaPorCpfEmail(clienteDTO);
        Cliente newObj = new Cliente(clienteDTO);
        return clienteRepository.save(newObj);
    }

    @Transactional
    public Cliente update(Long id, @Valid ClienteDTO clienteDTO) {
        clienteDTO.setId(id);
        Cliente oldObj = findById(id);

        validaPorCpfEmail(clienteDTO);
        oldObj = new Cliente(clienteDTO);

        return clienteRepository.save(oldObj);
    }

    private void validaPorCpfEmail(ClienteDTO clienteDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(clienteDTO.getCpf());
        if(obj.isPresent() && obj.get().getId() != clienteDTO.getId()){
            throw new DataIntegrityViolationException("CPF ja cadastrado no sistema");
        }

        obj = pessoaRepository.findByEmail(clienteDTO.getEmail());
        if(obj.isPresent() && obj.get().getId() != clienteDTO.getId()){
            throw new DataIntegrityViolationException("Email ja cadastrado no sistema");
        }
    }


    public void delete(Long id) {
        Cliente obj = findById(id);

        if(obj.getChamados().size() > 0){
            throw new DataIntegrityViolationException("Tecnico possui ordens de servico e nao podem ser deletados");
        }
        clienteRepository.deleteById(id);
    }
}
