package com.example.roly.guessnumber;

import com.example.roly.guessnumber.model.Answer;
import com.example.roly.guessnumber.model.GameResult;
import com.example.roly.guessnumber.model.GameState;
import com.example.roly.guessnumber.model.GuessingNumber;
import com.example.roly.guessnumber.model.NumberGenerator;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.List;


public class GuessingNumberTest {

    public static NumberGenerator generatorOfThree = new NumberGenerator() {
        @Override
        public int getGeneratedNumberBetweenInclusive(int min, int max) {
            return 3;
        }
    };


    @Test
    public void testThatTheNumberIsGuessedBeforeMaxQuantityOfTurns(){

        GuessingNumber guessingNumber = new GuessingNumber(generatorOfThree);

        guessingNumber.startNewGame();
        guessingNumber.putAnswerNumber(5);
        guessingNumber.putAnswerNumber(2);
        Answer lastAnswer = guessingNumber.putAnswerNumber(3);

        GameState gameState = guessingNumber.getGameState();
        GameResult gameResult = guessingNumber.getGameResult();
        assertTrue(gameState.isEndedGame() && gameResult.isWonGame() && lastAnswer.isCorrectAnswer());

    }

    @Test
    public void testThatSomeAnswerAreTooHighOrTooLow(){
        GuessingNumber guessingNumber = new GuessingNumber(generatorOfThree);

        guessingNumber.startNewGame();
        Answer tooHighAnswer = guessingNumber.putAnswerNumber(5);
        Answer tooLowAnswer = guessingNumber.putAnswerNumber(2);

        assertTrue(tooHighAnswer.isTooHighAnswer() && tooLowAnswer.isTooLowAnswer());
    }

    @Test
    public void testThatTheGameIsLost(){
        GuessingNumber guessingNumber = new GuessingNumber(generatorOfThree);

        guessingNumber.startNewGame();
        Answer lastAnswer = null;
        for(int answerNumber = 1; answerNumber <= guessingNumber.getQuantityOfTurns(); answerNumber++){
            lastAnswer = guessingNumber.putAnswerNumber(5);
        }

        GameState gameState = guessingNumber.getGameState();
        GameResult gameResult = guessingNumber.getGameResult();

        assertTrue(gameState.isEndedGame() && gameResult.isLostGame() &&(!lastAnswer.isCorrectAnswer()));


    }


    @Test
    public void testThatTheGameIsRestarted(){
        GuessingNumber guessingNumber = new GuessingNumber(generatorOfThree);

        guessingNumber.startNewGame();
        guessingNumber.putAnswerNumber(5);

        guessingNumber.startNewGame();
        List<Answer> givenAnswers = guessingNumber.getGivenAnswers();

        GameState gameState = guessingNumber.getGameState();
        GameResult gameResult = guessingNumber.getGameResult();

        assertTrue(gameState.isStartedGame() && gameResult.isNoResultGame() && givenAnswers.isEmpty());

    }

    @Test
    public void testGetGivenAnswers(){
        GuessingNumber guessingNumber = new GuessingNumber(generatorOfThree);
        guessingNumber.startNewGame();

        guessingNumber.putAnswerNumber(5);
        guessingNumber.putAnswerNumber(2);
        guessingNumber.putAnswerNumber(3);

        List<Answer> givenAnswers = guessingNumber.getGivenAnswers();

        assertTrue(givenAnswers.size() == 3);


    }

}