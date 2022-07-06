package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ModelLogin;

@WebServlet("/ServletLogin") // mapeamento da url que vem da tela
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletLogin() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String url = request.getParameter("url");
		
		if (login != null && !login.isEmpty() && senha != null && !senha.isEmpty()) {
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setLogin(login);
			modelLogin.setSenha(senha);
			
			if (modelLogin.getLogin().equalsIgnoreCase("admin") && modelLogin.getSenha().equalsIgnoreCase("admin")) {
				
				request.getSession().setAttribute("usuario", modelLogin.getLogin());
				
				if (url == null || url.equals("null")) {
					url = "principal/principal.jsp";
				}
				RequestDispatcher redirecionador = request.getRequestDispatcher(url);
				redirecionador.forward(request, response);
			} else {
				RequestDispatcher redirecionador = request.getRequestDispatcher("index.jsp");
				request.setAttribute("msg", "Informe os dados corretamente");
				redirecionador.forward(request, response);
			}
		} else {
			RequestDispatcher redirecionador = request.getRequestDispatcher("index.jsp");
			request.setAttribute("msg", "Informe os dados corretamente");
			redirecionador.forward(request, response);
		}
		
	}

}
