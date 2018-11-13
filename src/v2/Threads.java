package v2;

import java.util.concurrent.ThreadLocalRandom;

public class Threads extends Thread {
	@SuppressWarnings("unused")
	private String ler;
	private String escrever = "MODIFICADO";

	@Override
	synchronized public void run() {
		CriaThreads.setAlives(1);
		ThreadLocalRandom generator = ThreadLocalRandom.current();
		try {
			if (this.getName().startsWith("leitor"))
				ComecaLeitor();
			
			else
				ComecaEscritor();
			for (int i = 0; i < 100; i++) {
				runThread(generator.nextInt(0, 36242));
			}
			sleep(1);

			if (this.getName().startsWith("leitor"))
				AcabaLeitor();
			else
				AcabaEscritor();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	synchronized private void ComecaEscritor() throws InterruptedException {
		CriaThreads.escrevendo.acquire();
	}

	synchronized private void ComecaLeitor() throws InterruptedException {
		CriaThreads.mutex.acquire();
		CriaThreads.setLeitores(1);
		if (CriaThreads.getLeitores() == 1) {
			CriaThreads.escrevendo.acquire();
		}
		CriaThreads.mutex.release();
	}

	synchronized private void AcabaLeitor() throws InterruptedException {
		CriaThreads.mutex.acquire();
		CriaThreads.setLeitores(-1);

		if (CriaThreads.getLeitores() == 0) {
			CriaThreads.escrevendo.release();
		}
		CriaThreads.setAlives(-1);
		CriaThreads.mutex.release();
	}

	synchronized private void AcabaEscritor() {
		CriaThreads.setAlives(-1);
		CriaThreads.escrevendo.release();
	}

	public Threads(String args) {
		if (args.startsWith("leitor"))
			criaLeitor(args.substring(6));
		else
			criaEscritor(args.substring(8));
	}

	private void criaLeitor(String args) {
		this.setName("leitor" + args);
	}

	private void criaEscritor(String args) {
		this.setName("escritor" + args);
	}

	public void runThread(int regex) {
		String[] livro = Main.getLivro();
		String nova = livro[regex];
		if (this.getName().startsWith("leitor")) {
			this.ler = nova;
		} else {
			livro[regex] = escrever;
		}
	}
}
