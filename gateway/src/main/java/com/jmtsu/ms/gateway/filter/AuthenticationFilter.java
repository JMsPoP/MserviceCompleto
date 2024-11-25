package com.jmtsu.ms.gateway.filter;

import com.jmtsu.ms.gateway.util.JwtUtil;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator routeValidator;
   // @Autowired
   // private RestTemplate template;
    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if(routeValidator.isSecured.test(exchange.getRequest())){
                if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                 throw new RuntimeException("autorização header está faltando");
                }
                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }
                try {
                    //template.getForObject("http://auth//validate?token"+ authheader, String.class);
                    jwtUtil.validateToken(authHeader);
                }catch (Exception e){
                    System.out.println("acesso invalido...");
                    throw new RuntimeException("um acesso não autorizado na aplicação");
                }
            }
            return chain.filter(exchange);
        });
    }

    public static class Config {

    }
}