
package com.clearingcobwebsbackend.filters;

import java.io.IOException;

import org.apache.coyote.BadRequestException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.clearingcobwebsbackend.services.JWTService;
import com.clearingcobwebsbackend.services.UserDetailsServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor

@Component
public class JwtFilter extends OncePerRequestFilter {

  private final JWTService jwtService;
  private final UserDetailsServiceImpl userDetailsServiceImpl;

  @Override
  protected void doFilterInternal(HttpServletRequest request,
      HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    final String email;
    final String jwtToken;

    if (request.getServletPath().contains("/auth") | request.getServletPath().contains("/users/update-password")
        | (request.getServletPath().contains("/users") & request.getMethod().equals("POST"))) { // Tells JWT Filter
      // not to apply to
      // this route(s)
      filterChain.doFilter(request, response);
      return;
    }

    jwtToken = parseJwt(request);
    email = jwtService.extractEmail(jwtToken);

    if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(email);

      if (jwtService.validateToken(jwtToken, userDetails)) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(jwtToken, null,
            userDetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
      }
    }
    filterChain.doFilter(request, response);
  }

  private String parseJwt(HttpServletRequest request) {
    String authHeader = request.getHeader("Authorization");
    if (authHeader != null && authHeader.startsWith("Bearer ")) {
      return authHeader.substring(7);
    }
    return null;
  }
}
