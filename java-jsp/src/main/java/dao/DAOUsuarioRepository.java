package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOUsuarioRepository {

	private Connection connection;

	public DAOUsuarioRepository() {
		connection = SingleConnectionBanco.getConnection();
	}

	public ModelLogin gravarUsuario(ModelLogin obj) throws Exception {

		if (obj.isNovo()) { // grava um novo usuário

			String sql = "INSERT INTO model_login(senha, login, nome, email) VALUES (?, ?, ?, ?);";
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, obj.getSenha());
			statement.setString(2, obj.getLogin());
			statement.setString(3, obj.getNome());
			statement.setString(4, obj.getEmail());
			
			statement.execute();
			connection.commit();

		} else { // atualiza usuário existente
			String sql = "UPDATE model_login SET senha=?, login=?, nome=?, email=? WHERE id = "
					+ obj.getId() + ";";
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, obj.getSenha());
			statement.setString(2, obj.getLogin());
			statement.setString(3, obj.getNome());
			statement.setString(4, obj.getEmail());
			statement.executeUpdate();
			connection.commit();

		}
		return this.consultaUsuario(obj.getLogin());

	}
	public List<ModelLogin> buscarUsuarios(String nomeUser) throws Exception {
		List<ModelLogin> usuarios = new ArrayList<>();
		String sql = "select * from model_login where upper(nome) like upper(?);";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setString(1, "%" + nomeUser + "%");
		
		ResultSet resultado = statement.executeQuery();
		
		while (resultado.next()) {
			ModelLogin user = new ModelLogin();
			
			user.setEmail(resultado.getString("email"));
			user.setId(resultado.getLong("id"));
			user.setLogin(resultado.getString("login"));
			user.setNome(resultado.getString("nome"));
			//user.setSenha(resultado.getString("senha"));
			
			usuarios.add(user);
		}
		
		return usuarios; 
	}

	public ModelLogin consultaUsuario(String str) throws Exception {
		ModelLogin modelLogin = new ModelLogin();
		String sql = "select * from model_login where upper(login) = upper('" + str + "');";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();

		if (resultado.next()) {
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setSenha(resultado.getString("senha"));
		}

		return modelLogin;
	}

	public boolean validarLogin(String login) throws Exception {
		String sql = "select count(1) > 0 as existe from model_login where upper(login) = upper('" + login + "');";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();

		resultado.next();

		return resultado.getBoolean("existe");
	}
	
	public void deletarUsuario(String id) throws Exception {
		String sql = "DELETE FROM model_login WHERE id = "+ id + ";";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.executeUpdate();
		connection.commit();
	}
	

}
