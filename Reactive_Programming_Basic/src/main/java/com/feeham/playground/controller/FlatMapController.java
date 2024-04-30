package com.feeham.playground.controller;

import com.feeham.playground.service.FlatMapService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController @RequiredArgsConstructor
public class FlatMapController {
    private final FlatMapService flatMapService;
    @GetMapping(value = "/numbers", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Object> getNumbers(){
        return flatMapService.getNumbers();
    }
}
