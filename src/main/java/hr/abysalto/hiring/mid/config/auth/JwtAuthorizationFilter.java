package hr.abysalto.hiring.mid.config.auth;

import hr.abysalto.hiring.mid.common.util.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.util.List;
import java.util.Objects;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final JwtUtils jwtUtils;

    public JwtAuthorizationFilter(AuthenticationManager authManager, JwtUtils jwtUtils) {
        super(authManager);
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) {
        try {
            var header = request.getHeader("Authorization");

            if (Objects.nonNull(header) && header.startsWith("Bearer ")) {
                var authentication = getAuthentication(request);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            filterChain.doFilter(request, response);

            filterChain.doFilter(request, response);
        } catch (Exception ex) {
            throw new BadCredentialsException("Invalid username or password.", ex);
        }
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        var tokenHeader = request.getHeader("Authorization");

        if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
            var token = tokenHeader.substring(7);

            if (jwtUtils.validateToken(token) && !jwtUtils.isTokenExpired(token)) {
                var username = jwtUtils.getUsernameFromToken(token);

                if (username != null) {
                    return new UsernamePasswordAuthenticationToken(username, null, List.of());
                }
            }
        }
        return null;
    }
}
