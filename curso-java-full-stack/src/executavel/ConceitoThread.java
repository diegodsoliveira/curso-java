package executavel;

import javax.swing.JOptionPane;

public class ConceitoThread {
	public static void main(String[] args) throws InterruptedException {
		
		new Thread() {
			public void run() {
				for (int i = 0; i < 10; i++) {
					System.out.println("Enviando email " + (i+1) + "...");
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
				JOptionPane.showMessageDialog(null, "Envio de emails concluido.");
			};
		}.start();
		System.out.println("Operacao de venda concluida!");
		
	}
}
