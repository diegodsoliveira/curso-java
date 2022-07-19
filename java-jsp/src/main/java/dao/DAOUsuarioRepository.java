package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.StatementEvent;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOUsuarioRepository {

	private Connection connection;

	public DAOUsuarioRepository() {
		connection = SingleConnectionBanco.getConnection();
	}

	public PreparedStatement preparaStringSqlGravar(ModelLogin obj, Long idUserLogado) throws Exception {

		String sql = "INSERT INTO model_login(senha, login, nome, email, usuario_id, perfil, sexo, cep, logradouro, bairro, localidade, uf, numero)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, obj.getSenha());
		statement.setString(2, obj.getLogin());
		statement.setString(3, obj.getNome());
		statement.setString(4, obj.getEmail());
		statement.setLong(5, idUserLogado);
		statement.setString(6, obj.getPerfil());
		statement.setString(7, obj.getSexo());
		statement.setString(8, obj.getCep());
		statement.setString(9, obj.getBairro());
		statement.setString(10, obj.getLocalidade());
		statement.setString(11, obj.getLogradouro());
		statement.setString(12, obj.getUf());
		statement.setString(13, obj.getNumero());

		return statement;
	}

	public PreparedStatement preparaStringSqlUpdate(ModelLogin obj) throws Exception {

		String sql = "UPDATE model_login SET senha=?, login=?, nome=?, email=?, perfil=?, sexo=?,"
				+ " cep=?, bairro=?, localidade=?, logradouro=?, uf=?, numero=? " + "WHERE id=?";

		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, obj.getSenha());
		statement.setString(2, obj.getLogin());
		statement.setString(3, obj.getNome());
		statement.setString(4, obj.getEmail());
		statement.setString(5, obj.getPerfil());
		statement.setString(6, obj.getSexo());
		statement.setString(7, obj.getCep());
		statement.setString(8, obj.getBairro());
		statement.setString(9, obj.getLocalidade());
		statement.setString(10, obj.getLogradouro());
		statement.setString(11, obj.getUf());
		statement.setString(12, obj.getNumero());
		statement.setLong(13, obj.getId());

		return statement;
	}

	public ModelLogin setaCamposConsulta(ModelLogin objeto, ResultSet resultado) throws Exception {

		objeto.setEmail(resultado.getString("email"));
		objeto.setId(resultado.getLong("id"));
		objeto.setLogin(resultado.getString("login"));
		objeto.setNome(resultado.getString("nome"));
		objeto.setUserAdmin(resultado.getBoolean("useradmin"));
		objeto.setPerfil(resultado.getString("perfil"));
		objeto.setSexo(resultado.getString("sexo"));
		objeto.setCep(resultado.getString("cep"));
		objeto.setLogradouro(resultado.getString("logradouro"));
		objeto.setBairro(resultado.getString("bairro"));
		objeto.setLocalidade(resultado.getString("localidade"));
		objeto.setUf(resultado.getString("uf"));
		objeto.setNumero(resultado.getString("numero"));

		return objeto;
	}
	
	public ModelLogin setaCamposConsultaComFoto(ModelLogin objeto, ResultSet resultado) throws Exception {

		objeto.setEmail(resultado.getString("email"));
		objeto.setId(resultado.getLong("id"));
		objeto.setLogin(resultado.getString("login"));
		objeto.setNome(resultado.getString("nome"));
		objeto.setSenha(resultado.getString("senha"));
		objeto.setUserAdmin(resultado.getBoolean("useradmin"));
		objeto.setPerfil(resultado.getString("perfil"));
		objeto.setSexo(resultado.getString("sexo"));
		objeto.setFotoUser(resultado.getString("fotouser"));
		objeto.setExtensaoFotoUser(resultado.getString("extensaofotouser"));
		objeto.setCep(resultado.getString("cep"));
		objeto.setLogradouro(resultado.getString("logradouro"));
		objeto.setBairro(resultado.getString("bairro"));
		objeto.setLocalidade(resultado.getString("localidade"));
		objeto.setUf(resultado.getString("uf"));
		objeto.setNumero(resultado.getString("numero"));

		return objeto;
	}

	public ModelLogin gravarUsuario(ModelLogin obj, Long idUserLogado) throws Exception {
		String sql;

		if (obj.isNovo()) { // grava um novo usuário

			PreparedStatement statement = preparaStringSqlGravar(obj, idUserLogado);

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

			PreparedStatement statement = preparaStringSqlUpdate(obj);

			statement.executeUpdate();
			connection.commit();

			if (obj.getFotoUser() != null && !obj.getFotoUser().isEmpty()) {

				sql = "update model_login set fotouser=?, extensaofotouser=? where id=?";
				statement = connection.prepareStatement(sql);

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
			ModelLogin modelLogin = new ModelLogin();

			modelLogin = setaCamposConsulta(modelLogin, resultado);
			/*
			 * modelLogin.setEmail(resultado.getString("email"));
			 * modelLogin.setId(resultado.getLong("id"));
			 * modelLogin.setLogin(resultado.getString("login"));
			 * modelLogin.setNome(resultado.getString("nome"));
			 * modelLogin.setUserAdmin(resultado.getBoolean("useradmin"));
			 * modelLogin.setPerfil(resultado.getString("perfil"));
			 * modelLogin.setSexo(resultado.getString("sexo"));
			 * modelLogin.setCep(resultado.getString("cep"));
			 * modelLogin.setLogradouro(resultado.getString("logradouro"));
			 * modelLogin.setBairro(resultado.getString("bairro"));
			 * modelLogin.setLocalidade(resultado.getString("localidade"));
			 * modelLogin.setUf(resultado.getString("uf"));
			 * modelLogin.setNumero(resultado.getString("numero"));
			 */
			// user.setSenha(resultado.getString("senha"));

			usuarios.add(modelLogin);
		}

		return usuarios;
	}

	public List<ModelLogin> buscarUsuarios(Long idUserLogado) throws Exception {
		List<ModelLogin> usuarios = new ArrayList<ModelLogin>();
		String sql = "select * from model_login where useradmin is false and usuario_id = ? order by id asc";

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, idUserLogado);

		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) {
			ModelLogin modelLogin = new ModelLogin();

			modelLogin = setaCamposConsulta(modelLogin, resultado);
			// user.setSenha(resultado.getString("senha"));

			usuarios.add(modelLogin);
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

			modelLogin = setaCamposConsultaComFoto(modelLogin, resultado);
			
		}

		return modelLogin;
	}

	public ModelLogin consultaUsuario(String login) throws Exception {
		ModelLogin modelLogin = new ModelLogin();
		String sql = "select * from model_login where upper(login) = upper('" + login + "') and useradmin is false";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();

		if (resultado.next()) {
			modelLogin = setaCamposConsultaComFoto(modelLogin, resultado);
		}

		return modelLogin;
	}

	public ModelLogin consultaUsuarioLogado(String login) throws Exception {
		ModelLogin modelLogin = new ModelLogin();
		String sql = "select * from model_login where upper(login) = upper('" + login + "')";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();

		if (resultado.next()) {
			
			modelLogin = setaCamposConsultaComFoto(modelLogin, resultado);
			
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
			
			modelLogin = setaCamposConsultaComFoto(modelLogin, resultado);
			
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
