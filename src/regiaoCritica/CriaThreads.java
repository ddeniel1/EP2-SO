package regiaoCritica;

import java.util.concurrent.ThreadLocalRandom;


public class CriaThreads {
	private int propX;
	private int propY;

	public void run() throws InterruptedException {
		int tempo = 0;
		int propYBackUp = getPropY();
		int propXBackUp = getPropX();
		for (int cases = 0; cases < 50; cases++) {
			setPropX(propXBackUp);
			setPropY(propYBackUp);

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
				arranjoDeThreads[i].join();
			}

			for (int i = 0; i < arranjoDeThreads.length; i++) {
				try {
					arranjoDeThreads[i].join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			long fim = System.currentTimeMillis();
			tempo+=(fim-inicio);
		}
		tempo /=50;
		// Escreve log de saida
		String tempoString = "Tempo total: 0." + (tempo >= 10 ? (tempo >= 100 ? tempo : ("0" + tempo)) : ("00" + tempo))
				+ " segundos";
		Main_RC.escreve(tempoString);
		System.out.println(tempoString);
		tempo=0;
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
