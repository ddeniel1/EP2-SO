package v2;

import java.util.concurrent.ThreadLocalRandom;

public class Threads extends Thread {
	@SuppressWarnings("unused")
	private String ler;
	private String escrever = "MODIFICADO";

	@Override
	synchronized public void run() {
		ThreadLocalRandom generator = ThreadLocalRandom.current();

		if (this.getName().startsWith("leitor"))
			ComecaLeitor();
		else
			ComecaEscritor();
		for (int i = 0; i < 100; i++) {
			runThread(generator.nextInt(0, 36242));
		}
		if (this.getName().startsWith("leitor"))
			AcabaLeitor();
		else
			AcabaEscritor();
	}

	synchronized private void ComecaEscritor() {
		while (CriaThreads.isEscrevendo() || CriaThreads.isLendo()) {
			System.out.println("INFINITY");
			try {
				wait(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		CriaThreads.setEscrevendo(true);

	}

	synchronized private void ComecaLeitor() {
		while (CriaThreads.isMutex() || CriaThreads.isEscrevendo()) {
			try {
				wait(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		CriaThreads.setMutex(true);
		CriaThreads.setLeitores(CriaThreads.getLeitores() + 1);
		if (CriaThreads.getLeitores() >= 1) {
			CriaThreads.setLendo(true);
		}
		CriaThreads.setMutex(false);

	}

	synchronized private void AcabaLeitor() {
		while (CriaThreads.isMutex()) {
			try {
				wait(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		CriaThreads.setMutex(true);
		CriaThreads.setLeitores(CriaThreads.getLeitores() - 1);

		if (CriaThreads.getLeitores() == 0) {
			CriaThreads.setLendo(false);
		}

		notify();
		CriaThreads.setMutex(false);
	}

	synchronized private void AcabaEscritor() {
		notify();
		CriaThreads.setEscrevendo(false);
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
