package com.bahmet.tennisscoreboard.model;

import javax.persistence.*;

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

    public Match(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public Match() {

    }
}
