package v2;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class CriaThreads {
	private int propX;
	private int propY;
	private static int alives = 0;
	private static int leitores = 0;
	public static Semaphore mutex = new Semaphore(1, true);
	public static Semaphore escrevendo = new Semaphore(2, true);

	public void run() {
		int propX = this.propX;
		int propY = this.propY;
		Threads[] arranjoDeThreads = new Threads[100];
		ThreadLocalRandom generator = ThreadLocalRandom.current();
		try {
			// Alocacao das threads no array
			for (int i = 0; i < 100; i++) {

				// Definicao de x para saber se eh leitor ou escritor.
				int x = generator.nextInt(1, 3);
				Threads novaThread = null;
				if (x == 1) {
					if (propX > 0) {
						novaThread = new Threads("leitor " + i);
						// novaThread.setPriority(2);
						propX--;
					} else {
						novaThread = new Threads("escritor " + i);
						// novaThread.setPriority(1);
						propY--;
					}
				} else if (x == 2) {
					if (propY > 0) {
						novaThread = new Threads("escritor " + i);
						// novaThread.setPriority(1);
						propY--;
					} else {
						novaThread = new Threads("leitor " + i);
						// novaThread.setPriority(2);
						propX--;
					}
				}
				x = generator.nextInt(0, 100);
				while (arranjoDeThreads[x] != null) {
					x = generator.nextInt(0, 100);
				}
				arranjoDeThreads[x] = novaThread;
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		long inicio = System.currentTimeMillis();

		// Roda as threads

		for (int i = 0; i < arranjoDeThreads.length; i++) {
			arranjoDeThreads[i].start();
		}

		while (alives > 0)System.out.print("");
		long fim = System.currentTimeMillis();

		// Escreve log de saida
		String tempo = "Tempo total: 0."
				+ ((fim - inicio) >= 10 ? ((fim - inicio) >= 100 ? (fim - inicio) : ("0" + (fim - inicio)))
						: ("00" + (fim - inicio)))
				+ " segundos";

		Main.escreve(tempo);
		System.out.println(tempo);

	}

	public int getPropY() {
		return propY;
	}

	public int getPropX() {
		return propX;
	}

	public int setPropX(int prop) {
		return prop;
	}

	public int setPropY(int prop) {
		return prop;
	}

	public static int getLeitores() {
		return leitores;
	}

	public static void setLeitores(int leitoresa) {
		leitores += leitoresa;
	}

	public static int getAlives() {
		return alives;
	}

	public static void setAlives(int alivesa) {
		alives += alivesa;
	}
}
