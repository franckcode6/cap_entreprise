package fr.humanbooster.cap_entreprise.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

/**
 * Filtre à exécuter lorsque le serveur reçoit une requête HTTP
 * @author Franck
 *
 */
@Component
public class CheckSessionFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (((HttpServletRequest) request).getRequestURI().startsWith("/admin")
				&& ((HttpServletRequest) request).getSession().getAttribute("moderateur") == null) {
			System.out.println("Pas de session");
			((HttpServletResponse) response).sendRedirect("/erreur");
		} else if (((HttpServletRequest) request).getRequestURI().startsWith("/avis")
				&& (((HttpServletRequest) request).getSession().getAttribute("utilisateurConnecte")) == null) {
			System.out.println("Pas de session");
			((HttpServletResponse) response).sendRedirect("/");
		} else {
			chain.doFilter(request, response);
		}
	}

}
