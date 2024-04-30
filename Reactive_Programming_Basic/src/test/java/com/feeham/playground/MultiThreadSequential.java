package com.feeham.playground;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class MultiThreadSequential {
    @Test
    public void runMultipleThreads() throws InterruptedException {
        print("@Beginning of parent");
        for(int i = 1; i <= 3; i++){
            prepareFood(i);
        }
        Thread.sleep(1000);
        print("@End of parent");
    }

    private Mono<Integer> prepareFood(Integer orderNo){
        print("@Ordered food #" + orderNo);
        subTask().subscribe((result1) -> {
            print("@Row-materials for food #" + orderNo);
            subTask().subscribe((result2) -> {
                print("@Coking food #" + orderNo);
                subTask().subscribe((result3) -> {
                    print("@Decorating for food #" + orderNo);
                });
            });
        });
        return Mono.just(0).delayElement(Duration.ofMillis(100));
    }

    private Mono<Integer> subTask(){
        return Mono.just(0).delayElement(Duration.ofMillis(100));
    }

    private void print(String message){
        System.out.println(message + " -> " + Thread.currentThread().getName() );
    }
}

