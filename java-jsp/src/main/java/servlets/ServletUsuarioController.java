package servlets;

import java.io.IOException;
import java.util.List;

import org.apache.tomcat.jakartaee.commons.compress.utils.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DAOUsuarioRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.ModelLogin;

@MultipartConfig
@WebServlet(urlPatterns = { "/ServletUsuarioController" })
public class ServletUsuarioController extends ServletGenericUtil {

	private static final long serialVersionUID = 1L;

	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();

	public ServletUsuarioController() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String acao = request.getParameter("acao");

			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) {

				String idUser = request.getParameter("id");

				daoUsuarioRepository.deletarUsuario(idUser);

				List<ModelLogin> modelLogins = daoUsuarioRepository.buscarUsuarios(super.getUserLogado(request));
				request.setAttribute("modelLogins", modelLogins);

				request.setAttribute("msg", "Excluído com sucesso.");
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarAjax")) {

				String idUser = request.getParameter("id");

				daoUsuarioRepository.deletarUsuario(idUser);
				response.getWriter().write("Excluido com sucesso!");

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUser")) {

				String nomeBusca = request.getParameter("inputNomeBusca");

				List<ModelLogin> dadosJsonUser = daoUsuarioRepository.buscarUsuarios(nomeBusca,
						super.getUserLogado(request));

				ObjectMapper mapper = new ObjectMapper();

				String json = mapper.writeValueAsString(dadosJsonUser);

				response.getWriter().write(json);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar")) {

				String id = request.getParameter("id");

				ModelLogin usuario = daoUsuarioRepository.consultaUsuarioID(id, super.getUserLogado(request));

				request.setAttribute("msg", "Dados importados");
				request.setAttribute("modelusuario", usuario);

				List<ModelLogin> modelLogins = daoUsuarioRepository.buscarUsuarios(super.getUserLogado(request));
				request.setAttribute("modelLogins", modelLogins);

				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarUser")) {

				List<ModelLogin> modelLogins = daoUsuarioRepository.buscarUsuarios(super.getUserLogado(request));

				request.setAttribute("msg", "Usuarios carregados");
				request.setAttribute("modelLogins", modelLogins);
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);

			}

			else {
				List<ModelLogin> modelLogins = daoUsuarioRepository.buscarUsuarios(super.getUserLogado(request));
				request.setAttribute("modelLogins", modelLogins);
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			}

		} catch (

		Exception e) {

			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("erro.jsp").forward(request, response);

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String msg = "Operação realizada com sucesso!";

		try {
			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String perfil = request.getParameter("perfil");
			String sexo = request.getParameter("sexo");

			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setId(id != null && !id.isEmpty() ? Long.valueOf(id) : null);
			modelLogin.setNome(nome);
			modelLogin.setEmail(email);
			modelLogin.setLogin(login);
			modelLogin.setSenha(senha);
			modelLogin.setPerfil(perfil);
			modelLogin.setSexo(sexo);

			if (ServletFileUpload.isMultipartContent(request)) {
				Part part = request.getPart("fileFoto"); // pega a foto da tela

				byte[] foto = IOUtils.toByteArray(part.getInputStream()); // conver imagem para byte
				String imagemBase64 = "data:image/" + part.getContentType().split("\\/")[1] + ";base64,"
						+ new Base64().encodeBase64String(foto);

				modelLogin.setFotoUser(imagemBase64);
				modelLogin.setExtensaoFotoUser(part.getContentType().split("\\/")[1]);
			}

			if (daoUsuarioRepository.validarLogin(modelLogin.getLogin()) && modelLogin.getId() == null) {

				msg = "Já existe um usuário com este login. Por favor, informe outro.";

			} else {
				if (modelLogin.isNovo()) {
					msg = "Gravado com sucesso.";
				} else {
					msg = "Atualizado com sucesso.";
				}
				modelLogin = daoUsuarioRepository.gravarUsuario(modelLogin, super.getUserLogado(request));
			}

			request.setAttribute("msg", msg);
			request.setAttribute("modelusuario", modelLogin);

			List<ModelLogin> modelLogins = daoUsuarioRepository.buscarUsuarios(super.getUserLogado(request));
			request.setAttribute("modelLogins", modelLogins);

			request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);

		} catch (Exception e) {

			e.printStackTrace();

			request.setAttribute("msgerro", e.getMessage());
			request.getRequestDispatcher("erro.jsp").forward(request, response);

		}
	}

}
