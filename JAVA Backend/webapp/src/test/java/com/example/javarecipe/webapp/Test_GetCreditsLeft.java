package com.example.javarecipe.webapp;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class Test_GetCreditsLeft {

    @Test
    void thisShouldBeAValueNotLargerThan200() throws IOException, InterruptedException {
        PostTest credit = new PostTest();
        double value = credit.getCreditsLeft();
        assertTrue(value > 0 && value < 200);
    }

    @Test
    void thisShouldBeADouble() throws IOException, InterruptedException {
        PostTest credit = new PostTest();
        assertInstanceOf(Double.class, credit.getCreditsLeft());
    }

    //Can write exception test when close to out of credits
//    @Test
//    void aExceptionShouldBeRaisedWhenOutOfCredit() throws IOException, InterruptedException {
//        PostTest credit = new PostTest();
//        assertInstanceOf(Double.class, credit.getCreditsLeft());
//    }
}