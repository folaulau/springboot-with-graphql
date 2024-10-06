package com.folautech.graphql.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class GraphqlResponseInterceptor implements WebGraphQlInterceptor {
    @Override
    public Mono<WebGraphQlResponse> intercept(WebGraphQlRequest request, Chain chain) {
        return chain.next(request).doOnNext((response) -> {

            String value = response.getExecutionInput().getGraphQLContext().get("cookieName");
            log.info("cookieName={}", value);
            ResponseCookie cookie = ResponseCookie.from("cookieName", value).build();

            response.getResponseHeaders().add(HttpHeaders.SET_COOKIE, cookie.toString());
        });
    }
}
