package com.example.javarecipefinder.recipeapp;
//package com.example.javarecipefinder.recipeapp;
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
import com.example.javarecipefinder.recipeapp.Input;
import com.example.javarecipefinder.recipeapp.JsonFormatter;


@CrossOrigin
@RestController
public class PostTest {

    //static HashMap<String, String> user = new HashMap<>();

    public static void creditStatus(String left, String used, String requested) {
        System.out.println("Current credit requested by call: " + requested);
        System.out.println("Current amount used today: " + used);
        System.out.println("Current amount left for today: " + left);
    }

    @PostMapping(path="/test")
    public ResponseEntity<HttpStatus> read(@RequestBody InputPost file) throws IOException, InterruptedException {
        //System.out.println(file.getData());

        // Class that takes input from user and returns the JSON response
        //String results = Input.term();

        //String results = file.getData();

        // Logic that chooses correct class method to interpret JSON response based on user choice

        HttpResponse<String> searchResponse = null;

        String searchTerm = file.getData();


        // Creating Search Object that will return JSON String Body
        searchResponse = SearchConnect.searchTitle(searchTerm);

        // Json String Body from the HTTP response
        //results = searchResponse.body();


        ArrayList<HashMap> output;

        output = JsonFormatter.processTitle(searchResponse.body());

//        if (Input.choice == 1) {
//            output = JsonFormatter.processTitle(results);
//        } else {
//            output = JsonFormatter.processIngredients(results);
//        }

        // Output of formatted JSON to be 3 random results
        System.out.println(output);

        // Current additional output - Credits Left, Total Search Results, Ex
//        String creditsRequested = Input.searchResponse.headers().firstValue("x-api-quota-request").get();
//        String creditsLeft = Input.searchResponse.headers().firstValue("X-API-Quota-Left").get();
//        String creditsUsed = Input.searchResponse.headers().firstValue("x-api-quota-used").get();
//        creditStatus(creditsLeft, creditsUsed, creditsRequested);





        return ResponseEntity.ok(HttpStatus.OK);

    }

//    @PostMapping(path="/test")
//    public int read(@RequestBody InputPost file) {
//        System.out.println(file.getNum1());
//        System.out.println(file.getNum2());
//        //return ResponseEntity.ok(HttpStatus.OK);
//        return file.getNum1() + file.getNum2();
//    }
}
