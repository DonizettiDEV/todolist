package br.com.donizetti.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class filterTaskAuth extends OncePerRequestFilter{

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
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
        System.out.println(username);
        System.out.println(password);

        // Aqui você pode adicionar validação dos dados decodificados

        filterChain.doFilter(request, response);
    }

   
}

