package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = { "/principal/*" }) // Intercepta todas as requisições que vierem do projeto ou mapeamento
public class FilterAutenticacao extends HttpFilter implements Filter {
       
    public FilterAutenticacao() {
    }
    
    /* Encrerra os processos quando o servidor é parado */
	public void destroy() {
	}

	/* Intercepta as requisições e dá as respostas no sistema */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		String usuarioLogado = (String) session.getAttribute("usuario");
		
		String urlAutenticar = req.getServletPath();
		
		// validar se está logado. Se não, redireciona para a tela de login
		if (usuarioLogado == null && !urlAutenticar.equalsIgnoreCase("/principal/ServletLogin")) { // não está logado
			RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url=" + urlAutenticar);
			request.setAttribute("msg", "É preciso estar logado no sistema");
			redireciona.forward(request, response);
			return; // para a execução e redireciona para o login
		} else {
			chain.doFilter(request, response);
		}
		
	}

	/* Inicia os processos ou recursos quando o servidor sobe o projeto */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
