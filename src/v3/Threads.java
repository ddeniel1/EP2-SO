package v3;

import java.util.concurrent.ThreadLocalRandom;

public class Threads extends Thread {
	@SuppressWarnings("unused")
	private String ler;
	private String escrever = "MODIFICADO";

	@Override
	public void run() {
		try {
			if (this.getName().startsWith("leitor"))
				ComecaLeitor();
			else
				ComecaEscritor();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private void ComecaEscritor() throws InterruptedException {
		CriaThreads.mutex2.acquire();
		CriaThreads.fila = true;
		CriaThreads.mutex2.release();
		CriaThreads.escrevendo.acquire();
		runThread();
		CriaThreads.mutex2.acquire();
		CriaThreads.fila = false;
		CriaThreads.mutex2.release();
		CriaThreads.escrevendo.release();
	}

	private void ComecaLeitor() throws InterruptedException {
		
		CriaThreads.mutex.acquire();
		CriaThreads.setLeitores(1);
		if (CriaThreads.getLeitores() == 1) {
			while(CriaThreads.fila);
			CriaThreads.escrevendo.acquire();
		}
		CriaThreads.mutex.release();
		runThread();
		CriaThreads.mutex.acquire();
		CriaThreads.setLeitores(-1);

		if (CriaThreads.getLeitores() == 0) {
			CriaThreads.escrevendo.release();
		}
		CriaThreads.mutex.release();
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

	public void runThread() throws InterruptedException {
		ThreadLocalRandom generator = ThreadLocalRandom.current();
		String[] livro = Main_v3.getLivro();
		for (int i = 0; i < 100; i++) {
			int regex = generator.nextInt(0, 36242);
			String nova = livro[regex];
			if (this.getName().startsWith("leitor")) {
				this.ler = nova;
			} else {
				livro[regex] = escrever;
			}
		}
		sleep(1);
	}
}
