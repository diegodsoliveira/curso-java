package web.mail;

import javax.swing.JOptionPane;

/**
 * Unit test for simple App.
 */
public class AppTest {
	
<<<<<<< HEAD
	private String userName = JOptionPane.showInputDialog("Login: ");
	private String pass = JOptionPane.showInputDialog("Senha: ");
=======
	private String userName = ""; // usuario da conta de email
	private String pass = ""; // senha da conta de email
>>>>>>> refs/heads/java-web

	@org.junit.Test
	public void testeEmail() throws Exception {
		
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append("Testando html <br/>"); //aceita todo tipo de código html
		stringBuilder.append("<h3>Está funcionando perfeitamente</h3><br/>");
		
		ObjetoEnviaEmail enviarEmail = new ObjetoEnviaEmail("diegodsoliveira@gmail.com", 
				"Viajar a Bessa", "Novidades chegando...", stringBuilder.toString(),userName,pass);
		
		enviarEmail.enviaEmail(true);
		
		Thread.sleep(2000);
	}

		
}
