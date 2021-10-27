package it.unitn.disi.webarch.memory.models;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class Scoreboard implements Serializable {

    private final Map<User, Integer> scores = new HashMap<>();
    private final Comparator<List<Serializable>> top5Comparator = (left, right) -> {
        Integer leftTuplePoints = (Integer) left.get(1);
        Integer rightTuplePoints = (Integer) right.get(1);

        return rightTuplePoints.compareTo(leftTuplePoints);
    };

    public Scoreboard() {
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

    public Map<User, Integer> getScores() {
        return this.scores;
    }

    public List<List<Serializable>> getTop5() {
        // map scores to list
        List<List<Serializable>> top5 = this.scores
                .keySet()
                .stream()
                .map((User user) -> Arrays.asList(user, this.getPoints(user)))
                .sorted(this.top5Comparator)
                .limit(5) // only first 5
                .collect(Collectors.toList());

        return top5;
    }

    public boolean isEmpty() {
        return this.scores.isEmpty();
    }

}
