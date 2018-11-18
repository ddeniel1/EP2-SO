package Preferencia_Leitor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
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
		writer = new PrintWriter("log_de_saida/log_implementação_2.txt", "UTF-8");
		writer_arq = new FileWriter("log_de_saida/log_2.csv");
		// Criacao das proporcoes
		CriaThreads nova = new CriaThreads();
		writer_arq.append("Leitores,Escritores,Tempo,Version\n");
		for (int j = 0, i = 100; j <= 100 && i >= 0; j++, i--) {
			nova = new CriaThreads();
			writer.println("Leitores: " + i);
			writer_arq.append(i+","+j+",");
			writer.println("Escritores: " + j);
			nova.setPropX(i);
			nova.setPropY(j);
			nova.run();
			setLivro(palavras);
			writer_arq.append(",2\n");
			writer.println();
		}
		writer.close();
		writer_arq.flush();
		writer_arq.close();

	}

	public static void escreve(String escreve) throws NumberFormatException, IOException {
		writer.println(escreve);
		writer_arq.append(""+escreve.substring(13, 18));
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
