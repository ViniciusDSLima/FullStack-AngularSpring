package com.example.angular_spring.repository;

import com.example.angular_spring.Domain.models.Cliente;
import com.example.angular_spring.Domain.models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
