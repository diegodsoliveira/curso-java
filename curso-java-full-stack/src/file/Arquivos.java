package file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Arquivos {

	public static void main(String[] args) throws IOException {
		
		Pessoa pessoa1 = new Pessoa();
		Pessoa pessoa2 = new Pessoa();
		Pessoa pessoa3 = new Pessoa();
		
		File arquivo = new File("C:\\Users\\diego\\git\\repository\\curso-java-full-stack\\src\\file\\arquivo.txt");
		
		pessoa1.setEmail("pessoa1@gmail.com");
		pessoa1.setNome("Diego Oliveira");
		pessoa1.setTelefone("1321323244");
		
		pessoa2.setEmail("pessoa2@gmail.com");
		pessoa2.setNome("João Oliveira");
		pessoa2.setTelefone("654657963241");
		
		pessoa3.setEmail("pessoa3@gmail.com");
		pessoa3.setNome("José Monteiro");
		pessoa3.setTelefone("19237891237");
		
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		pessoas.add(pessoa3);
		pessoas.add(pessoa2);
		pessoas.add(pessoa1);

		if (!arquivo.exists()) {
			arquivo.createNewFile();
		}
				
		FileWriter escreveNoArquivo = new FileWriter(arquivo);
		
		for (Pessoa p : pessoas) {
			escreveNoArquivo.write(p.getNome() + "," + p.getEmail() + "," + p.getTelefone() + "\n");
		}
		escreveNoArquivo.flush();
		escreveNoArquivo.close();
	}

}
