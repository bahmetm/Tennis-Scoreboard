package com.bahmet.tennisscoreboard.model;


import javax.persistence.*;

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

    public Player(String name) {
        this.name = name;
    }

    public Player() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
