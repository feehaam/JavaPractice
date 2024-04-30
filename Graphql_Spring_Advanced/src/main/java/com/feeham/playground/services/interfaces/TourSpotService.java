package com.feeham.playground.services.interfaces;

import com.feeham.playground.models.TourSpot;

import java.util.List;
import java.util.Map;

public interface TourSpotService extends CommonService{
    TourSpot getTourSpotById(Integer tourSpotId);
    List<TourSpot> getTourSpotsByRating(Integer min, Integer max);
    TourSpot create(Map<String, Object> input);
    TourSpot update(Integer tourSpotId, Map<String, Object> input);
    Boolean delete(Integer tourSpotId);
}
