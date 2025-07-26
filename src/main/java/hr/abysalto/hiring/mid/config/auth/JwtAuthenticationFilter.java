package hr.abysalto.hiring.mid.config.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import hr.abysalto.hiring.mid.common.util.JwtUtils;
import hr.abysalto.hiring.mid.user.model.AccessRequest;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@AllArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        try {
            var accessRequest = new ObjectMapper().readValue(request.getInputStream(), AccessRequest.class);
            var token = new UsernamePasswordAuthenticationToken(accessRequest.getUsername(), accessRequest.getPassword());
            return authenticationManager.authenticate(token);
        } catch (Exception e) {
            throw new BadCredentialsException("Failed to parse login request.", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) {
        var token = jwtUtils.generateToken(authResult.getName());
        response.addHeader("Authorization", "Bearer " + token);
        response.setContentType("application/json");

        try {
            response.getWriter().write("{\"token\":\"" + token + "\"}");
        } catch (IOException ex) {
            throw new BadCredentialsException("Failed to return token.", ex);
        }
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        try {
            response.getWriter().write("{\"error\":\"Invalid username or password\"}");
        } catch (IOException ex) {
            throw new BadCredentialsException("Invalid username or password.", ex);
        }
    }
}