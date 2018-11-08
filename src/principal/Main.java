package principal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	private static String[] livro = null;

	public static void main(String[] args) throws IOException {

		// Leitura do livro
		String[] palavras = new String[36242];
		BufferedReader reader = new BufferedReader(new FileReader("bd.txt"));
		int k = 0;
		while (reader.ready()) {
			palavras[k] = reader.readLine();
			k++;
		}
		setLivro(palavras);
		reader.close();

		// Criacao das proporcoes
		principal.Threads nova = new principal.Threads();
		for (int i = 0, j = 100; i <= 100 && j >= 0; i++, j--) {
			nova.setPropX(i);
			nova.setPropY(j);
			nova.run();
			setLivro(palavras);
		}

	}

	public static void setLivro(String[] palavras) {
		livro = palavras;
	}
	public static String[] getLivro() {
		return livro;
	}

}
