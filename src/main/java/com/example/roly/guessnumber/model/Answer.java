package com.example.roly.guessnumber.model;

public class Answer {

    public static final int TOO_LOW = 0;
    public static final int CORRECT_ANSWER = 1;
    public static final int TOO_HIGH = 2;

    private int categorization;
    private int number;
    private int attemptNumber;

    public Answer(int number, int attemptNumber, int categorization){
        this.number = number;
        this.attemptNumber = attemptNumber;
        this.categorization = categorization;
    }

    public boolean equals(Answer otherAnswer){
        return this.number == otherAnswer.number &&
                this.attemptNumber == otherAnswer.attemptNumber &&
                this.categorization == otherAnswer.categorization;
    }

    public boolean equalAttempt(Answer other){
        return this.number == other.number &&
                this.categorization == other.categorization;
    }

    public int getNumber() {
        return number;
    }

    public int getAttemptNumber() {
        return attemptNumber;
    }

    public boolean isTooLowAnswer(){
        return categorization == TOO_LOW;
    }

    public boolean isTooHighAnswer(){
        return categorization == TOO_HIGH;
    }

    public boolean isCorrectAnswer(){
        return categorization == CORRECT_ANSWER;
    }


}
