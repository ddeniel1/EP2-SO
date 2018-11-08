package regiaoCritica;

import java.util.concurrent.ThreadLocalRandom;

public class CriaThread extends Thread {

	private String tipoThread;
	private String ler;
	private String escrever = "MODIFICADO";
	
	@Override
	public void run() {
		ThreadLocalRandom generator = ThreadLocalRandom.current();
		for (int i = 0; i < 100; i++) {
			runThread(generator.nextInt(0, 36241));
		}
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public CriaThread(String args) {
		if (args.equals("leitor"))
			criaLeitor();
		else
			criaEscritor();
	}

	private void criaLeitor() {
		tipoThread = "leitor";
	}

	private void criaEscritor() {
		tipoThread = "escritor";
	}

	public void runThread(int regex) {
		String [] livro = Main.getLivro();
		String nova = livro[regex];
		if (tipoThread.equals("leitor")) {
			this.ler = nova;
		} else {
			livro[regex] = escrever;
		}

	}
}
