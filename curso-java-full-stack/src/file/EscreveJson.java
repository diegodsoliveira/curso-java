package file;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class EscreveJson {

	public static void main(String[] args) throws IOException {
		Usuario usuario1 = new Usuario();
		
		usuario1.setCpf("65445613");
		usuario1.setLogin("caio");
		usuario1.setSenha("123456");
		usuario1.setNome("Caio Rodrigues");
		
		Usuario usuario2 = new Usuario();
		usuario2.setCpf("213809321");
		usuario2.setLogin("maria");
		usuario2.setSenha("123");
		usuario2.setNome("Maria da Silva");
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios.add(usuario1);
		usuarios.add(usuario2);
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonUser = gson.toJson(usuarios);
		
		System.out.println(jsonUser);
		
		FileWriter file = new FileWriter("C:\\Users\\diego\\git\\repository\\curso-java-full-stack\\src\\file\\arquivo.json");
		file.write(jsonUser);
		file.flush();
		file.close();
		
		/* -------- Lendo arquivo Json -------*/
		FileReader fileReader = new FileReader("C:\\Users\\diego\\git\\repository\\curso-java-full-stack\\src\\file\\arquivo.json");
		JsonArray jsonArray = (JsonArray) JsonParser.parseReader(fileReader);
		
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		
		for (JsonElement jsonElement: jsonArray) {
			Usuario usuario = new Gson().fromJson(jsonElement, Usuario.class);
			listaUsuarios.add(usuario);
		}
		
		System.out.println("Leitura do arquivo JSON: " + listaUsuarios);
	}

}
