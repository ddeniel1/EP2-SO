import java.io.FileNotFoundException;
import java.io.IOException;

import preferencia_Escritor.*;
import preferencia_Leitor.*;
import regiaoCritica.*;

public class Testes {

	public static void main(String[] args) throws IOException, InterruptedException {
		try {
			// Marca o tempo pra rodar todos os testes
			long inicio = System.currentTimeMillis();
			// chama todos os testes
			Main_RC.main(args);
			Main.main(args);
			Main_v3.main(args);
			long fim = System.currentTimeMillis();
			long tempo = (fim - inicio);
			System.out.println("Tempo total: " + tempo);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage().startsWith("entrada")
					? "Eh necessario que o arquivo bd.txt esteja dentro de uma pasta chamada entrada na raiz do programa"
					: "Eh necessario que uma pasta chamada log_de_saida esteja presente na raiz do programa");
		}
	}

}
