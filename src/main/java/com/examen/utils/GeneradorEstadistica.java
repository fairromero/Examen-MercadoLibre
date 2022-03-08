package com.examen.utils;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import com.examen.dto.Estadistica;
import com.examen.service.PersonaService;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Clase encargada preparar las estadisticas del analisis de las perosnas
 * @author FairRodrigo
 *
 */
@Component
public class GeneradorEstadistica {

	@Autowired
	private PersonaService personaService;

	/**
	 * Construye la estadistica de humanos y mutantes
	 * @return
	 */
	public String obtenerEstadistica() {
		BigDecimal mutantes = personaService.contarPersonas(true);
		BigDecimal humanos = personaService.contarPersonas(false);
		String ratio = this.calcularRatio(mutantes, humanos);
		return ratio;
	}

	private String calcularRatio(BigDecimal mutantes, BigDecimal humanos) {
		Estadistica estadistica = new Estadistica();
		estadistica.setHumanos(humanos);
		estadistica.setMutantes(mutantes);
		estadistica.setRatio(BigDecimal.ZERO);

		if (humanos.compareTo(BigDecimal.ZERO) != 0 && mutantes.compareTo(BigDecimal.ZERO) != 0) {
			estadistica.setRatio(mutantes.divide(humanos, BigDecimal.ONE.intValue(), RoundingMode.HALF_EVEN));
			return estadistica.toString();
		} else {
			return estadistica.toString();
		}
	}
}
