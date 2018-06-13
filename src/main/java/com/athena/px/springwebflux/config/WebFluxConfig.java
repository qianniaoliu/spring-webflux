package com.athena.px.springwebflux.config;

import com.athena.px.springwebflux.webfluxhandler.UserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

/**
 * @Description:
 * @Author: ShenLong
 * @CreateDate: 2018/6/11 13:28
 */
@Configuration
@EnableWebFlux
public class WebFluxConfig implements WebFluxConfigurer {

    private final UserHandler userHandler;

    @Autowired
    public WebFluxConfig(UserHandler userHandler) {
        this.userHandler = userHandler;
    }


    @Bean
    public RouterFunction<ServerResponse> indexRoute(){
        RouterFunction<ServerResponse> routerFunction = RouterFunctions
                .route(POST("/webflux/user")
                    .and(accept(MediaType.APPLICATION_JSON)),userHandler::saveUser)
                .andRoute(GET("/webflux/get/user/{id}")
                        .and(accept(MediaType.APPLICATION_JSON)),userHandler::getUser);

        /*return route(POST("/webflux/person")
                .and(accept(MediaType.APPLICATION_JSON)),userHandler::saveUser)
                .andRoute(GET("/webflux/get/user")
                        .and(accept(MediaType.APPLICATION_JSON)),userHandler::getUser);*/
        return routerFunction;
    }
}
