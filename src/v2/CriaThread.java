package v2;

import java.util.concurrent.ThreadLocalRandom;

public class CriaThread extends Thread {

	private String ler;
	private String escrever = "MODIFICADO";
	
	@Override
	public void run() {
		ThreadLocalRandom generator = ThreadLocalRandom.current();
		for (int i = 0; i < 100; i++) {
			runThread(generator.nextInt(0, 36242));
		}
		try {
			Thread.sleep(1);
			if(this.getName().equals("leitor")) Threads.setLeitores(Threads.getLeitores()-1);
			else Threads.setEscritores(Threads.getEscritores()-1);
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
		this.setName("leitor");
	}

	private void criaEscritor() {
		this.setName("escritor");
	}

	public void runThread(int regex) {
		String [] livro = Main.getLivro();
		String nova = livro[regex];
		if (this.getName().equals("leitor")) {
			this.ler = nova;
		} else {
			livro[regex] = escrever;
		}

	}
}
