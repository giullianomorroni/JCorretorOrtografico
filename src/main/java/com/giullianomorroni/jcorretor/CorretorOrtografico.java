package com.giullianomorroni.jcorretor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 
 * @author giulliano.morroni
 * @see http://www.ime.usp.br/~ueda/br.ispell/
 */
public class CorretorOrtografico {

	private SugestaoOrtografia sugestao = new SugestaoOrtografia();
	private static String DESCONHECIDA = "?";
	private static String VIRGULA = ", ";
	private static Dicionario dicionarios = new Dicionario();

	/**
	 * Por hora esta lendo somente um dicionário pt_BR
	 * Necessita de refatoração para outras línguas.
	 * @throws IOException
	 */
	public CorretorOrtografico() {
		dicionarios.inicializar();
	}

	/**
	 * 
	 * @param texto
	 * @return
	 */
	public final SugestaoOrtografia corrigir(String texto) {
		if (texto  == null)
			return sugestao;

		String[] palavraPorPalavra = texto.split(" ");
		this.sugestao.setTextoOriginal(texto);
		for (String palavra : palavraPorPalavra) {
			palavra = palavra.toLowerCase();

			//remove qq coisa diferente de letras
			Pattern diferenteDeLetras = Pattern.compile("[^a-záàâãéèêíïóôõöúçñ]+$");
			palavra = palavra.replaceAll(diferenteDeLetras.pattern(), "");
			if (palavra.length() <= 2)
				continue;

			String palavraRevisada = avaliar(palavra.toLowerCase());
			this.sugestao.adicionarPalavraRevisada(palavra, palavraRevisada);
		}
		return this.sugestao;
	}

	private final ArrayList<String> variacoes(String word) {
		ArrayList<String> result = new ArrayList<String>();
		for (int i=0; i < word.length(); ++i){
			result.add(word.substring(0, i) + word.substring(i+1));
		}
		for (int i=0; i < word.length()-1; ++i) {
			result.add(word.substring(0, i) + word.substring(i+1, i+2) + word.substring(i, i+1) + word.substring(i+2));
		}
		for (int i=0; i < word.length(); ++i) {
			for(char c='a'; c <= 'z'; ++c) {
				result.add(word.substring(0, i) + String.valueOf(c) + word.substring(i+1));
			}
		}
		for (int i=0; i <= word.length(); ++i) {
			for (char c='a'; c <= 'z'; ++c) {
				result.add(word.substring(0, i) + String.valueOf(c) + word.substring(i));
			}
		}
		return result;
	}

	private String avaliar(String palavra) {
		if (palavra == null || palavra.isEmpty())
			return palavra;

		if (Collections.binarySearch(dicionarios.dicionario(palavra), palavra) >= 0){
			return null;
		}

		final List<String> candidatas = new ArrayList<String>();
		sugestao.setRevisar(true);
		final List<String> listaVariacaoes = variacoes(palavra);
		for(String s : listaVariacaoes) {
			if(Collections.binarySearch(dicionarios.dicionario(s), s) >= 0) {
				candidatas.add(s);
			}
		}

		if (candidatas.size() > 0) {
			List<String> melhoresSugestoes = new Levenshtein().choiceBestOption(candidatas, palavra);
			if (!melhoresSugestoes.isEmpty()) {
				return melhoresSugestoes.get(0);
			}	
		}

		List<String> melhoresSugestoes = new Levenshtein().choiceBestOption(dicionarios.dicionario(palavra), palavra);
		if (!melhoresSugestoes.isEmpty()) {
			String resultado = "";
			if (melhoresSugestoes.size() >= 3) {
				resultado = melhoresSugestoes.get(0)+VIRGULA+melhoresSugestoes.get(1)+VIRGULA+melhoresSugestoes.get(2);
			} else if (melhoresSugestoes.size() >= 2) {
				resultado = melhoresSugestoes.get(0)+VIRGULA+melhoresSugestoes.get(1);			
			} else if (melhoresSugestoes.size() > 1) {
				resultado = melhoresSugestoes.get(0);
			}
			return resultado;
		}
		return DESCONHECIDA;
	}

	public static void main(String args[]) throws IOException {
		CorretorOrtografico corretorOrtografico = new CorretorOrtografico();
		SugestaoOrtografia correct = corretorOrtografico.corrigir("estta8");
		System.out.println(correct);
	}

}
