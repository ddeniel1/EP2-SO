package principal;

import java.util.concurrent.ThreadLocalRandom;

public class Threads {
	private int propX;
	private int propY;

	public void main(String[] args) {
		int propX = this.propX;
		int propY = this.propY;
		CriaThread[] arranjoDeThreads = new CriaThread[100];
		ThreadLocalRandom generator = ThreadLocalRandom.current();
		for (int i = 0; i < 100; i++) {
			// Definicao de x para saber se eh leitor ou escritor.
			int x = generator.nextInt(1, 2);
			CriaThread novaThread;
			if (x == 1 && propX > 0) {
				novaThread = new CriaThread("leitor");
				propX--;
			} else if (x == 2 && propY > 0) {
				novaThread = new CriaThread("escritor");
				propY--;
			}
			x = generator.nextInt(0, 99);
			while (arranjoDeThreads[x] != null)
				x = generator.nextInt(0, 99);
			arranjoDeThreads[x] = novaThread;
		}
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
