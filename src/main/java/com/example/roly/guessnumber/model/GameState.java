package com.example.roly.guessnumber.model;


public class GameState {

    public static final int ENDED_GAME = 0;
    public static final int STARTED_GAME = 1;

    private int state;

    public GameState(int state){
        this.state = state;
    }

    public boolean equals(GameState other){
        return this.state == other.state;
    }

    public boolean isEndedGame(){
        return state == ENDED_GAME;
    }

    public boolean isStartedGame(){
        return state == STARTED_GAME;
    }
}
