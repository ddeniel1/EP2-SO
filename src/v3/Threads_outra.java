package v3;

import java.util.concurrent.ThreadLocalRandom;

public class Threads_outra extends Thread {
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
		CriaThreads.setEscritores(1);
		if (CriaThreads.getEscritores() == 1)
			CriaThreads.lendo.acquire();
		CriaThreads.mutex2.release();
		CriaThreads.escrevendo.acquire();
		runThread();
		CriaThreads.escrevendo.release();
		CriaThreads.mutex2.acquire();
		CriaThreads.setEscritores(-1);
		if (CriaThreads.getEscritores() == 0)
			CriaThreads.lendo.release();
		CriaThreads.mutex2.release();
	}

	private void ComecaLeitor() throws InterruptedException {
		CriaThreads.lendo.acquire();
		CriaThreads.mutex.acquire();
		CriaThreads.setLeitores(1);
		if (CriaThreads.getLeitores() == 1) {
			CriaThreads.escrevendo.acquire();
		}
		CriaThreads.mutex.release();
		CriaThreads.lendo.release();
		runThread();
		CriaThreads.mutex.acquire();
		CriaThreads.setLeitores(-1);

		if (CriaThreads.getLeitores() == 0) {
			CriaThreads.escrevendo.release();
		}
		CriaThreads.mutex.release();
	}

	public Threads_outra(String args) {
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
