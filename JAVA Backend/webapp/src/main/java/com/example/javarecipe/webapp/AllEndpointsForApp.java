package com.example.javarecipe.webapp;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.IOException;


@CrossOrigin
@RestController
public class AllEndpointsForApp {

    public static void creditStatus(String left, String used, String requested) {
        System.out.println("Current credit requested by call: " + requested);
        System.out.println("Current amount used today: " + used);
        System.out.println("Current amount left for today: " + left);
    }

    public double getCreditsLeft() throws IOException, InterruptedException {
        try{
            InputForRecipeTitle.term("null");
            return Double.parseDouble(InputForRecipeTitle.searchResponse.headers().firstValue("X-API-Quota-Left").get());
        }catch (Exception e) {
            throw new NullPointerException("We're out of credits");
        }
    }

    // Sends remaining credits to the front end as a String. Credits reset 8PM EST ( 12AM UTC)
    @GetMapping(path="/credit")
    public HashMap<String, Double> credit() throws IOException, InterruptedException {
        HashMap<String, Double> remainingCredits = new HashMap<>();
        double value = 0;
        InputForRecipeTitle.term("null");
        try {
            value = Double.parseDouble(InputForRecipeTitle.searchResponse.headers().firstValue("X-API-Quota-Left").get());
        }catch (NullPointerException e){
            value = Double.parseDouble(InputForIngredients.searchResponse.headers().firstValue("X-API-Quota-Left").get());
        }

        remainingCredits.put("credits", value);
        return remainingCredits;
    }

    @PostMapping(path="/recipe")
    public ArrayList<HashMap> read(@RequestBody TitleSearchData file) throws IOException, InterruptedException {

        // Class that takes input from user and returns the JSON response
        String results = InputForRecipeTitle.term(file.getData());

        //System.out.println(file.getData() instanceof String);

        // Logic that chooses correct class method to interpret JSON response based on user choice
        ArrayList<HashMap> output;
        if (InputForRecipeTitle.userInput == 1) {
            output = JsonFormatter.processTitle(results);
        } else {
            output = JsonFormatter.processIngredients(results);
        }

        // Output of formatted JSON to be 3 random results
        System.out.println(output);

        // Current additional output - Credits Left, Total Search Results, Ex
        String creditsRequested = InputForRecipeTitle.searchResponse.headers().firstValue("x-api-quota-request").get();
        String creditsLeft = InputForRecipeTitle.searchResponse.headers().firstValue("X-API-Quota-Left").get();
        String creditsUsed = InputForRecipeTitle.searchResponse.headers().firstValue("x-api-quota-used").get();
        creditStatus(creditsLeft, creditsUsed, creditsRequested);

        return output;


    }

    @PostMapping(path="/ingredient")
    public ArrayList<HashMap> read(@RequestBody IngredientSearchData file) throws IOException, InterruptedException {

        // Class that takes input from user and returns the JSON response
        String results = InputForIngredients.term(file.getTagList());

        // Logic that chooses correct class method to interpret JSON response based on user choice
        ArrayList<HashMap> output;
        if (InputForIngredients.userInput == 1) {
            output = JsonFormatter.processTitle(results);
        } else {
            output = JsonFormatter.processIngredients(results);
        }

        // Output of formatted JSON to be 3 random results
        //System.out.println(file.getTagList());
        System.out.println(output);

        // Current additional output - Credits Left, Total Search Results, Ex
        String creditsRequested = InputForIngredients.searchResponse.headers().firstValue("x-api-quota-request").get();
        String creditsLeft = InputForIngredients.searchResponse.headers().firstValue("X-API-Quota-Left").get();
        String creditsUsed = InputForIngredients.searchResponse.headers().firstValue("x-api-quota-used").get();
        creditStatus(creditsLeft, creditsUsed, creditsRequested);

        return output;

    }





}
