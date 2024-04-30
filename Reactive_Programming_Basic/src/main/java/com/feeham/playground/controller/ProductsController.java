package com.feeham.playground.controller;

import com.feeham.playground.service.ProductsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductsController {
    private final ProductsService productsService;

    @GetMapping
    public Flux<Object> getAll(){
        return productsService.getAllProducts();
    }

    @GetMapping("/test")
    public Flux<Object> test(){
        return Flux.empty();
    }

    // Will not print the numbers.
    private void approach1() {
        System.out.println("Approach 1 in action!");
        System.out.println(getNumbers());
    }

    // Will print the numbers
    private void approach2() {
        System.out.println("Approach 2 in action!");
        getNumbers().subscribe(System.out::println);
    }

    private Flux<Object> getNumbers(){
        System.out.println("Called get numbers.");
        Flux<Object> numbers = Flux.just(1, 2, 3, 4, 5);
        return numbers.flatMap(num -> {
            System.out.println("Extracting numbers #" + num + "@Tread: " + Thread.currentThread().getName());
            return explode(num).delayElements(Duration.ofMillis(100));
        });
    }

    private Flux<Object> explode(Object obj){
        Integer num = (Integer) obj;
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0; i< 5; i++) list.add(num);
        return Flux.fromIterable(list);
    }
}
