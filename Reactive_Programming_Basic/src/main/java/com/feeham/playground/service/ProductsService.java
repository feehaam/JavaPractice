package com.feeham.playground.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class ProductsService {
    private final WebClient webClient;

    public ProductsService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://dummyjson.com").build();
    }
    public Flux<Object> getAllProducts(){
        System.out.println("In products service. Thread -> " + Thread.currentThread().getName());
        var productIds = Flux.just(1);
        return productIds.flatMap(this::getProduct);
    }

    private Mono<Object> getProduct(Integer productId){
        System.out.println("Getting product: " + productId + ". " + Thread.currentThread().getName());
        return fetchProduct(productId).flatMap(result -> {
            System.out.println("Got result: " + productId + ". " + Thread.currentThread().getName() + "\n");
            return Mono.just(result);
        });
    }

    private Mono<Object> fetchProduct(Integer productId){
        System.out.println("Fetching product: " + productId + ". " + Thread.currentThread().getName());
        long time = System.currentTimeMillis();
        return webClient.get()
                .uri("/products/{productId}", productId)
                .retrieve()
                .bodyToMono(Object.class)
                .flatMap(result -> {
                    System.out.println(result);
                    System.out.println("Started at " + time + " ended at " + System.currentTimeMillis()
                            + " | Diff: " + (System.currentTimeMillis() - time)
                            + " --> Thread: " + Thread.currentThread().getName());
                    return Mono.just(result);
                }).delayElement(Duration.ofMillis(1000));
/*              .delayElement(Duration.ofMillis(100)); */
/*
    Very important note: If I use the delayElement then it runs in multi thread, but removing it the whole
    process runs in a single thread! Yet all those calls are handled in parallel! How?
    Well, in reactive it's not necessary to run each sub-task in a different thread. It can ensure non-blocking
    asynchronous behaviour using its NIO single tread because it basically works in an event-listener based
    strategy. But when we put the delay, in that case, that line blocks the thread as a result, multiple threads
    comes to play. So 26th line prints different thread names for each iteration. Very much like MultiThread tests
    written in the test folders.
*/
    }
}
