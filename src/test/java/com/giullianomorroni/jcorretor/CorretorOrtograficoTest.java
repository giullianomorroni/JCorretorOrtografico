package com.giullianomorroni.jcorretor;

import org.junit.Assert;
import org.junit.Test;

import com.giullianomorroni.jcorretor.CorretorOrtografico;
import com.giullianomorroni.jcorretor.SugestaoOrtografia;

public class CorretorOrtograficoTest {

	private CorretorOrtografico corretor = new CorretorOrtografico();;

	@Test
	public void fraseSemErro() {
		String texto = 
				"O dólar subiu em relação ao real nesta quarta-feira (8), em meio a preocupações com a fraqueza "
				+ "das bolsas da China, com investidores temendo que isso possa ser um sinal "
				+ "de desaceleração mais firme no país, que é a segunda maior economia do mundo. "
				+ "Além disso, o mercado continua preocupado com a crise da dívida da Grécia";
		SugestaoOrtografia correcao = corretor.corrigir(texto);
		Assert.assertFalse(correcao.getRevisar());
	}

	@Test
	public void fraseComErro() {
		String texto =  "O grupo de Despesus Pessoais foi o que teve maiur alta, de 1,63%. O item jogos de azar teve a maior variação neste grupo, de 30,80%";
		SugestaoOrtografia correcao = corretor.corrigir(texto);
		Assert.assertTrue(correcao.getRevisar());
		Assert.assertTrue(correcao.getTextoSugerido().size() == 2);
	}

	@Test
	public void fraseSemLetrasNaoDeveValidar() {
		String texto =  "30,80% [] ; ( ) ~ 789 765 & * ! @";
		SugestaoOrtografia correcao = corretor.corrigir(texto);
		Assert.assertFalse(correcao.getRevisar());
	}

}
