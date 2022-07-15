package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import connection.SingleConnectionBanco;

@WebFilter(urlPatterns = { "/principal/*" }) // Intercepta todas as requisições que vierem do projeto ou mapeamento
public class FilterAutenticacao implements Filter {

	private static Connection connection;

	public FilterAutenticacao() {
	}

	/* Encrerra os processos quando o servidor é parado */
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/** Intercepta as requisições e dá as respostas no sistema *
	 * Validar e fazer redirecionamento de páginas
	 * Validação de autenticação
	 * 
	 * 
	 * */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		try {

			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();

			String usuarioLogado = (String) session.getAttribute("usuario");

			String urlAutenticar = req.getServletPath(); // retorna a url que está sendo acessada

			// validar se está logado. Se não, redireciona para a tela de login
			if (usuarioLogado == null && !urlAutenticar.equalsIgnoreCase("/principal/ServletLogin")) { // não está logado
				RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url=" + urlAutenticar);
				request.setAttribute("msg", "É preciso estar logado no sistema");
				redireciona.forward(request, response);
				return; // para a execução e redireciona para o login
			} else {
				chain.doFilter(request, response);
			}
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			
			RequestDispatcher redirecionador = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionador.forward(request, response);
			
			try {
				connection.rollback(); // cancela a conexão em caso de erro
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	/** Inicia os processos ou recursos quando o servidor sobe o projeto 
	 *  Inicia a conexão com o banco
	 * */
	public void init(FilterConfig fConfig) throws ServletException {
		connection = SingleConnectionBanco.getConnection();
	}

}
