package com.example.roly.guessnumber.presenter;

import com.example.roly.guessnumber.model.Answer;
import com.example.roly.guessnumber.model.GameResult;
import com.example.roly.guessnumber.model.GuessingNumber;
import com.example.roly.guessnumber.model.RandomNumberGenerator;
import com.example.roly.guessnumber.view.GuessNumberView;
import com.example.roly.guessnumber.view.exception.BadInputException;

import java.util.List;


public class MainPresenter {

    private GuessNumberView view;
    private GuessingNumber guessingNumberGame;

    public MainPresenter(GuessNumberView view){

        this.view = view;
        guessingNumberGame = new GuessingNumber(new RandomNumberGenerator());
    }

    public void startNewGame(){
        guessingNumberGame.startNewGame();
        view.resetUIComponents();
    }

    public void tryCurrentNumber(){
        int currentNumber = 0;
        try {
            currentNumber = view.getNumberFromUI();
        } catch (BadInputException e) {
            throw new RuntimeException("No validated field.");
        }

        guessingNumberGame.putAnswerNumber(currentNumber);

        Answer correctAnswer = guessingNumberGame.getCorrectAnswer();
        GameResult gameResult = guessingNumberGame.getGameResult();

        if(gameResult.isLostGame() || gameResult.isWonGame()){
            view.openGameResultView(gameResult, correctAnswer);
        }

    }




    public List<Answer> getGivenAnswerList(){
        return guessingNumberGame.getGivenAnswers();
    }



}
