package com.example.angular_spring.repository;

import com.example.angular_spring.Domain.models.Chamado;
import com.example.angular_spring.Domain.models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChamadoRepository extends JpaRepository<Chamado, Long> {
}
