package com.giullianomorroni.jcorretor;

import java.util.ArrayList;
import java.util.List;

public class SugestaoOrtografia {

	private Boolean revisar = false;
	private String textoOriginal;
	private List<String> textoSugerido = new ArrayList<String>();

	public void adicionarPalavraRevisada(String palavraOriginal, String palavraRevisada) {
		if (palavraRevisada != null) {
			textoSugerido.add(palavraOriginal +"["+palavraRevisada+"]");
		}
	}

	public Boolean getRevisar() {
		return revisar;
	}

	public void setRevisar(Boolean revisar) {
		this.revisar = revisar;
	}

	public String getTextoOriginal() {
		return textoOriginal;
	}

	public void setTextoOriginal(String textoOriginal) {
		this.textoOriginal = textoOriginal;
	}

	public List<String> getTextoSugerido() {
		return textoSugerido;
	}

	public void setTextoSugerido(List<String> textoSugerido) {
		this.textoSugerido = textoSugerido;
	}

	@Override
	public String toString() {
		return "SugestaoOrtografia [revisar=" + revisar + ", textoOriginal="
				+ textoOriginal + ", textosSugeridos=" + textoSugerido + "]";
	}

}
