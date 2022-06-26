package file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LerArquivoTxt {
	
	public static void main(String[] args) throws IOException {
		
		FileInputStream arquivo = new FileInputStream("C:\\Users\\diego\\git\\repository\\curso-java-full-stack\\src\\file\\contatos.csv");
		
		try (Scanner leArquivo = new Scanner(arquivo, "UTF-8")) {
			List<Pessoa> pessoas = new ArrayList<Pessoa>();
			
			while (leArquivo.hasNext()) {
				String linha = leArquivo.nextLine();
				
				if (linha != null && !linha.isEmpty()) {
					//System.out.println(linha);
					String[] dados = linha.split("\\,"); //separa os dados em um campo do array toda vez que encontra uma ","
					
					Pessoa pessoa = new Pessoa();
					if (!dados[0].isEmpty()) {
						pessoa.setNome(dados[0]);
					} else {
						pessoa.setNome(null);
					}
					
					if (!dados[1].isEmpty()) {
						pessoa.setSobrenome(dados[1]);
					} else {
						pessoa.setSobrenome(null);
					}
					
					if (!dados[2].isEmpty()) {
						pessoa.setEmail(dados[2]);
					} else {
						pessoa.setEmail(null);
					}
					
					if (!dados[3].isEmpty()) {
						pessoa.setTelefone(dados[3]);
					} else {
						pessoa.setTelefone(null);
					}
					/*
					if (dados[4] != null && !dados[4].isEmpty()) {
						pessoa.setCidade(dados[4]);
					} else {
						pessoa.setCidade(null);
					}
					*/
					pessoas.add(pessoa);
				}
			}
			
			// varre a lista de pessoas e imprime os dados
			for (Pessoa p : pessoas) {
				if (p.getNome() != null && p.getNome().contains("Maria")) {
					System.out.println(p);
				}
			}
		}
		
		arquivo.close();
	}

}
