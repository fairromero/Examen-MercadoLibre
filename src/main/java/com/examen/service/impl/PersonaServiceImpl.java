package com.examen.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examen.model.Persona;
import com.examen.repository.PersonaRepository;
import com.examen.service.PersonaService;

@Service
public class PersonaServiceImpl implements PersonaService {

	@Autowired
	private PersonaRepository personaRepository;

	@Override
	public Persona findById(String adn) {
		// Buscar y devolver la persona por el ID
		return personaRepository.findByAdn(adn);
	}

	@Override
	public Boolean personaExiste(String adn) {
		// Verificar si la persona existe
		if (personaRepository.findByAdn(adn) == null) {
			return false;
		}
		return true;
	}

	@Override
	public Persona savePersona(Persona persona) {
		return personaRepository.save(persona);
	}

	@Override
	public BigDecimal contarPersonas(Boolean mutante) {
		return new BigDecimal(personaRepository.countPersonaByMutante(mutante));
	}
}