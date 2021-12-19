package com.example.roly.guessnumber.model;




public class RandomNumberGenerator implements NumberGenerator {

    @Override
    public int getGeneratedNumberBetweenInclusive(int min, int max) {
        int randomValue = ((int)(Math.random() * max)) + min;
        if(randomValue > max){
            randomValue = max;
        }
        return randomValue;
    }
}
