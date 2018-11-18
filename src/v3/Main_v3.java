package v3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class Main_v3 {
	private static String[] livro = null;
	private static PrintWriter writer;
	private static FileWriter writer_arq;

	public static void main(String[] args) throws IOException {

		// Leitura do livro
		String[] palavras = new String[36242];
		BufferedReader reader = new BufferedReader(new FileReader("entrada/bd.txt"));
		int k = 0;
		while (reader.ready()) {
			palavras[k] = reader.readLine();
			k++;
		}
		setLivro(palavras);
		reader.close();
		writer = new PrintWriter("log_de_saida/log_implementação_3.txt", "UTF-8");
		writer_arq = new FileWriter("log_de_saida/log_3.csv");
		// Criacao das proporcoes
		CriaThreads nova = new CriaThreads();
		writer_arq.append("Leitores,Escritores,Tempo,Version\n");
		for (int j = 0, i = 100; j <= 100 && i >= 0; j++, i--) {
			writer.println("Leitores: " + i);
			writer_arq.append(i+","+j+",");
			writer.println("Escritores: " + j);
			nova.setPropX(i);
			nova.setPropY(j);
			nova.run();
			setLivro(palavras);
			writer_arq.append(",3\n");
			writer.println();
		}
		writer.close();
		writer_arq.flush();
		writer_arq.close();

	}

	public static void escreve(String escreve) {
		writer.println(escreve);
		try {
			writer_arq.append(""+escreve.substring(13, 18));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void setLivro(String[] palavras) {
		livro = new String[palavras.length];
		for (int i = 0; i < palavras.length; i++) {
			livro[i] = palavras[i];
		}
	}

	public static String[] getLivro() {
		return livro;
	}

}
