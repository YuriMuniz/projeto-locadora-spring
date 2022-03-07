package com.ymg.locadorafilmes.security.jwt;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.ymg.locadorafilmes.service.LoggedPersonDetailService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

public class JwtAuthFilter extends OncePerRequestFilter {

    private JwtService jwtService;
    private LoggedPersonDetailService loggedPersonService;

    public JwtAuthFilter(JwtService jwtService, LoggedPersonDetailService loggedPersonService) {
        this.jwtService = jwtService;
        this.loggedPersonService = loggedPersonService;
    }

    @Override
        protected void doFilterInternal(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain) throws ServletException, IOException{
            
            String authorization = httpServletRequest.getHeader("Authorization");

            if(authorization != null && authorization.startsWith("Bearer")){
                String token = authorization.split(" ")[1];
                boolean isValid = jwtService.isValidaToken(token);

                if(isValid){
                    String email = jwtService.getLoginUser(token);
                    UserDetails user = loggedPersonService.loadUserByUsername(email);
                    UsernamePasswordAuthenticationToken userAuth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    userAuth.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(userAuth);
                }
            }

            filterChain.doFilter(httpServletRequest, httpServletResponse);

        }
}
