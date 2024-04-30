package com.feeham.playground.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class LanguageServiceMono {
    public Mono<String> helloJava(){
        System.out.println("Returning the result 1.");
        return Mono.just("Hello from Java!").delayElement(Duration.ofSeconds(2));
    }

    public Mono<String> helloPython(){
        System.out.println("Returning the result 2.");
        return Mono.just("Hello from Python").delayElement(Duration.ofSeconds(3));
    }

    public Mono<String> sayHellos(){
        helloJava();
        System.out.println("Called...");
        System.out.println("Started the sayHello method.");
        return helloJava().zipWith(helloPython())
                .map(tuple -> {
                    System.out.println("In side of the zipper.");
                    String resultFromSource1 = tuple.getT1();
                    String resultFromSource2 = tuple.getT2();
                    System.out.println("Returning the results.");
                    return resultFromSource1 + " and " + resultFromSource2;
                });
    }
}
