package servlets;

import java.io.Serializable;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import dao.DAOUsuarioRepository;
import model.ModelLogin;

public class ServletGenericUtil extends HttpServlet implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
	
	public Long getIdUserLogado(HttpServletRequest request) throws Exception {
		
		String usuarioLogado = (String) request.getSession().getAttribute("usuario"); 
		
		return daoUsuarioRepository.consultaUsuarioLogado(usuarioLogado).getId();
	}
	
	public ModelLogin getUserLogadoObj(HttpServletRequest request) throws Exception {
		
		String usuarioLogado = (String) request.getSession().getAttribute("usuario"); 
		
		return daoUsuarioRepository.consultaUsuarioLogado(usuarioLogado);
	}
	
}
