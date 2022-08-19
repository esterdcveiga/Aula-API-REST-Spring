package com.aula.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.aula.entities.Compromisso;

@Repository
public interface CompromissoRepository extends JpaRepository<Compromisso, Long> {
	@Query("select cp from Compromisso cp, Contato ct where cp.contato = ct.id and ct.nome = ?1")
	List<Compromisso> consultaPorNomeContato(String nome);
}
