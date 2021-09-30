package com.app.cofig;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.app.service.UserDetailsServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private JwtUtils jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		// extract authorization header
		final String requestTokenhandler = request.getHeader("Authorization");
		System.out.println(requestTokenhandler);
		String username = null;
		String jwtToken = null;

		if (requestTokenhandler != null && requestTokenhandler.startsWith("Bearer")) {
			// if yes
			jwtToken = requestTokenhandler.substring(7);// here 7 is bearer string length--- and we will remove string and only take token--after that
			
			try {
				username = this.jwtUtil.extractUsername(jwtToken);
				
			} catch (ExpiredJwtException e) {
				e.printStackTrace();
				System.out.println("Error" + e);
			}

		} else {
			System.out.println("Invalid token, not start with bearer string");
		}
		
		// once we get token >>> validate , then we will set security context holder
		if(username !=null  &&  SecurityContextHolder.getContext().getAuthentication()==null) 
		{
			final UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			
			if(this.jwtUtil.validateToken(jwtToken, userDetails))
			{
				// token is valid
				UsernamePasswordAuthenticationToken usernamePasswordAuthentication = new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
				
				usernamePasswordAuthentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthentication);
			}
		}else
		{
			System.out.println("Token is not valid");
		}
		
		filterChain.doFilter(request, response);
	}

}
