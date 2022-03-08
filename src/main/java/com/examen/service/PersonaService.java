package com.examen.service;

import java.math.BigDecimal;

import com.examen.model.Persona;

public interface PersonaService {

	Persona findById(String adn);

	Boolean personaExiste(String id);

	Persona savePersona(Persona persona);

	BigDecimal contarPersonas(Boolean mutante);
}
