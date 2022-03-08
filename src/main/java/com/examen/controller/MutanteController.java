package com.examen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.examen.dto.Adn;
import com.examen.model.Persona;
import com.examen.utils.GeneradorEstadistica;
import com.examen.utils.ValidadorADN;
import com.examen.utils.ValidadorPersona;

@RestController
@RequestMapping("/api")
public class MutanteController {

	@Autowired
	private ValidadorPersona validadorPersona;

	@Autowired
	private ValidadorADN validadorADN;

	@Autowired
	private GeneradorEstadistica generadorEstadistica;

	@RequestMapping(value = "/mutant", method = RequestMethod.POST)
	public ResponseEntity<String> mutant(@RequestBody String JsonAdn) {
		Adn adn = validadorADN.parseJson(JsonAdn);
		// valida si el objeto adn es nulo
		if (adn == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		// valida que lo ingresado cumpla con los requisitos básicos, de lo contrario
		// devuelvo un error
		if (!validadorADN.verificarMatriz(adn)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		// se valida si la persona es un mutante
		Persona persona = validadorPersona.isMutante(adn);
		// Guardo la persona en la base de datos, si falla devolver un error
		if (validadorPersona.guardarPersona(persona) == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Verificar conexión a la base de datos");
		}
		// determina si es mutante
		boolean resultado = (persona.getMutante()) ? true : false;
		if (resultado) {
			return ResponseEntity.status(HttpStatus.OK).body("Es Mutante");
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No es mutante");
		}
	}

	@RequestMapping(value = "/stats", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE + "; charset=UTF-8" })
	public String Estadisticas() {
		return generadorEstadistica.obtenerEstadistica();
	}
}