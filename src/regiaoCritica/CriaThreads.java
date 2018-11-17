package regiaoCritica;

import java.util.concurrent.ThreadLocalRandom;

public class CriaThreads {
	private int propX;
	private int propY;

	public void run() {
		Threads[] arranjoDeThreads = new Threads[100];
		ThreadLocalRandom generator = ThreadLocalRandom.current();
		try {
			for (int i = 0; i < 100; i++) {

				// Definicao de x para saber se eh leitor ou escritor.
				int x = 0;
				Threads novaThread = null;

				if (this.getPropX() > 0) {
					novaThread = new Threads("leitor");
					novaThread.setPriority(2);
					this.setPropX(this.getPropX() - 1);
					x = generator.nextInt(0, 100);
					while (arranjoDeThreads[x] != null) {
						x = generator.nextInt(0, 100);
					}
					arranjoDeThreads[x] = novaThread;
				} else {
					novaThread = new Threads("escritor");
					novaThread.setPriority(1);
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

				Main_RC.escreve(tempo);
				System.out.println(tempo);
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
