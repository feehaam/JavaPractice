package com.feeham.playground.controller;

import com.feeham.playground.service.LanguageServiceMono;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController @RequiredArgsConstructor
public class MonoController {

    private final LanguageServiceMono languageService;

    @GetMapping("/hello")
    public Mono<String> sayHello(){
        return languageService.sayHellos();
    }
}
