package com.hr.system.authservice.service;


import com.hr.system.authservice.repository.UserRepository;
import com.hr.system.authservice.security.MyUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class MyUserDetailsService implements org.springframework.security.core.userdetails.ReactiveUserDetailsService {

    private final UserRepository userRepository;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return Mono.fromCallable(() -> userRepository.findByUsername(username))
                .flatMap(optionalUser -> optionalUser.map(user -> Mono.just((UserDetails) new MyUserDetails(user)))
                        .orElseGet(() -> Mono.error(new UsernameNotFoundException("User not found: " + username))));
    }
}