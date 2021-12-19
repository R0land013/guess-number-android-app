package com.example.roly.guessnumber.model;


import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class GuessingNumber {

    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 100;

    private static final int MAX_QUANTITY_OF_ANSWERS = 10;

    private static final int NO_ATTEMPT_NUMBER = -1;


    private GameState gameState;
    private GameResult gameResult;
    private NumberGenerator randomGenerator;
    private Answer correctAnswer;
    private List<Answer> givenAnswers;

    public GuessingNumber(NumberGenerator generator){
        gameState = new GameState(GameState.ENDED_GAME);
        gameResult = new GameResult(GameResult.NO_RESULT);
        randomGenerator = generator;
        givenAnswers = new LinkedList<Answer>();
    }

    public void startNewGame(){
        restartAllGameComponents();
    }

    private void restartAllGameComponents(){
        gameState = new GameState(GameState.STARTED_GAME);
        gameResult = new GameResult(GameResult.NO_RESULT);
        givenAnswers.clear();
        generateCorrectAnswer();

    }

    private void generateCorrectAnswer(){
        int randomNumber = randomGenerator.getGeneratedNumberBetweenInclusive(MIN_NUMBER, MAX_NUMBER);
        correctAnswer = new Answer(randomNumber, NO_ATTEMPT_NUMBER, Answer.CORRECT_ANSWER);
    }

    public Answer putAnswerNumber(int number){
        if(gameState.isEndedGame()){
            throw new RuntimeException("The game has not been started.");
        }

        if(number < MIN_NUMBER || number > MAX_NUMBER){
            throw new RuntimeException("The given number is out of bounds.");
        }

        Answer currentAnswer = constructAnswerByGivenNumber(number);

        addCurrentAnswerAndUpdateGameState(currentAnswer);

        return currentAnswer;
    }

    private Answer constructAnswerByGivenNumber(int currentNumber){
        int correctNumber = correctAnswer.getNumber();
        int currentAttempt = givenAnswers.size() + 1;

        if(currentNumber == correctNumber){
            return new Answer(currentNumber, currentAttempt, Answer.CORRECT_ANSWER);
        }
        else if(currentNumber < correctNumber){
            return new Answer(currentNumber, currentAttempt, Answer.TOO_LOW);
        }
        return new Answer(currentNumber, currentAttempt, Answer.TOO_HIGH);
    }

    public void addCurrentAnswerAndUpdateGameState(Answer currentAnswer){
        givenAnswers.add(currentAnswer);

        if(givenAnswers.size() == MAX_QUANTITY_OF_ANSWERS && !correctAnswer.equalAttempt(currentAnswer)){
            gameState = new GameState(GameState.ENDED_GAME);
            gameResult = new GameResult(GameResult.LOST_GAME);
        }
        else if(correctAnswer.equalAttempt(currentAnswer)){
            gameState = new GameState(GameState.ENDED_GAME);
            gameResult = new GameResult(GameResult.WON_GAME);
        }

    }

    public List<Answer> getGivenAnswers(){
        return Collections.unmodifiableList(givenAnswers);
    }

    public GameState getGameState(){
        return gameState;
    }

    public GameResult getGameResult(){
        return gameResult;
    }

    public int getQuantityOfTurns(){
        return MAX_QUANTITY_OF_ANSWERS;
    }

    public Answer getCorrectAnswer(){
        return correctAnswer;
    }
}
