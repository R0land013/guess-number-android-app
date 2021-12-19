package com.example.roly.guessnumber.view;


import com.example.roly.guessnumber.model.Answer;
import com.example.roly.guessnumber.model.GameResult;
import com.example.roly.guessnumber.view.exception.BadInputException;

public interface GuessNumberView {

    int getNumberFromUI() throws BadInputException;

    void openGameResultView(GameResult gameResult, Answer correctAnswer);

    void resetUIComponents();

}
