package v2;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class CriaThreads {
	private int propX;
	private int propY;
	public static int leitores = 0;
	public static Semaphore mutex = new Semaphore(1);
	public static Semaphore escrevendo = new Semaphore(1);

	public void run() {
		Threads[] arranjoDeThreads = new Threads[100];
		ThreadLocalRandom generator = ThreadLocalRandom.current();
		try {
			// Alocacao das threads no array
			for (int i = 0; i < 100; i++) {

				// Definicao de x para saber se eh leitor ou escritor.
				int x = 0;
				Threads novaThread = null;

				if (this.getPropX() > 0) {
					novaThread = new Threads("leitor " + i);
					this.setPropX(this.getPropX() - 1);
					x = generator.nextInt(0, 100);
					while (arranjoDeThreads[x] != null) {
						x = generator.nextInt(0, 100);
					}
					arranjoDeThreads[x] = novaThread;
				} else {
					novaThread = new Threads("escritor " + i);
					this.setPropY(this.getPropY() - 1);
					x = generator.nextInt(0, 100);
					while (arranjoDeThreads[x] != null) {
						x = generator.nextInt(0, 100);
					}
					arranjoDeThreads[x] = novaThread;
				}
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		long inicio = System.currentTimeMillis();

		// Roda as threads

		for (int i = 0; i < arranjoDeThreads.length; i++) {
			arranjoDeThreads[i].start();
		}

		for (int i = 0; i < arranjoDeThreads.length; i++) {
			try {
				arranjoDeThreads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		long fim = System.currentTimeMillis();

		// Escreve log de saida
		String tempo = "Tempo total: 0." + ((fim - inicio) >= 10
				? ((fim - inicio) >= 100 ? (fim - inicio) : ("0" + (fim - inicio))) : ("00" + (fim - inicio)))
				+ " segundos";

		Main.escreve(tempo);
		System.out.println(tempo);
	}

	public static int getLeitores() {
		return leitores;
	}

	public static void setLeitores(int leitoresa) {
		leitores += leitoresa;
	}

	public int getPropY() {
		return propY;
	}

	public void setPropY(int propY) {
		this.propY = propY;
	}

	public int getPropX() {
		return propX;
	}

	public void setPropX(int propX) {
		this.propX = propX;
	}
}
