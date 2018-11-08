package regiaoCritica;

import java.util.concurrent.ThreadLocalRandom;

public class Threads {
	private int propX;
	private int propY;

	public void run() {
		int propX = this.propX;
		int propY = this.propY;
		CriaThread[] arranjoDeThreads = new CriaThread[100];
		ThreadLocalRandom generator = ThreadLocalRandom.current();
		try {
			for (int i = 0; i < 100; i++) {

				// Definicao de x para saber se eh leitor ou escritor.
				int x = generator.nextInt(1, 3);
				CriaThread novaThread = null;
				if (x == 1) {
					if (propX > 0) {
						novaThread = new CriaThread("leitor");
						novaThread.setPriority(2);
						propX--;
					} else {
						novaThread = new CriaThread("escritor");
						novaThread.setPriority(1);
						propY--;
					}
				} else if (x == 2) {
					if (propY > 0) {
						novaThread = new CriaThread("escritor");
						novaThread.setPriority(1);
						propY--;
					} else {
						novaThread = new CriaThread("leitor");
						novaThread.setPriority(2);
						propX--;
					}
				}
				x = generator.nextInt(0, 99);
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
			try {
				arranjoDeThreads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		long fim = System.currentTimeMillis();

		Main.escreve("Tempo total: 0." + (fim - inicio)+ " segundos");
		System.out.println("Tempo total: 0." + (fim - inicio)+ " segundos");

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

}