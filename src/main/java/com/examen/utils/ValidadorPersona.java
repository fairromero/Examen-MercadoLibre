package com.examen.utils;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.examen.dto.Adn;
import com.examen.model.Persona;
import com.examen.service.PersonaService;

/**
 * Clase encargada de determinar si es muatnte o humano
 * @author FairRodrigo
 *
 */
@Component
public class ValidadorPersona {

	private List<String> buscar = Arrays.asList("AAAA", "TTTT", "GGGG", "CCCC");

	@Autowired
	private PersonaService personaService;

	private ValidadorADN validadorADN = new ValidadorADN();
	
	

	public PersonaService getPersonaService() {
		return personaService;
	}

	/**
	 * Determina si es mutante o humano
	 * @param muestra
	 * @return
	 */
	public Persona isMutante(Adn muestra) {
		try {
			long total = 0;
			Persona persona = new Persona();

			muestra.setDnaHorizontal(muestra.getDna());

			// vertical
			muestra.setDnaVertical(validadorADN.recorridoVertical(muestra));

			// diagonales
			muestra.setDnaDiagonalUno(validadorADN.recorridoDiagonalUno(muestra));
			muestra.setDnaDiagonalDos(validadorADN.recorridoDiagonalDos(muestra));
			muestra.setDnaDiagonalTres(validadorADN.recorridoDiagonalTres(muestra));
			muestra.setDnaDiagonalCuatro(validadorADN.recorridoDiagonalCuatro(muestra));

			long horizontal = muestra.getDnaHorizontal().stream()
					.filter(e -> buscar.stream().filter(w -> e.contains(w)).count() > 0)
					.peek(e -> System.out.printf("total : %s %n", e)).count();

			long vertical = muestra.getDnaVertical().stream()
					.filter(e -> buscar.stream().filter(w -> e.contains(w)).count() > 0)
					.peek(e -> System.out.printf("total : %s %n", e)).count();

			long diagonalUno = muestra.getDnaDiagonalUno().stream()
					.filter(e -> buscar.stream().filter(w -> e.contains(w)).count() > 0)
					.peek(e -> System.out.printf("total : %s %n", e)).count();

			long diagonalDos = muestra.getDnaDiagonalDos().stream()
					.filter(e -> buscar.stream().filter(w -> e.contains(w)).count() > 0)
					.peek(e -> System.out.printf("total : %s %n", e)).count();

			long diagonalTres = muestra.getDnaDiagonalTres().stream()
					.filter(e -> buscar.stream().filter(w -> e.contains(w)).count() > 0)
					.peek(e -> System.out.printf("total : %s %n", e)).count();

			long diagonalCuatro = muestra.getDnaDiagonalCuatro().stream()
					.filter(e -> buscar.stream().filter(w -> e.contains(w)).count() > 0)
					.peek(e -> System.out.printf("total : %s %n", e)).count();

			total = horizontal + vertical + diagonalUno + diagonalDos + diagonalTres + diagonalCuatro;
			if (total >= 2) {
				persona.setMutante(true);
				persona.setAdn(muestra.getDna().toString());
			} else {
				persona.setAdn(muestra.getDna().toString());
				persona.setMutante(false);
			}
			return persona;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Realiza el registro en BD de la persona
	 * @param persona
	 * @return
	 */
	public Persona guardarPersona(Persona persona) {
		try {
			// Verifico si existe la persona
			Persona p = personaService.findById(persona.getAdn());
			// Si la persona existe no se guarda ni se actualiza la estadística
			if (p == null) {
				// Guardo la nueva persona
				return personaService.savePersona(persona);
			}
			return p;
		} catch (Exception ex) {
			return null;
		}
	}

}
