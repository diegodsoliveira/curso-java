package file;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class LeArquivoXls {
	public static void main(String[] args) throws Exception {

		FileInputStream arquivo = new FileInputStream(
				new File("C:\\Users\\diego\\git\\repository\\curso-java-full-stack\\src\\file\\arquivo.xls"));

		HSSFWorkbook hffHssfWorkbook = new HSSFWorkbook(arquivo);
		HSSFSheet planilha = hffHssfWorkbook.getSheetAt(0);

		Iterator<Row> linhaIterator = planilha.iterator();
		List<Pessoa> pessoas = new ArrayList<Pessoa>();

		while (linhaIterator.hasNext()) {
			Row linha = linhaIterator.next();

			Iterator<Cell> celulas = linha.iterator();
			Pessoa pessoa = new Pessoa();
			while (celulas.hasNext()) {
				Cell cell = celulas.next();
				
				switch (cell.getColumnIndex()) {
				case 0: 
					pessoa.setNome(cell.getStringCellValue());
					break;
				case 1:
					pessoa.setEmail(cell.getStringCellValue());
					break;
				case 2:
					pessoa.setTelefone(cell.getStringCellValue());
					break;
				}
			}
			pessoas.add(pessoa);
			
		}
		arquivo.close();
		
		for (Pessoa pessoa : pessoas) {
			System.out.println(pessoa);
			
		}
	}
}
