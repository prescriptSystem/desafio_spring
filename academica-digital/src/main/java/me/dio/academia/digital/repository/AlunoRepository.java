package me.dio.academia.digital.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import me.dio.academia.digital.entity.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
	
	List<Aluno> findByDataDeNascimento(LocalDate dataDeNascimento);

}
