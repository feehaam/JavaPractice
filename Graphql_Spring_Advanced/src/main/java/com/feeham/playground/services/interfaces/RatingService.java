package com.feeham.playground.services.interfaces;
import com.feeham.playground.models.Rating;
import java.util.Map;

public interface RatingService extends CommonService{
    Rating create(Map<String, Object> input);
    Rating update(Integer ratingId, Map<String, Object> input);
    Boolean delete(Integer ratingId);
}
