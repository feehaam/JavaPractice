package com.feeham.playground.services.implementations;

import com.feeham.playground.exceptions.CustomException;
import com.feeham.playground.models.Tourist;
import com.feeham.playground.repositories.TouristRepository;
import com.feeham.playground.services.interfaces.TouristService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TouristServiceImpl implements TouristService {

    private final TouristRepository touristRepository;

    @Override
    public Tourist getTouristsById(Integer touristId) {
        Optional<Tourist> result = touristRepository.findById(touristId);
        if(result.isEmpty()){
            throw new CustomException("Tourist with ID "+ touristId + " was not found!", HttpStatus.NOT_FOUND);
        }
        return result.get();
    }

    @Override
    public List<Tourist> getTouristsByName(String name) {
        return touristRepository
                .findAll().stream().filter(tourist -> {
                    return tourist.getFirstName().toLowerCase()
                            .contains(name.toLowerCase()) || tourist
                            .getLastName().toLowerCase()
                            .contains(name.toLowerCase());
                }).toList();
    }

    @Override
    public Tourist create(Map<String, Object> input) {
        Tourist tourist = mapToObject(input, Tourist.class);
        tourist.setTouristId(0);
        return tourist;
    }

    @Override
    public Tourist update(Integer touristId, Map<String, Object> input) {
        Tourist touristUpdate = mapToObject(input, Tourist.class);
        Tourist tourist = getTouristsById(touristId);
        tourist.setBirthPlace(touristUpdate.getBirthPlace());
        tourist.setCurrentAddress(touristUpdate.getCurrentAddress());
        tourist.setFirstName(touristUpdate.getFirstName());
        tourist.setLastName(touristUpdate.getLastName());
        touristRepository.save(tourist);
        return tourist;
    }

    @Override
    public Boolean delete(Integer touristId) {
        getTouristsById(touristId);
        touristRepository.deleteById(touristId);
        return true;
    }
}
