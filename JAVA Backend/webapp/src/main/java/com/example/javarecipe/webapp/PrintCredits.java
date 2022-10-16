package com.example.javarecipe.webapp;

import java.io.IOException;

public class PrintCredits {
    public static void main(String[] args) throws IOException, InterruptedException {
        AllEndpointsForApp credits = new AllEndpointsForApp();
        System.out.println(credits.getCreditsLeft());
    }
}
