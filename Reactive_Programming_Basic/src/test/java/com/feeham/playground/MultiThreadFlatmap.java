package com.feeham.playground;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class MultiThreadFlatmap {
    @Test
    public void runMultipleThreads() throws InterruptedException {
        print("@Beginning of parent");
        for(int i = 1; i <= 3; i++){
            prepareFood(i).subscribe();
        }
        Thread.sleep(1000);
        print("@End of parent");
    }

    private Mono<Integer> prepareFood(Integer orderNo){
        print("@Ordered food #" + orderNo);
        subTask().flatMap(result1 -> {
            print("@Row-materials for food #" + orderNo);
            subTask().flatMap(result2 -> {
                print("@Coking food #" + orderNo);
                subTask().flatMap((result3) -> {
                    print("@Decorating for food #" + orderNo);
                    return Mono.just(0);
                });
                return Mono.just(0);
            });
            return Mono.just(0);
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

