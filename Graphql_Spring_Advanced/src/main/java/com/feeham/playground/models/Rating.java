package com.feeham.playground.models;

import com.feeham.playground.constants.enums.RatingType;
import com.feeham.playground.exceptions.CustomException;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

import static com.feeham.playground.constants.ApplicationConstants.MAX_RATING;
import static com.feeham.playground.constants.ApplicationConstants.MIN_RATING;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class Rating {
    private Integer ratingId;
    private Integer rate;
    private String comment;
    private Tourist ratedBy;
    private RatingType ratingType;
    private Integer targetId;
    private LocalDateTime created;
    private LocalDateTime updated;

    public void setRate(Integer rate){
        if(rate > MAX_RATING) {
            throw new CustomException("Maximum rating limit is " + MAX_RATING, HttpStatus.BAD_REQUEST);
        }
        if(rate < MIN_RATING) {
            throw new CustomException("Minimum rating limit is " + MIN_RATING, HttpStatus.BAD_REQUEST);
        }
        this.rate = rate;
    }
}
