package web.mail;


/**
 * Unit test for simple App.
 */
public class AppTest {
	
	private String userName = ""; // usuario da conta de email
	private String pass = ""; // senha da conta de email

	@org.junit.Test
	public void testeEmail() throws Exception {
		
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append("Testando html <br/>"); //aceita todo tipo de código html
		
		ObjetoEnviaEmail enviarEmail = new ObjetoEnviaEmail("diegodsoliveira@gmail.com,contato@viajarabessa.com.br", 
				"Viajar a Bessa", "Novidades chegando...", stringBuilder.toString());
		
		enviarEmail.enviaEmail(true);
		
		Thread.sleep(5000);
	}

		
}
