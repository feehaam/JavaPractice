package com.feeham.playground.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;

@Service
public class FlatMapService {
    public Flux<Object> getNumbers(){
        Flux<Object> numbers = Flux.just(1, 2, 3, 4, 5);
        return numbers.flatMap(num -> {
            return explode(num).delayElements(Duration.ofMillis(300));
        });
    }

    private Flux<Object> explode(Object obj){
        Integer num = (Integer) obj;
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0; i< 5; i++) list.add(num);
        return Flux.fromIterable(list);
    }
}
