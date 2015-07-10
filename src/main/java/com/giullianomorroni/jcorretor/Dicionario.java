package com.giullianomorroni.jcorretor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import org.apache.log4j.Logger;

import com.giullianomorroni.jcorretor.unzip.CompressorArquivo;

/**
 * 
 * @author giulliano.morroni
 *
 */
public class Dicionario {

	private Logger log = Logger.getLogger(this.getClass());
	private static Boolean inicializada = false;
	
	private static List<String> A = new ArrayList<String>();
	private static List<String> B = new ArrayList<String>();
	private static List<String> C = new ArrayList<String>();
	private static List<String> D = new ArrayList<String>();
	private static List<String> E = new ArrayList<String>();
	private static List<String> F = new ArrayList<String>();
	private static List<String> G = new ArrayList<String>();
	private static List<String> H = new ArrayList<String>();
	private static List<String> I = new ArrayList<String>();
	private static List<String> J = new ArrayList<String>();
	private static List<String> L = new ArrayList<String>();
	private static List<String> M = new ArrayList<String>();
	private static List<String> N = new ArrayList<String>();
	private static List<String> O = new ArrayList<String>();
	private static List<String> P = new ArrayList<String>();
	private static List<String> Q = new ArrayList<String>();
	private static List<String> R = new ArrayList<String>();
	private static List<String> S = new ArrayList<String>();
	private static List<String> T = new ArrayList<String>();
	private static List<String> U = new ArrayList<String>();
	private static List<String> V = new ArrayList<String>();
	private static List<String> X = new ArrayList<String>();
	private static List<String> Z = new ArrayList<String>();

	private static List<String> Y = new ArrayList<String>();
	private static List<String> K = new ArrayList<String>();
	private static List<String> W = new ArrayList<String>();

	/**
	 * Realiza a carga de palavras para os dicionários 
	 */
	public void inicializar() {
		if (inicializada)
			return;

		try {
			/* todos os elementos precisam ser ordenados na ordem natural @see Collections.binarySearch(List, key) */
			 FutureTask<Boolean> future = new FutureTask<Boolean>(new Callable<Boolean>() {
		         public Boolean call() {
		           Collections.sort(A);
		           Collections.sort(B);
		           Collections.sort(C);
		           Collections.sort(D);
		           Collections.sort(E);
		           Collections.sort(F);
		           Collections.sort(G);
		           Collections.sort(H);
		           Collections.sort(I);
		           Collections.sort(J);
		           Collections.sort(L);
		           Collections.sort(M);
		           Collections.sort(N);
		           Collections.sort(O);
		           Collections.sort(P);
		           Collections.sort(Q);
		           Collections.sort(R);
		           Collections.sort(S);
		           Collections.sort(T);
		           Collections.sort(U);
		           Collections.sort(V);
		           Collections.sort(X);
		           Collections.sort(Z);
		           Collections.sort(Y);
		           Collections.sort(K);
		           Collections.sort(W);
		           return true;
		       }});
			 ExecutorService executor = Executors.newFixedThreadPool(1);

			 ClassLoader classLoader = getClass().getClassLoader();
			 File zip = new File(classLoader.getResource("dicionarios/dicionarios_pt_BR.zip").getFile());
			 List<File> arquivos = CompressorArquivo.unZipIt(zip.getAbsolutePath(), "/tmp");
			 for (File dicionario : arquivos) {
				 BufferedReader in = new BufferedReader(new FileReader(dicionario));
				 List<String> tmp = new ArrayList<String>();
				 for(String palavra = ""; palavra != null; palavra = in.readLine()){
					 tmp.add(palavra);
				 }
				 in.close();
				 adicionarDicionario(dicionario, tmp);
			 }

			 executor.execute(future);
			 inicializada = future.get();
		} catch (Exception e) {
			log.error(e);
		}
	}

	private void adicionarDicionario(File dicionario, List<String> tmp) {
		String name = dicionario.getName();
		switch (name) {
		case "pt_BR_A.dic": A.addAll(tmp); break;
		case "pt_BR_B.dic": B.addAll(tmp); break;
		case "pt_BR_C.dic": C.addAll(tmp); break;
		case "pt_BR_D.dic": D.addAll(tmp); break;
		case "pt_BR_E.dic": E.addAll(tmp); break;
		case "pt_BR_F.dic": F.addAll(tmp); break;
		case "pt_BR_G.dic": G.addAll(tmp); break;
		case "pt_BR_H.dic": H.addAll(tmp); break;
		case "pt_BR_I.dic": I.addAll(tmp); break;
		case "pt_BR_J.dic": J.addAll(tmp); break;
		case "pt_BR_L.dic": L.addAll(tmp); break;
		case "pt_BR_M.dic": M.addAll(tmp); break;
		case "pt_BR_N.dic": N.addAll(tmp); break;
		case "pt_BR_O.dic": O.addAll(tmp); break;
		case "pt_BR_P.dic": P.addAll(tmp); break;
		case "pt_BR_Q.dic": Q.addAll(tmp); break;
		case "pt_BR_R.dic": R.addAll(tmp); break;
		case "pt_BR_S.dic": S.addAll(tmp); break;
		case "pt_BR_T.dic": T.addAll(tmp); break;
		case "pt_BR_U.dic": U.addAll(tmp); break;
		case "pt_BR_V.dic": V.addAll(tmp); break;
		case "pt_BR_X.dic": X.addAll(tmp); break;
		case "pt_BR_Z.dic": Z.addAll(tmp); break;
		case "pt_BR_Y.dic": Y.addAll(tmp); break;
		case "pt_BR_K.dic": K.addAll(tmp); break;
		case "pt_BR_W.dic": W.addAll(tmp); break;
		default: break;
		}
		tmp = null;
	}

	/**
	 * Retorna o dicionário baseado na primeira letra da palavra
	 * @param palavra
	 * @throws IllegalStateException
	 * @return
	 */
	public List<String> dicionario(String palavra) throws IllegalStateException {
		if (!inicializada) {
			throw new IllegalStateException("Inicialize o dicionário antes de utilizar");
		}

		if (palavra == null || palavra.isEmpty())
			return new ArrayList<String>();

		String primeiraLetra = palavra.substring(0, 1).toLowerCase();
		switch (primeiraLetra) {
		case "a": return A;
		case "b": return B;
		case "c": return C;
		case "d": return D;
		case "e": return E;
		case "f": return F;
		case "g": return G;
		case "h": return H;
		case "i": return I;
		case "j": return J;
		case "l": return L;
		case "m": return M;
		case "n": return N;
		case "o": return O;
		case "p": return P;
		case "q": return Q;
		case "r": return R;
		case "s": return S;
		case "t": return T;
		case "u": return U;
		case "v": return V;
		case "x": return X;
		case "z": return Z;
		case "y": return Y;
		case "k": return K;
		case "w": return W;
		default: return new ArrayList<String>();
		}
	}

}
