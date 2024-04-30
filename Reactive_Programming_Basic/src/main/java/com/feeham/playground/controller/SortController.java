package com.feeham.playground.controller;

import com.feeham.playground.models.SortResults;
import com.feeham.playground.service.SortService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/sort")
public class SortController {
    private final SortService sortService;

    @GetMapping
    public Mono<SortResults> getAll(){
        return sortService.sort();
    }
}