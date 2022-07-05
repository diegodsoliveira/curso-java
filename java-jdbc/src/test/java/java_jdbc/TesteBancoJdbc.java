package java_jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import dao.UserJavaDao;
import model.BeanConsulta;
import model.Telefone;
import model.Usuario;

public class TesteBancoJdbc {

	// adiciona um dado na tabela do banco de dados
	@Test
	public void addItemBanco() {
		UserJavaDao userJavaDao = new UserJavaDao();
		
		// Insere dados no banco de dados sql
		Usuario userJavaSql = new Usuario();
		
		// seta dados a serem inseridos no banco de dados
		userJavaSql.setNome("Maria");
		userJavaSql.setEmail("mariajose@uol.com.br");
		
		// chama método que salva os dados no banco de dados sql
		userJavaDao.salvar(userJavaSql);
		
	}
	
	// Lista dados do banco de dados sql
	@Test
	public void listaDadosBanco() {
		UserJavaDao dao = new UserJavaDao();
		
		List<Usuario> lista;
		try {
			lista = dao.listar();
			
			// lambda
			lista.forEach(c -> System.out.println(c));
			System.out.println("----- lista com filtro ------");
			lista.forEach(c -> System.out.println("Lista de emails: " + c.getEmail()));
			
			// chama método toString
			System.out.println(lista);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Atualiza dados no banco de dados
	@Test
	public void atualizaItemBanco() {
		UserJavaDao dao = new UserJavaDao();
		try {
			Usuario dado = dao.buscar(4L);
			
			dado.setNome("Antonio");
			dado.setEmail("antonio@bol.com.br");
			
			dao.atualizar(dado);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void deletarItemBanco() {
		try {
			UserJavaDao dao = new UserJavaDao();
			dao.deletar("DELETE from telefone_user WHERE usuariopessoa = " + 3L);
			dao.deletar("delete from userjavasql where id = " + 3L);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void addTelefone() {
		UserJavaDao userJavaDao = new UserJavaDao();
		
		// Insere dados no banco de dados sql
		Telefone telefone = new Telefone();
		
		// seta dados a serem inseridos no banco de dados
		telefone.setNumero("(61)5421-4578");
		telefone.setTipo("casa");
		telefone.setUsuario(7L);
		
		// chama método que salva os dados no banco de dados sql
		userJavaDao.salvarTelefone(telefone);
		
	}
	
	@Test
	public void listaTelefone() {
		UserJavaDao dao = new UserJavaDao();
		
		try {
			List<BeanConsulta> bean = dao.listarUserTelefone(3L);
			
			bean.forEach(c -> System.out.println(c.getNome() + ", telefone (" + c.getTipo() + ") " + c.getTelefone() + ", " 
					+ c.getEmail()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
