package com.helpinghandslocation.helpinghandslocation.config;

import com.helpinghandslocation.helpinghandslocation.utils.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService; // Cambia a tu clase de UserDetailsService

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Authorization");

        String username = null;
        String jwtToken = null;

        // El token JWT debe tener el prefijo "Bearer "
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                // Extraemos el nombre de usuario del token
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                throw new AuthenticationServiceException("No se pudo obtener el token JWT.");
            } catch (ExpiredJwtException e) {
                throw new AuthenticationServiceException("El token JWT ha expirado.");
            }
        } else if (requestTokenHeader != null) {
            throw new AuthenticationServiceException("El token JWT no comienza con 'Bearer '.");
        }

        // Una vez que obtenemos el username, validamos el token
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Cargamos los detalles del usuario
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

            // Si el token es válido, configuramos la autenticación de Spring Security
            if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Establecemos la autenticación en el contexto de seguridad
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            } else {
                throw new AuthenticationServiceException("El token JWT no es válido.");
            }
        }

        // Continuamos con el siguiente filtro en la cadena
        chain.doFilter(request, response);
    }
}
