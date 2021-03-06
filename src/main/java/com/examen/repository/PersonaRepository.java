package com.examen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.examen.model.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, String> {

	@Query("SELECT p FROM Persona p WHERE LOWER(p.adn) = LOWER(:adn)")
	public Persona findByAdn(@Param("adn") String adn);

	Long countPersonaByMutante(Boolean mutante);
}
