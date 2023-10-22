package com.bahmet.tennisscoreboard.model;

import jakarta.persistence.*;

@Entity
@Table(name = "matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "player_one_id", nullable = false)
    private Player player1;

    @ManyToOne
    @JoinColumn(name = "player_two_id", nullable = false)
    private Player player2;

    @ManyToOne
    @JoinColumn(name = "winner_id")
    private Player winner;
}
