package estudoThread;

import javax.swing.JOptionPane;

public class ConceitoThread {
	public static void main(String[] args) throws InterruptedException {
		/**
		Thread threadEmail = new Thread(new IsThread("email ",2,1000).thread);
		threadEmail.start();
		
		Thread threadNFCE = new Thread(new IsThread("nota fiscal ",1,1500).thread);
		threadNFCE.start();
		
		System.out.println("Operacao de venda concluida!");
		**/
		
		MinhaTelaThread telaThread = new MinhaTelaThread();
	}
}
