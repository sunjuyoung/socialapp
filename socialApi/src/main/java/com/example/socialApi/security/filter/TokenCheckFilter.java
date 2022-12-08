package com.example.socialApi.security.filter;

import com.example.socialApi.service.AuthService;
import com.example.socialApi.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Log4j2
@RequiredArgsConstructor
public class TokenCheckFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;
    private final AuthService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        if(!path.startsWith("/api/") || path.startsWith("/api/auth/signUp")){
            filterChain.doFilter(request,response);
            return;
        }
        log.info("--- token check filter ----");
        try {
            String headerStr = request.getHeader("Authorization");
            if(headerStr == null || headerStr.length() < 8){
                log.info("--- token error 1 ----");
            }
            String tokenType = headerStr.substring(0,6);
            String tokenStr =  headerStr.substring(7);
            if(tokenType.equalsIgnoreCase("Bearer") == false){
                log.info("--- token error 2 ----");
            }

            Map<String, Object> payload = jwtUtil.validateToken(tokenStr);

            String nickname = (String)payload.get("nickname");
            log.info("--- nickname ---- " + nickname);

            filterChain.doFilter(request,response);
        }catch (RuntimeException e){
            log.error(e);
            throw new RuntimeException();
        }


    }
}
