package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOTelefoneRepository;
import dao.DAOUsuarioRepository;
import model.ModelLogin;
import model.ModelTelefone;

@WebServlet("/ServletTelefone")
public class ServletTelefone extends ServletGenericUtil {
	private static final long serialVersionUID = 1L;
	
	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
	private DAOTelefoneRepository daoTelefoneRepository = new DAOTelefoneRepository();
       
    public ServletTelefone() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String acao = request.getParameter("acao");

			if (acao != null && !acao.isEmpty() && acao.equals("deletar")) {
				
				String idFone = request.getParameter("id");

				daoTelefoneRepository.deleteFoneById(Long.parseLong(idFone));
				
				String userPai = request.getParameter("userPai");
				ModelLogin modelLogin = daoUsuarioRepository.findUserById(Long.parseLong(userPai));
				request.setAttribute("modelLogin", modelLogin);
				
				List<ModelTelefone> modelTelefones = daoTelefoneRepository.findTelefoneById(modelLogin.getId());
				request.setAttribute("modelTelefones", modelTelefones);
				
				request.setAttribute("msg", "Telefone deletado com sucesso");
				
				request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);
				
				return;
			}
			
			String idUser = request.getParameter("idUser");
			
			if (idUser != null && !idUser.isEmpty()) {
				
				ModelLogin modelLogin = daoUsuarioRepository.findUserById(Long.parseLong(idUser));
				
				request.setAttribute("modelLogin", modelLogin);
				
				List<ModelTelefone> modelTelefones = daoTelefoneRepository.findTelefoneById(modelLogin.getId());
				
				request.setAttribute("modelTelefones", modelTelefones);
				
				request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);
			} else {
				List<ModelLogin> modelLogins = daoUsuarioRepository.findUserByIdPaginado(super.getIdUserLogado(request));
				request.setAttribute("modelLogins", modelLogins);
				request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getIdUserLogado(request)));
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		 
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {

			String usuario_pai_id = request.getParameter("id");
			String numero = request.getParameter("telefone");
			ModelLogin modelLogin = daoUsuarioRepository.findUserById(Long.parseLong(usuario_pai_id));
			
			if (numero != null && !numero.isEmpty()) {
				
				if (daoTelefoneRepository.isTelefoneIgual(numero,usuario_pai_id)) {
					
					
					request.setAttribute("modelLogin", modelLogin);
					
					request.setAttribute("msg", "Telefone já existe");
					request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);
					List<ModelTelefone> modelTelefones = daoTelefoneRepository.findTelefoneById(Long.parseLong(usuario_pai_id));
					
					request.setAttribute("modelTelefones", modelTelefones);
				} else {
					
					ModelTelefone modelTelefone = new ModelTelefone();
					
					modelTelefone.setNumero(numero);
					modelTelefone.setUsuario_pai_id(daoUsuarioRepository.findUserById(Long.parseLong(usuario_pai_id)));
					modelTelefone.setUsuario_cad_id(super.getUserLogadoObj(request));
					
					daoTelefoneRepository.gravaTelefone(modelTelefone);
					
					List<ModelTelefone> modelTelefones = daoTelefoneRepository.findTelefoneById(Long.parseLong(usuario_pai_id));
					
					request.setAttribute("modelLogin", modelLogin);
					request.setAttribute("modelTelefones", modelTelefones);
					request.setAttribute("msg", "Telefone salvo com sucesso");
					request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("modelLogin", modelLogin);
				List<ModelTelefone> modelTelefones = daoTelefoneRepository.findTelefoneById(Long.parseLong(usuario_pai_id));
				request.setAttribute("modelTelefones", modelTelefones);
				request.setAttribute("msg", "Por favor, informe um número");
				request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
			request.setAttribute("msgerro", e.getMessage());
			request.getRequestDispatcher("erro.jsp").forward(request, response);
		}
		
		
		
		
	}

}
