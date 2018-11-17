package regiaoCritica;

import java.util.concurrent.ThreadLocalRandom;

public class Threads extends Thread {

	private String ler;
	private String escrever = "MODIFICADO";

	@Override
	public void run() {
		ThreadLocalRandom generator = ThreadLocalRandom.current();
		String[] livro = Main_RC.getLivro();
		for (int i = 0; i < 100; i++) {
			runThread(generator.nextInt(0, 36241), livro);
		}
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public Threads(String args) {
		this.setName(args);

	}

	public void runThread(int regex, String[] livro) {
		if (this.getName().equals("leitor")) {
			this.setLer(livro[regex]);
		} else {
			livro[regex] = escrever;
		}

	}

	public String getLer() {
		return ler;
	}

	public void setLer(String ler) {
		this.ler = ler;
	}
}
