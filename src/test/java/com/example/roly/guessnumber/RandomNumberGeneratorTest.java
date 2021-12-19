package com.example.roly.guessnumber;


import com.example.roly.guessnumber.model.RandomNumberGenerator;

import static org.junit.Assert.*;
import org.junit.Test;

public class RandomNumberGeneratorTest {

    private static final int MIN = 1;
    private static final int MAX = 100;

    @Test
    public void testGenerateAtLeastTwoDifferentNumbers(){
        RandomNumberGenerator generator = new RandomNumberGenerator();



        int firstRandomNumber = generator.getGeneratedNumberBetweenInclusive(MIN, MAX);
        int quantityOfDifferentNumbers = 0;
        int currentRandomNumber;

        for(int i = 0; i < 100; i++){
            currentRandomNumber = generator.getGeneratedNumberBetweenInclusive(MIN, MAX);
            System.out.println(currentRandomNumber);
            if(currentRandomNumber != firstRandomNumber){
                quantityOfDifferentNumbers++;
            }
        }

        if(quantityOfDifferentNumbers > 1){
            assertTrue(true);
        }
        else{
            fail();
        }

    }
}
