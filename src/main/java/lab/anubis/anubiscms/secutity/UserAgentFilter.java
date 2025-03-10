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
public class UserAgentFilter extends OncePerRequestFilter {

    private static Logger log = LoggerFactory.getLogger(UserAgentFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("Les headers: "+request.getHeaderNames());
        String userAgent = request.getHeader("User-Agent");

        if (userAgent == null){
            log.info("User agent est null");
        }
        log.info("c'est vrai? "+userAgent);

        if (userAgent != null && userAgent.contains("Bruno")){
            System.out.println("Vous etes sur Bruno");
        }

        filterChain.doFilter(request, response);
    }
}
