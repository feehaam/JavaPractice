package com.feeham.concurrency;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/t1")
@RequiredArgsConstructor
public class G_SharedResourceHttp_Controller {
    private final G_SharedResourceHttp_DataSource data;

    private volatile int incCount = 0;
    private volatile int decCount = 0;

    @GetMapping("/inc")
    public String increment(){
        x();
        data.increment();
        return data.getVal() + " | " + incCount + " | " + decCount + " | " + (incCount - decCount);
    }

    private synchronized void x(){
        incCount++;
    }

    private synchronized void y(){
        decCount++;
    }

    @GetMapping("/dec")
    public String decrement(){
        y();
        data.decrement();
        return data.getVal() + " | " + incCount + " | " + decCount + " | " + (incCount - decCount);
    }

    @GetMapping("/val")
    public String getVal(){
        return data.getVal() + " | " + incCount + " | " + decCount + " | " + (incCount - decCount);
    }
}
