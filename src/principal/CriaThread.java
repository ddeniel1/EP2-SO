package principal;

public class CriaThread extends Thread {

	public CriaThread(String args) {
		if (args.equals("leitor"))
			criaLeitor();
		else
			criaEscritor();
	}

	private void criaLeitor() {
		ThreadLeitora leitor = new ThreadLeitora();
	}

	private void criaEscritor() {
		ThreadEscritora escritor = new ThreadEscritora();
	}
}
