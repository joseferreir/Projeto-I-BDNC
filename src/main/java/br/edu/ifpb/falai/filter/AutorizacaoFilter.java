/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.falai.filter;

import br.edu.ifpb.falai.entidade.User;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jose2
 */
@WebFilter("*.xhtml")
public class AutorizacaoFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain) throws IOException, ServletException {

        System.err.println("entro o filtro ID  ");
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
 User user = null;
        if (request.getRequestURI().endsWith("/index.xhtml")
                || request.getRequestURI().endsWith("/pageError/403.xhtml")
                || request.getRequestURI().endsWith("/cadastro_pessoa.xhtml")
                || request.getRequestURI()
                .contains("/javax.faces.resource/")) {
            // System.err.println("no if filter");
            chain.doFilter(req, res);
            // System.err.println("index filtro");
        } else {
            try {
                user = (User) session.getAttribute("usuario");
            } catch (Exception e) {
                System.err.println("exep filte "+e.getMessage());
                 response.sendRedirect(request.getContextPath()
                        + "/index.xhtml");
            }
            // System.err.println("passou do filtro com usuario " + user.getNome()+" id "+user.getId());
            if (user != null && user.getId() > 0) {
                //   System.err.println("if do uuu "+user.getId());
                chain.doFilter(req, res);
            } else {
                // throw new RuntimeException("faça login para ter acesso!");
               
                response.sendRedirect(request.getContextPath()
                        + "/index.xhtml");
                System.err.println("else do uuu ");
            }
        }

    }

    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
