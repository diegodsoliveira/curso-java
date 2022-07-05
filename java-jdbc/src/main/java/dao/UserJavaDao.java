package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaojdbc.SingleConnection;
import model.BeanConsulta;
import model.Telefone;
import model.Usuario;

public class UserJavaDao {

	private Connection connection;

	public UserJavaDao() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(Usuario userJavaSql) {
		try {

			String sql = "INSERT INTO userjavasql (nome, email) VALUES (?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, userJavaSql.getNome());
			insert.setString(2, userJavaSql.getEmail());
			insert.execute();
			connection.commit(); // salva no banco

		} catch (Exception e) {
			try {
				connection.rollback(); // em caso de erro reverte a operação no banco de dados
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	/**
	 * @param telefone
	 */
	public void salvarTelefone(Telefone telefone) {
		try {

			String sql = "INSERT INTO telefone_user (numero, tipo, usuariopessoa) VALUES (?, ?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, telefone.getNumero());
			insert.setString(2, telefone.getTipo());
			insert.setLong(3, telefone.getUsuario());
			insert.execute();
			connection.commit(); // salva no banco

		} catch (Exception e) {
			try {
				connection.rollback(); // em caso de erro reverte a operação no banco de dados
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public List<Usuario> listar() throws SQLException {

		List<Usuario> listaSql = new ArrayList<>(); // lista que vai receber os dados do banco de dados

		String sql = "SELECT * FROM userjavasql"; // query de consulta ao banco de dados

		PreparedStatement statement = connection.prepareStatement(sql); // prepara a conexão com a query sql
		ResultSet resultado = statement.executeQuery(); // realiza consulta ao banco de dados e armazena os dados na
														// variável resultado

		while (resultado.next()) { // percorre os dados em resultado enquanto houver dados
			Usuario userJavaSql = new Usuario(); // instancia do tipo de dado no banco

			/*
			 * manipulando os dados obtidos do banco de dados e adicionando na lista do tipo
			 * de dado no banco
			 */
			userJavaSql.setId(resultado.getLong("id"));
			userJavaSql.setNome(resultado.getString("nome"));
			userJavaSql.setEmail(resultado.getString("email"));

			listaSql.add(userJavaSql);
		}

		return listaSql;
	}

	public Usuario buscar(Long id) throws SQLException {

		Usuario objeto = new Usuario(); // lista que vai receber os dados do banco de dados

		String sql = "SELECT * FROM userjavasql WHERE ID = " + id; // query de consulta ao banco de dados

		PreparedStatement statement = connection.prepareStatement(sql); // prepara a conexão com a query sql
		ResultSet resultado = statement.executeQuery(); // realiza consulta ao banco de dados e armazena os dados na
														// variável resultado

		while (resultado.next()) { // percorre os dados em resultado enquanto houver dados

			/*
			 * manipulando os dados obtidos do banco de dados e adicionando na lista do tipo
			 * de dado no banco
			 */
			objeto.setId(resultado.getLong("id"));
			objeto.setNome(resultado.getString("nome"));
			objeto.setEmail(resultado.getString("email"));
		}

		return objeto;
	}

	public void atualizar(Usuario userJavaSql) throws SQLException {
		try {
			String sql = "UPDATE userjavasql SET NOME = ?, EMAIL = ? WHERE ID = " + userJavaSql.getId();

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, userJavaSql.getNome());
			statement.setString(2, userJavaSql.getEmail());

			statement.execute();
			connection.commit();
		} catch (Exception e) {
			connection.rollback();
			e.printStackTrace();
		}
	}

	public void deletar(Long id) throws SQLException {
		String sql = "DELETE from userjavasql WHERE ID = " + id;

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.execute();
		connection.commit();
		try {
			connection.rollback();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<BeanConsulta> listarUserTelefone(Long idUser) {

		List<BeanConsulta> listaSql = new ArrayList<>(); // lista que vai receber os dados do banco de dados

		String sql = "select numero, tipo, email, nome from telefone_user as tel "; // query de consulta ao banco de dados
		sql += "inner join userjavasql as pessoa ";
		sql += "on tel.usuariopessoa = pessoa.id ";
		sql += "where pessoa.id = " + idUser;

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultado = statement.executeQuery(); // realiza consulta ao banco de dados e armazena os dados na
			// variável resultado
			
			while (resultado.next()) { // percorre os dados em resultado enquanto houver dados
				BeanConsulta beanConsulta = new BeanConsulta(); // instancia do tipo de dado no banco
				
				/*
				 * manipulando os dados obtidos do banco de dados e adicionando na lista do tipo
				 * de dado no banco
				 */
				beanConsulta.setNome(resultado.getString("nome"));
				beanConsulta.setTelefone(resultado.getString("numero"));
				beanConsulta.setEmail(resultado.getString("email"));
				beanConsulta.setTipo(resultado.getString("tipo"));
				
				listaSql.add(beanConsulta);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} // prepara a conexão com a query sql

		return listaSql;
	}
}
