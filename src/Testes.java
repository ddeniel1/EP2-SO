import java.io.IOException;

import Preferencia_Leitor.*;
import preferencia_Escritor.*;
import regiaoCritica.*;
public class Testes {

	public static void main(String[] args) throws IOException, InterruptedException {
		long inicio = System.currentTimeMillis();
		Main_RC.main(args);
		Main.main(args);
		Main_v3.main(args);
		long fim = System.currentTimeMillis();
		long tempo = (fim - inicio);
		System.out.println("Tempo total: "+tempo);
	}

}
