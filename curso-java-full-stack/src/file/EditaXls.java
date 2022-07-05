package file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

public class EditaXls {

	public static void main(String[] args) throws Exception {
		File arquivo = new File("C:\\Users\\diego\\git\\repository\\curso-java-full-stack\\src\\file\\arquivo.xls");
		FileInputStream entrada = new FileInputStream(arquivo);
		
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(entrada);
		HSSFSheet planilha = hssfWorkbook.getSheetAt(0);
		
		Iterator<Row> linhaIterator = planilha.iterator();
		
		while (linhaIterator.hasNext()) {
			Row linha = linhaIterator.next(); //dados da pessoa na linha
			String valorCelula = linha.getCell(0).getStringCellValue();
			
			linha.getCell(0).setCellValue(valorCelula + " teste concatenacao");
			
		}
		FileOutputStream saida = new FileOutputStream(arquivo);
		hssfWorkbook.write(saida);
		saida.flush();
		saida.close();
		entrada.close();
		
		System.out.println("Planilha atualizada");
	}

}
