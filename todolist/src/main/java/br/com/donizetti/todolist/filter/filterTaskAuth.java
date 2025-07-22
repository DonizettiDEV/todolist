package br.com.donizetti.todolist.filter;
import br.com.donizetti.todolist.user.UserRepository;
import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class filterTaskAuth extends OncePerRequestFilter{

    @Autowired 
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        var servletpath = request.getServletPath();
        if (servletpath.equals("/task/")) {
            var authorization = request.getHeader("Authorization");

            if (authorization == null || !authorization.startsWith("Basic ")) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Unauthorized: Missing or invalid Authorization header");
                return;
            }

            // Pega apenas a parte codificada em base64
            var authEncoded = authorization.substring("Basic ".length()).trim();
            byte[] authDecode = Base64.getDecoder().decode(authEncoded);
            var authString = new String(authDecode);

            String[] credentials = authString.split(":");
            String username = credentials[0];
            String password = credentials[1];
            var user = this.userRepository.findByusername(username);
            if (user == null) {
                response.sendError(401);
            } else {
                var passwordverify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                if (passwordverify.verified) {
                    request.setAttribute("iduser", user.getId());
                    filterChain.doFilter(request, response);
                } else {
                    filterChain.doFilter(request, response);
                }
            }
        }else{
             filterChain.doFilter(request, response);
        }
    }
   
}

