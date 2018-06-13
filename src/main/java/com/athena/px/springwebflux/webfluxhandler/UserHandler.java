package com.athena.px.springwebflux.webfluxhandler;

import com.athena.px.springwebflux.model.User;
import com.athena.px.springwebflux.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * @Description:
 * @Author: ShenLong
 * @CreateDate: 2018/6/11 16:04
 */
@Component
public class UserHandler {

    private final UserRepository userRepository;

    @Autowired
    public UserHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Mono<ServerResponse> saveUser(ServerRequest serverRequest){
        Mono<User> userMono = serverRequest.bodyToMono(User.class);
        Mono<Boolean> booleanMono = userMono.map(userRepository::saveUser);
        return ok().body(booleanMono,Boolean.class);
    }

    public Mono<ServerResponse> getUser(ServerRequest serverRequest){
        Integer id = Integer.valueOf(serverRequest.pathVariable("id"));
        Mono<User> userMono = Mono.just(userRepository.getUser(id));
//        userMono.flatMap(user1 -> ok().contentType(MediaType.APPLICATION_JSON).body(userMono,User.class));
        return ok().contentType(MediaType.APPLICATION_JSON).body(userMono,User.class);
    }
}
