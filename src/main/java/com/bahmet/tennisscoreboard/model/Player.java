package com.bahmet.tennisscoreboard.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "players", indexes = {
        @Index(name = "idx_name_players", columnList = "name")
})
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "player1", cascade = CascadeType.ALL)
    private Set<Match> player1Matches = new HashSet<>();

    @OneToMany(mappedBy = "player2", cascade = CascadeType.ALL)
    private Set<Match> player2Matches = new HashSet<>();

    @OneToMany(mappedBy = "winner", cascade = CascadeType.ALL)
    private Set<Match> wonMatches = new HashSet<>();
}
