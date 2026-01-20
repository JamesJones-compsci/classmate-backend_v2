package ca.gbc.comp3095.apigateway.config;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthorizationHeaderFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // Just forwards the Authorization header
        if (exchange.getRequest().getHeaders().containsKey("Authorization")) {
            String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
            exchange = exchange.mutate()
                    .request(r -> r.header("Authorization", authHeader))
                    .build();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1; // Run before other filters
    }
}
