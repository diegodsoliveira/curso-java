package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnectionBanco;
import model.ModelTelefone;

public class DAOTelefoneRepository {

	private Connection connection;
	
	DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();

	public DAOTelefoneRepository() {
		connection = SingleConnectionBanco.getConnection();
	}

	public List<ModelTelefone> findTelefoneById(Long id) throws Exception {

		List<ModelTelefone> telefones = new ArrayList<ModelTelefone>();

		String querySql = "select * from telefone where usuario_pai_id = ?";

		PreparedStatement statement = connection.prepareStatement(querySql);

		statement.setLong(1, id);
		
		ResultSet retornoQuerySql = statement.executeQuery();
		
		while (retornoQuerySql.next()) {
			ModelTelefone modelTelefone = new ModelTelefone();
			
			modelTelefone.setId(retornoQuerySql.getLong("id"));
			modelTelefone.setNumero(retornoQuerySql.getString("numero"));
			modelTelefone.setUsuario_pai_id(daoUsuarioRepository.findUserById(retornoQuerySql.getLong("usuario_pai_id")));
			modelTelefone.setUsuario_cad_id(daoUsuarioRepository.findUserById(retornoQuerySql.getLong("usuario_cad_id")));
			
			telefones.add(modelTelefone);
		}
		
		return telefones;

	}

	public void gravaTelefone(ModelTelefone modelTelefone) throws Exception {
		String querySql = "insert into telefone(numero, usuario_pai_id, usuario_cad_id) VALUES (?, ?, ?);";

		PreparedStatement statement = connection.prepareStatement(querySql);
		statement.setString(1, modelTelefone.getNumero());
		statement.setLong(2, modelTelefone.getUsuario_pai_id().getId());
		statement.setLong(3, modelTelefone.getUsuario_cad_id().getId());

		statement.execute();
		connection.commit();
	}

	public void deleteFoneById(Long id) throws SQLException {
		String querySql = "delete from telefone where id=?";

		PreparedStatement statement = connection.prepareStatement(querySql);

		statement.setLong(1, id);

		statement.executeUpdate();

		connection.commit();
	}

	public boolean isTelefoneIgual(String numero, String idUser) throws Exception {
		String querySql = "select count(1) > 0 as existe from telefone where usuario_pai_id=? and numero='" + numero + "\'";

		PreparedStatement statement = connection.prepareStatement(querySql);
		statement.setLong(1, Long.valueOf(idUser));
		ResultSet retornoQuerySql = statement.executeQuery();

		retornoQuerySql.next();

		return retornoQuerySql.getBoolean("existe");
	}

}
