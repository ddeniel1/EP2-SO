package v3;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class CriaThreads {
	public static boolean fila = false;
	private int propX;
	private int propY;
	private static int leitores = 0;
	private static int escritores = 0;
	public static Semaphore mutex = new Semaphore(1);
	public static Semaphore mutex2 = new Semaphore(1);
	public static Semaphore lendo = new Semaphore(1, true);
	public static Semaphore escrevendo = new Semaphore(1);

	public void run() {
		int tempo = 0;
		int propYBackUp = getPropY();
		int propXBackUp = getPropX();
		for (int cases = 0; cases < 50; cases++) {
			setPropX(propXBackUp);
			setPropY(propYBackUp);

			Threads_outra[] arranjoDeThreads = new Threads_outra[100];
			ThreadLocalRandom generator = ThreadLocalRandom.current();
			try {
				// Alocacao das threads no array
				for (int i = 0; i < 100; i++) {

					// Definicao de x para saber se eh leitor ou escritor.
					int x = 0;
					Threads_outra novaThread = null;

					if (this.getPropX() > 0) {
						novaThread = new Threads_outra("leitor " + i);
						this.setPropX(this.getPropX() - 1);
						x = generator.nextInt(0, 100);
						while (arranjoDeThreads[x] != null) {
							x = generator.nextInt(0, 100);
						}
						arranjoDeThreads[x] = novaThread;
					} else {
						novaThread = new Threads_outra("escritor " + i);
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
			tempo+=(fim-inicio);
		}
		tempo /=50;
		// Escreve log de saida
		String tempoString = "Tempo total: 0." + (tempo >= 10 ? (tempo >= 100 ? tempo : ("0" + tempo)) : ("00" + tempo))
				+ " segundos";
		Main_v3.escreve(tempoString);
		System.out.println(tempoString);
		tempo=0;
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

	public static int getEscritores() {
		return escritores;
	}

	public static void setEscritores(int escritoresa) {
		escritores += escritoresa;
	}
}
