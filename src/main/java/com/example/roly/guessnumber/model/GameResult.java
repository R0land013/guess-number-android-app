package com.example.roly.guessnumber.model;


public class GameResult {

    public static final int LOST_GAME = 0;
    public static final int WON_GAME = 1;
    public static final int NO_RESULT = 2;

    private int result;

    public GameResult(int result){
        this.result = result;
    }

    public boolean isLostGame(){
        return result == LOST_GAME;
    }

    public boolean isWonGame(){
        return result == WON_GAME;
    }

    public boolean isNoResultGame(){
        return result == NO_RESULT;
    }

}
