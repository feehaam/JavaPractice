package com.feeham.concurrency;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class G_SharedResourceHttp_DataSourceSync {
    private Integer val = 0;

    public synchronized void increment(){
        System.out.println("Incrementing | " + Thread.currentThread().getName() + " | " + this.hashCode());
        val++;
    }

    public synchronized void decrement(){
        System.out.println("Decrementing | " + Thread.currentThread().getName() + " | " + this.hashCode());
        val--;
    }

}
