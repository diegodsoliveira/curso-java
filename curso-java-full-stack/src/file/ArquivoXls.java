package file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ArquivoXls {

	public static void main(String[] args) throws IOException {
		
		Pessoa pessoa1 = new Pessoa();
		Pessoa pessoa2 = new Pessoa();
		Pessoa pessoa3 = new Pessoa();
		File arquivo = new File("C:\\Users\\diego\\git\\repository\\curso-java-full-stack\\src\\file\\arquivo.xls");
		if (!arquivo.exists()) {
			arquivo.createNewFile();
		}
		
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

		
		try (HSSFWorkbook hssfWorkbook = new HSSFWorkbook()) {
			HSSFSheet linhas = hssfWorkbook.createSheet("Planilha de pessoas");
			
			int numeroLinha = 0;
			
			for (Pessoa p : pessoas) {
				
				Row linha = linhas.createRow(numeroLinha++);
				
				int celula = 0;
				
				Cell celNome = linha.createCell(celula++);
				celNome.setCellValue(p.getNome());

				Cell celEmail = linha.createCell(celula++);
				celEmail.setCellValue(p.getEmail());	

				Cell celTelefone = linha.createCell(celula++);
				celTelefone.setCellValue(p.getTelefone());

			}
			FileOutputStream saida = new FileOutputStream(arquivo);
			hssfWorkbook.write(saida);
			saida.flush();
			saida.close();
		}
		System.out.println("Planilha foi criada");
	}

}
