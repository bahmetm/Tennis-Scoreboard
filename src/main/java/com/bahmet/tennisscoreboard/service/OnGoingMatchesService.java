package com.bahmet.tennisscoreboard.service;

import com.bahmet.tennisscoreboard.model.Match;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OnGoingMatchesService {
    private final Map<String, Match> onGoingMatches = new ConcurrentHashMap<>();

    public OnGoingMatchesService() {
    }

    public String add(Match match) {
        String uuid = UUID.randomUUID().toString();
        while (onGoingMatches.containsKey(uuid)) {
            uuid = UUID.randomUUID().toString();
        }

        onGoingMatches.put(uuid, match);

        return uuid;
    }

    public Match getMatchByUUID(String uuid) {
        return onGoingMatches.get(uuid);
    }

    public Match get(String uuid) {
        return onGoingMatches.getOrDefault(uuid, null);
    }

    public void remove(String uuid) {
        onGoingMatches.remove(uuid);
    }

}
