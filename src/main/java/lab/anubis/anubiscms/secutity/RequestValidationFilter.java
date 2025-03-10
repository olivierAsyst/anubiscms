package lab.anubis.anubiscms.secutity;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//@Component
public class RequestValidationFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(RequestValidationFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("X-Valid-Request");
        if (header == null || !header.equals("true")){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid response");
            return;
        }
        filterChain.doFilter(request, response);
    }
}
