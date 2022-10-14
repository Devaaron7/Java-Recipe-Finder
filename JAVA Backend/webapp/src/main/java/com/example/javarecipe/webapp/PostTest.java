package com.example.javarecipe.webapp;
//package com.example.javarecipefinder.recipeapp;
import com.example.javarecipe.webapp.SumPost;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Random;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.javarecipe.webapp.Input;
import com.example.javarecipe.webapp.JsonFormatter;


@CrossOrigin
@RestController
public class PostTest {

    public static void creditStatus(String left, String used, String requested) {
        System.out.println("Current credit requested by call: " + requested);
        System.out.println("Current amount used today: " + used);
        System.out.println("Current amount left for today: " + left);
    }

    public double getCreditsLeft() throws IOException, InterruptedException {
        Input.term("null");
        return Double.parseDouble(Input.searchResponse.headers().firstValue("X-API-Quota-Left").get());
    }


    @PostMapping(path="/test")
    public ArrayList<HashMap> read(@RequestBody InputPost file) throws IOException, InterruptedException {

        // Class that takes input from user and returns the JSON response
        String results = Input.term(file.getData());

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

        return output;

    }


    // Sends remaining credits to the front end as a String. Credits reset 8PM EST ( 12AM UTC)
    @GetMapping(path="/credit")
    public String credit() {
        return Input.searchResponse.headers().firstValue("X-API-Quota-Left").get();
    }


}
