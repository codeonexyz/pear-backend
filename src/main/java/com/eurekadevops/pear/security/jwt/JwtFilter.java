package com.eurekadevops.pear.security.jwt;

import static com.eurekadevops.pear.security.SecurityConstants.HEADER_STRING;
import static com.eurekadevops.pear.security.SecurityConstants.TOKEN_PREFIX;

import com.eurekadevops.pear.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.jsonwebtoken.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userService;
    
    @Autowired
    private TokenProvider tokenProvider;
    
    private Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader(HEADER_STRING);
        if (requestTokenHeader != null) logger.info("Request JWT: " + requestTokenHeader);
        
        String username = null;
        String jwt = null;
        
        if (requestTokenHeader != null && requestTokenHeader.startsWith(TOKEN_PREFIX)) {
            jwt = requestTokenHeader.substring(7);
            
            try {
                username = tokenProvider.getUsernameFromToken(jwt);
            } catch (SignatureException exc) {
                logger.warn(exc.getMessage());
            } catch (IllegalArgumentException exc) {
                logger.warn("Unable to get JWT Token");
            } catch (ExpiredJwtException exc) {
                logger.warn("JWT Token has expired");
            }
        } else {
            logger.warn("Request without JWT to public endpoint, or JWT does not begin with Bearer String");
        }
        
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userService.loadUserByUsername(username);
            
            if (tokenProvider.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthentication);
            }
        }
        
        chain.doFilter(request, response);
    }
}
