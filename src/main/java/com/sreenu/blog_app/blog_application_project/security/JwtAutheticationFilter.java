package com.sreenu.blog_app.blog_application_project.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAutheticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private  JwtTokenHelper jwtTokenHelper;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {


        String requestURI = request.getRequestURI();


        // 1️⃣ Skip JWT filter for login endpoint
        if (requestURI.contains("/api/v1/auth/login")) {
            filterChain.doFilter(request, response);
            return;
        }

//     1. get token

        String requestToken = request.getHeader("Authorization");

        // Bearer 4w534353hdfd

        System.out.println(requestToken);

        String userName = null;
        String token = null;

        if(requestToken != null && requestToken.startsWith("Bearer "))
        {
           token =   requestToken.substring(7); // means it will eliminate the bearer and will give actual token
            try {
                userName = jwtTokenHelper.getUserNameFromToken(token);
            }
            catch(IllegalArgumentException e)
            {
                System.out.println("Unable to get jwt token");
            }
            catch (ExpiredJwtException e) {
                System.out.println("Jwt token has expired");
            }
            catch (MalformedJwtException e){
                System.out.println("invalid jwt");
            }
        } else {
            System.out.println("Jwt token does not begin with Bearer");
        }

        //2. Once we got the token we have to validate

        if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null)
        {

           UserDetails userDetails =  userDetailsService.loadUserByUsername(userName);

             if(jwtTokenHelper.validateToken(token,userDetails)){
                 // sahi chal rha hai
                 // authentication karna hai

                 UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
                         new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                 usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                 SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
             } else {
                 System.out.println("Invalid jwt token");
             }
        }
        else
        {
             System.out.println("Username is null or context is not null");
        }

        filterChain.doFilter(request,response);
    }
}
