package com.feeham.playground.controller;

import com.feeham.playground.service.LanguageServiceFlux;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController @RequiredArgsConstructor
public class FluxController {
    private final LanguageServiceFlux languageServiceFlux;

    // PLEASE HIT THE API FROM BROWSER!!
    // We can use both MediaType or response body

    @GetMapping(value = "/lang", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<String> getLanguagesDirectly() {
        return languageServiceFlux.getLanguages();
    }

    @GetMapping("/langos")
    @ResponseBody
    public Flux<String> getLanguagesAndOS(){
        return languageServiceFlux.fetchDataFromMultipleSources();
    }

    @GetMapping(value = "/c", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<String> getCFamilyLanguages(){
        return languageServiceFlux.getCFamilyLang();
    }
}
