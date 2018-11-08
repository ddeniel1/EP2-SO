package principal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {

		// Leitura do livro
		String[] livro = new String[36242];
		BufferedReader reader = new BufferedReader(new FileReader("bd.txt"));
		int k = 0;
		while (reader.ready()) {
			livro[k] = reader.readLine();
			k++;
		}
		reader.close();

		// Criacao das proporcoes
		principal.Threads nova = new principal.Threads();
		for (int i = 0, j = 100; i <= 100 && j >= 0; i++, j--) {
			nova.setPropX(i);
			nova.setPropY(j);
		}

	}

}
