package com.example.javarecipe.webapp;

import com.sun.jdi.IntegerType;
import org.assertj.core.api.IntegerAssert;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.InstanceOf;

import java.awt.geom.Arc2D;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PostTestTest {

    @Test
    void thisShouldBeAValueNotLargerThan200() throws IOException, InterruptedException {
        PostTest credit = new PostTest();
        double value = credit.getCreditsLeft();
        assertTrue(value > 0 && value < 200);
    }

    @Test
    void thisShouldBeADouble() throws IOException, InterruptedException {
        PostTest credit = new PostTest();
        //double value = credit.getCreditsLeft();
        assertInstanceOf(Double.class, credit.getCreditsLeft());
    }
}