package estudoThread;

import javax.swing.JOptionPane;

public class IsThread {
	String msg;
	int qtde, sleep;

	// Construtores
	public IsThread(String msg, int qtde, int sleep) {
		this.msg = msg;
		this.qtde = qtde;
		this.sleep = sleep;
	}
	public IsThread() {
	}

	
	public Runnable thread = new Runnable() {
		
		@Override
		public void run() {
			for (int i = 0; i < qtde; i++) {
				System.out.println(msg + (i+1) + "...");
				try {
					Thread.sleep(sleep);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			JOptionPane.showMessageDialog(null, "Envio de " + msg + " concluido.");			
		}
	};

}
