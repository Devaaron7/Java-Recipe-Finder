package com.example.javarecipe.webapp;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class Test_GetCreditsLeft {

    @Test
    void thisShouldBeAValueNotLargerThan200() throws IOException, InterruptedException {
        AllEndpointsForApp credit = new AllEndpointsForApp();
        double value = credit.getCreditsLeft();
        assertTrue(value > 0 && value < 200);
    }

    @Test
    void thisShouldBeADouble() throws IOException, InterruptedException {
        AllEndpointsForApp credit = new AllEndpointsForApp();
        assertInstanceOf(Double.class, credit.getCreditsLeft());
    }

    //Can write exception test when close to out of credits
    @Test
    void aExceptionShouldBeRaisedWhenOutOfCredit() throws IOException, InterruptedException {
        AllEndpointsForApp credit = new AllEndpointsForApp();
        assertThrows(NullPointerException.class,
                () -> {
            credit.getCreditsLeft();
                });
    }
}