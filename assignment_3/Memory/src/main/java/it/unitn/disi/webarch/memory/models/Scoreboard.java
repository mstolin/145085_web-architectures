package it.unitn.disi.webarch.memory.models;

import java.io.Serializable;
import java.util.*;

public class Scoreboard implements Serializable {

    private final Map<User, Integer> scores = new HashMap<>();

    public Scoreboard() {
    }

    public Map<User, Integer> getScores() {
        return this.scores;
    }

    public void addUserScore(User user, Integer points) {
        if (this.scores.containsKey(user)) {
            Integer currentPoints = this.scores.getOrDefault(user, 0);
            Integer updatedPoints = currentPoints + points;
            this.scores.put(user, updatedPoints);
        } else {
            this.scores.put(user, points);
        }
    }

    public Integer getPoints(User user) {
        return this.scores.getOrDefault(user, 0);
    }

    public boolean isEmpty() {
        return this.scores.isEmpty();
    }

}
