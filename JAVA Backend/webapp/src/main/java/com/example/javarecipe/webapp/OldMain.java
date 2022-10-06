package com.example.javarecipe.webapp;
import java.io.IOException;


public class OldMain {

    public static void creditStatus(String left, String used, String requested) {
        System.out.println("Current credit requested by call: " + requested);
        System.out.println("Current amount used today: " + used);
        System.out.println("Current amount left for today: " + left);
    }

    public static void oldMain(String[] args) throws IOException, InterruptedException {

        /*
        // Class that takes input from user and returns the JSON response
        String results = Input.term();

        // Logic that chooses correct class method to interpret JSON response based on user choice
        ArrayList<HashMap> output;
        if (Input.userInput == 1) {
            output = JsonFormatter.processTitle(results);
        } else {
            output = JsonFormatter.processIngredients(results);
        }

        // Output of formatted JSON to be 3 random results
        System.out.println(output);


        // Current additional output - Credits Left, Total Search Results, Ex
        String creditsRequested = Input.searchResponse.headers().firstValue("x-api-quota-request").get();
        String creditsLeft = Input.searchResponse.headers().firstValue("X-API-Quota-Left").get();
        String creditsUsed = Input.searchResponse.headers().firstValue("x-api-quota-used").get();
        creditStatus(creditsLeft, creditsUsed, creditsRequested);
    */
    }


}