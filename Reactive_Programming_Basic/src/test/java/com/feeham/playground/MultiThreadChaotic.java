package com.feeham.playground;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class MultiThreadChaotic {
    @Test
    public void runMultipleThreads() throws InterruptedException {
        print("@Beginning of parent.");
        for(int i = 1; i < 4; i++){
            fissionWave1(i);
        }
        Thread.sleep(1000);
        print("@End of parent");
    }

    private Mono<Integer> fissionWave1(Integer atomPair){
        print("@First fission wave, atom pair #" + atomPair);
        for(int i = 1; i < 4; i++){
            fissionWave2(atomPair).subscribe((result) -> {
                print("@Second fission wave, atom pair #" + result);
            });
        }
        return Mono.just(0).delayElement(Duration.ofMillis(100));
    }

    private Mono<Integer> fissionWave2(Integer atomPair){
        return Mono.just(atomPair).delayElement(Duration.ofMillis(100));
    }

    private void print(String message){
        System.out.println(message + " -> " + Thread.currentThread().getName() );
    }
}
