package fr.univlyon1.mif03.tpspring;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Maud on 30/11/2016.
 */
public class ErrorHandlerFilter implements Filter {

    private static final String ACCESS_PUBLIC = "/";
    private static final String ATT_PSEUDO = "pseudo";

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        String path = request.getRequestURI().substring(request.getContextPath().length());

        if( path.equals(ACCESS_PUBLIC)
                || request.getServletPath().startsWith("/resources")){
            filterChain.doFilter( request, response );
            return;
        }

        if ( session != null && session.getAttribute( ATT_PSEUDO ) == null ) {

            /* Redirection to public */
            request.getRequestDispatcher(ACCESS_PUBLIC).forward(request,response);
        } else {
            filterChain.doFilter( request, response );
        }

    }

    public void destroy() {

    }
}
