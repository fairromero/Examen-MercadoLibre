package com.examen.dto;

import java.util.List;

public class Adn {

	private List<String> dna;

	private List<String> dnaHorizontal;
	private List<String> dnaVertical;
	private List<String> dnaDiagonalUno;
	private List<String> dnaDiagonalDos;
	private List<String> dnaDiagonalTres;
	private List<String> dnaDiagonalCuatro;

	public List<String> getDnaHorizontal() {
		return dnaHorizontal;
	}

	public void setDnaHorizontal(List<String> dnaHorizontal) {
		this.dnaHorizontal = dnaHorizontal;
	}

	public List<String> getDnaVertical() {
		return dnaVertical;
	}

	public void setDnaVertical(List<String> dnaVertical) {
		this.dnaVertical = dnaVertical;
	}

	public List<String> getDnaDiagonalUno() {
		return dnaDiagonalUno;
	}

	public void setDnaDiagonalUno(List<String> dnaDiagonalUno) {
		this.dnaDiagonalUno = dnaDiagonalUno;
	}

	public List<String> getDnaDiagonalDos() {
		return dnaDiagonalDos;
	}

	public void setDnaDiagonalDos(List<String> dnaDiagonalDos) {
		this.dnaDiagonalDos = dnaDiagonalDos;
	}

	public List<String> getDnaDiagonalTres() {
		return dnaDiagonalTres;
	}

	public void setDnaDiagonalTres(List<String> dnaDiagonalTres) {
		this.dnaDiagonalTres = dnaDiagonalTres;
	}

	public List<String> getDnaDiagonalCuatro() {
		return dnaDiagonalCuatro;
	}

	public void setDnaDiagonalCuatro(List<String> dnaDiagonalCuatro) {
		this.dnaDiagonalCuatro = dnaDiagonalCuatro;
	}

	public List<String> getDna() {
		return dna;
	}

	public void setDna(List<String> dna) {
		this.dna = dna;
	}

}