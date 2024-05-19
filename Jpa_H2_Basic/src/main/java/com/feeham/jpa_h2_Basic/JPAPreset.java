package com.feeham.jpa_h2_Basic;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JPAPreset {
    private final GameRepo gameRepo;

    public JPAPreset(GameRepo gameRepo) {
        this.gameRepo = gameRepo;
    }

    @PostConstruct
    public void loadDb(){
        Game game1 = new Game("Chess", 2, 2, 7);
        Game game2 = new Game("Ludo", 4, 4, 5);
        Game game3 = new Game("Cricket", 22, 2, 6);
        Game game4 = new Game("Football", 22, 2, 10);

        gameRepo.saveAll(List.of(game1, game2, game3, game4));
    }
}
