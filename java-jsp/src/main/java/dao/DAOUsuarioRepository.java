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

	public ModelLogin gravarUsuario(ModelLogin obj, Long idUserLogado) throws Exception {

		if (obj.isNovo()) { // grava um novo usuário

			String sql = "INSERT INTO model_login(senha, login, nome, email, usuario_id, perfil, sexo) VALUES (?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, obj.getSenha());
			statement.setString(2, obj.getLogin());
			statement.setString(3, obj.getNome());
			statement.setString(4, obj.getEmail());
			statement.setLong(5, idUserLogado);
			statement.setString(6, obj.getPerfil());
			statement.setString(7, obj.getSexo());

			statement.execute();
			connection.commit();

			if (obj.getFotoUser() != null && !obj.getFotoUser().isEmpty()) {

				sql = "update model_login set fotouser=?, extensaofotouser=? where login=?";

				statement.setString(1, obj.getFotoUser());
				statement.setString(2, obj.getExtensaoFotoUser());
				statement.setString(3, obj.getLogin());

				statement.execute();
				connection.commit();
			}

		} else { // atualiza usuário existente
			String sql = "UPDATE model_login SET senha=?, login=?, nome=?, email=?, perfil=?, sexo=? WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, obj.getSenha());
			statement.setString(2, obj.getLogin());
			statement.setString(3, obj.getNome());
			statement.setString(4, obj.getEmail());
			statement.setString(5, obj.getPerfil());
			statement.setString(6, obj.getSexo());
			statement.setLong(7, obj.getId());

			statement.executeUpdate();
			connection.commit();

			if (obj.getFotoUser() != null && !obj.getFotoUser().isEmpty()) {

				sql = "update model_login set fotouser=?, extensaofotouser=? where id=?";

				statement.setString(1, obj.getFotoUser());
				statement.setString(2, obj.getExtensaoFotoUser());
				statement.setLong(3, obj.getId());

				statement.execute();
				connection.commit();
			}

		}
		return this.consultaUsuario(obj.getLogin(), idUserLogado);

	}

	public List<ModelLogin> buscarUsuarios(String nomeUser, Long idUserLogado) throws Exception {
		List<ModelLogin> usuarios = new ArrayList<>();
		String sql = "select * from model_login where upper(nome) like upper(?) and useradmin is false and usuario_id = "
				+ idUserLogado;

		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, "%" + nomeUser + "%");

		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) {
			ModelLogin user = new ModelLogin();

			user.setEmail(resultado.getString("email"));
			user.setId(resultado.getLong("id"));
			user.setLogin(resultado.getString("login"));
			user.setNome(resultado.getString("nome"));
			user.setUserAdmin(resultado.getBoolean("useradmin"));
			user.setPerfil(resultado.getString("perfil"));
			user.setSexo(resultado.getString("sexo"));
			// user.setSenha(resultado.getString("senha"));

			usuarios.add(user);
		}

		return usuarios;
	}

	public List<ModelLogin> buscarUsuarios(Long idUserLogado) throws Exception {
		List<ModelLogin> usuarios = new ArrayList<ModelLogin>();
		String sql = "select * from model_login where useradmin is false and usuario_id = ? order by id asc"; // consulta
																												// sql
																												// ordenada
																												// por
																												// id
																												// crescente

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, idUserLogado);

		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) {
			ModelLogin user = new ModelLogin();

			user.setEmail(resultado.getString("email"));
			user.setId(resultado.getLong("id"));
			user.setLogin(resultado.getString("login"));
			user.setNome(resultado.getString("nome"));
			user.setUserAdmin(resultado.getBoolean("useradmin"));
			user.setPerfil(resultado.getString("perfil"));
			user.setSexo(resultado.getString("sexo"));
			// user.setSenha(resultado.getString("senha"));

			usuarios.add(user);
		}

		return usuarios;
	}

	public ModelLogin consultaUsuario(String login, Long idUserLogado) throws Exception {
		ModelLogin modelLogin = new ModelLogin();
		String sql = "select * from model_login where upper(login) = upper('" + login
				+ "') and useradmin is false and usuario_id = " + idUserLogado;

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();

		if (resultado.next()) {
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setUserAdmin(resultado.getBoolean("useradmin"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));
		}

		return modelLogin;
	}

	public ModelLogin consultaUsuario(String login) throws Exception {
		ModelLogin modelLogin = new ModelLogin();
		String sql = "select * from model_login where upper(login) = upper('" + login + "') and useradmin is false";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();

		if (resultado.next()) {
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setUserAdmin(resultado.getBoolean("useradmin"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));
		}

		return modelLogin;
	}

	public ModelLogin consultaUsuarioLogado(String login) throws Exception {
		ModelLogin modelLogin = new ModelLogin();
		String sql = "select * from model_login where upper(login) = upper('" + login + "')";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();

		if (resultado.next()) {
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setUserAdmin(resultado.getBoolean("useradmin"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));
		}

		return modelLogin;
	}

	public ModelLogin consultaUsuarioID(String id, Long idUserLogado) throws Exception {
		ModelLogin modelLogin = new ModelLogin();
		String sql = "select * from model_login where id = ? and useradmin is false and usuario_id = ?";

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, Long.parseLong(id));
		statement.setLong(2, idUserLogado);
		ResultSet resultado = statement.executeQuery();

		if (resultado.next()) {
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setUserAdmin(resultado.getBoolean("useradmin"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));
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
		String sql = "DELETE FROM model_login WHERE id = " + id + "and useradmin is false;";

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.executeUpdate();
		connection.commit();
	}

}
