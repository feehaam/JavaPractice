package com.feeham.jpa_h2_Basic;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public String name;
    public Integer playerInvolved;
    public Integer numberOfSides;
    public Integer popularityIndex;

    public Game(String name, Integer playerInvolved, Integer numberOfSides, Integer popularityIndex){
        this.name = name;
        this.playerInvolved = playerInvolved;
        this.numberOfSides = numberOfSides;
        this.popularityIndex = popularityIndex;
    }
}
