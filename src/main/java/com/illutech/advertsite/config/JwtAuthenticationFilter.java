package com.illutech.advertsite.config;

import com.illutech.advertsite.config.customconfigs.CustomUserDetailService;
import com.illutech.advertsite.config.customconfigs.CustomUserDetails;
import com.illutech.advertsite.config.jwtconfig.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailService userDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws
            ServletException, IOException {
        final String authenticationHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String username;

        if (authenticationHeader == null || !authenticationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        jwtToken = authenticationHeader.substring(7); //pos 7 because bearer
        username = jwtService.extractUserName(jwtToken);
        //SecurityContextHolder.getContext().getAuthentication()==null //MEANS USER IS NOT YET AUTHENTICATED
        if (username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            CustomUserDetails userDetails = (CustomUserDetails) this.userDetailsService.loadUserByUsername(username);
            if (jwtService.isTokenValid(jwtToken,userDetails)){
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }
        }

    }
}
