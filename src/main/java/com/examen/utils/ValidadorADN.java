package com.examen.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.examen.dto.Adn;
import com.google.gson.GsonBuilder;

/**
 * Clase encargada de realizar las valiadaciones de las cadenas de ADN de las personas
 * 
 * @author FairRodrigo
 *
 */
@Component
public class ValidadorADN {

	/**
	 * Convierte un String de JSON en un array de Strings
	 * 
	 * @param dna
	 * @return
	 */
	public Adn parseJson(String dna) {
		Adn adn = null;
		try {
			adn = new GsonBuilder().create().fromJson(dna, Adn.class);
			return adn;
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * Verificar que la matriz ingresada cumpla con ciertos requisitos básicos antes
	 * de verificar si es mutante o no
	 * 
	 * @param adn
	 * @return
	 */
	public boolean verificarMatriz(Adn adn) {
		return validarRangoMinimo(adn) && verificarCaracteres(adn) && validarMatrizCuadrada(adn);
	}

	/**
	 * Verifica que sea una matriz con la que se pueda trabajar
	 * 
	 * @param adn
	 * @return
	 */
	private boolean validarRangoMinimo(Adn adn) {
		// Si la matriz no es de al menos 4x4 no se puede analizar
		return adn.getDna().size() > 3;
	}

	/**
	 * Valida que sea una matriz de NxN
	 * 
	 * @param adn
	 * @return
	 */
	private boolean validarMatrizCuadrada(Adn adn) {
		// Si la cantidad de cadenas es diferente al largo de las cadenas entonces no es
		// una matriz cuadrada
		int filas = adn.getDna().size();
		for (String s : adn.getDna()) {
			if (s.length() != filas) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Verificar que se hayan ingresado solo caracteres permitidos
	 * 
	 * @param adn
	 * @return
	 */
	private boolean verificarCaracteres(Adn adn) {
		String permitidos = "[ATCG]+";
		// Si se ingresa un caracter diferente a los permitidos (A, T, C , G)
		for (String s : adn.getDna())
			if (!s.matches(permitidos)) {
				return false;
			}

		return true;
	}

	/**
	 * Efectua el recorrido en forma vertical
	 * 
	 * @param muestra
	 * @return
	 */
	public List<String> recorridoVertical(Adn muestra) {
		List<String> dnaVertical = new ArrayList<String>();
		for (int i = 0; i < muestra.getDnaHorizontal().size(); i++) {
			StringBuilder cadena = new StringBuilder();
			for (int j = 0; j < muestra.getDnaHorizontal().get(i).length(); j++) {
				cadena.append(muestra.getDnaHorizontal().get(j).charAt(i));
			}
			if (cadena.length() > 0) {
				dnaVertical.add(cadena.toString());
			}
		}
		return dnaVertical;
	}

	/**
	 * Efectua el recorrido en para la diagonal tipo 1
	 * 
	 * @param muestra
	 * @return
	 */
	public List<String> recorridoDiagonalUno(Adn muestra) {
		List<String> dnaDiagonalUno = new ArrayList<String>();
		int x = 0;
		for (int i = 0; i < muestra.getDnaHorizontal().size(); i++) {
			StringBuilder cadena = new StringBuilder();
			int y = muestra.getDnaHorizontal().size() - 1;
			while (x < muestra.getDnaHorizontal().size() && y >= 0) {
				cadena.append(muestra.getDnaHorizontal().get(x).charAt(y));
				x++;
				y--;
			}
			if (cadena.length() > 0) {
				dnaDiagonalUno.add(cadena.toString());
			}
			x = i + 1;
		}
		return dnaDiagonalUno;
	}

	/**
	 * Efectua el recorrido en para la diagonal tipo 2
	 * 
	 * @param muestra
	 * @return
	 */
	public List<String> recorridoDiagonalDos(Adn muestra) {
		List<String> dnaDiagonalDos = new ArrayList<String>();
		int x = 0;
		for (int i = 0; i < muestra.getDnaHorizontal().size(); i++) {
			StringBuilder cadena = new StringBuilder();
			int y = 0;
			while (x < muestra.getDnaHorizontal().size() && y < muestra.getDnaHorizontal().size()) {
				cadena.append(muestra.getDnaHorizontal().get(x).charAt(y));
				x++;
				y++;
			}
			if (cadena.length() > 0) {
				dnaDiagonalDos.add(cadena.toString());
			}
			x = i + 1;
		}
		return dnaDiagonalDos;
	}

	/**
	 * Efectua el recorrido en para la diagonal tipo 3
	 * 
	 * @param muestra
	 * @return
	 */
	public List<String> recorridoDiagonalTres(Adn muestra) {
		List<String> dnaDiagonalTres = new ArrayList<String>();
		int x = 0;
		int y = 0;
		for (int i = 0; i < muestra.getDnaHorizontal().size(); i++) {
			StringBuilder cadena = new StringBuilder();
			y = i + 1;
			while (x < muestra.getDnaHorizontal().size() && y < muestra.getDnaHorizontal().size()) {
				cadena.append(muestra.getDnaHorizontal().get(x).charAt(y));
				x++;
				y++;
			}
			if (cadena.length() > 0) {
				dnaDiagonalTres.add(cadena.toString());
			}
			x = 0;
		}
		return dnaDiagonalTres;
	}

	/**
	 * Efectua el recorrido en para la diagonal tipo 4
	 * 
	 * @param muestra
	 * @return
	 */
	public List<String> recorridoDiagonalCuatro(Adn muestra) {
		List<String> dnaDiagonalCuatro = new ArrayList<String>();
		int x = 0;
		int y = 1;
		for (int i = muestra.getDnaHorizontal().size() - 1; i > 0; i--) {
			StringBuilder cadena = new StringBuilder();
			y = i - 1;
			while (x < muestra.getDnaHorizontal().size() && y >= 0) {
				cadena.append(muestra.getDnaHorizontal().get(x).charAt(y));
				x++;
				y--;
			}
			if (cadena.length() > 0) {
				dnaDiagonalCuatro.add(cadena.toString());
			}
			x = 0;
		}
		return dnaDiagonalCuatro;
	}

}
