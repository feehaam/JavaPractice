package com.feeham.playground.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Builder @Getter @Setter
public class Sort {
    public Long initTime;
    public Integer totalItems;
    public Long startTime;
    public Long endTime;
    public Long totalTimeTaken;
    public String threadName;
}
