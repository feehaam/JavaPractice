package com.feeham.playground.service;

import com.feeham.playground.models.Sort;
import com.feeham.playground.models.SortResults;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class SortService {
    private final int ITEM_COUNT = 10000;

    public Mono<SortResults> sort() {
        SortResults results = new SortResults();
        List<Mono<Sort>> sortMonos = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            sortMonos.add(getSortObject().flatMap(this::runSort));
        }

        Flux.merge(sortMonos)
                .doOnNext(results.sortItems::add)
                .doOnComplete(() -> calculateTotalTime(results))
                .subscribe();

        return Mono.just(results);
    }

    private Mono<Sort> getSortObject() {
        return Mono.just(
                Sort.builder()
                        .initTime(System.currentTimeMillis())
                        .totalItems(ITEM_COUNT)
                        .build()
        );
    }

    private Mono<Sort> runSort(Sort sort) {
        sort.setStartTime(System.currentTimeMillis());
        bubbleSort(getUnsortedItems());
        sort.setThreadName(Thread.currentThread().getName());
        sort.setEndTime(System.currentTimeMillis());
        sort.setTotalTimeTaken(sort.getEndTime() - sort.getStartTime());
        return Mono.just(sort);
    }

    private void bubbleSort(List<Integer> items) {
        int n = items.size();
        boolean swapped;
        do {
            swapped = false;
            for (int i = 1; i < n; i++) {
                if (items.get(i - 1) > items.get(i)) {
                    // swap items[i-1] and items[i]
                    int temp = items.get(i - 1);
                    items.set(i - 1, items.get(i));
                    items.set(i, temp);
                    swapped = true;
                }
            }
            n--;
        } while (swapped);
    }

    private List<Integer> getUnsortedItems() {
        Random rand = new Random();
        List<Integer> items = new ArrayList<>();
        for (int i = 0; i < ITEM_COUNT; i++) {
            items.add(rand.nextInt(ITEM_COUNT));
        }
        return items;
    }

    private void calculateTotalTime(SortResults results) {
        results.sortItems.forEach(sort ->
                sort.setTotalTimeTaken(sort.getEndTime() - sort.getStartTime()));
    }
}
