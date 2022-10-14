package com.example.javarecipe.webapp;

import java.io.IOException;

public class PrintCredits {
    public static void main(String[] args) throws IOException, InterruptedException {
        PostTest credits = new PostTest();
        System.out.println(credits.getCreditsLeft());
    }
}
