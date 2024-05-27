package com.feeham.concurrency;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class G2_SharedResourceHttp_DataSource {
    private Integer val = 0;

    public void increment(){
        System.out.println("Incrementing | " + Thread.currentThread().getName() + " | " + this.hashCode());
        val++;
    }

    public void decrement(){
        System.out.println("Decrementing | " + Thread.currentThread().getName() + " | " + this.hashCode());
        val--;
    }

}
