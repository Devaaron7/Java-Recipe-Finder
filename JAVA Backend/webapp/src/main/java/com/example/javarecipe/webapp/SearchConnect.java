package com.example.javarecipe.webapp;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class SearchConnect {


    public static HttpResponse<String> searchTitle(String searchTerm) throws IOException, InterruptedException {


        // Forming Get Request using the search term provided by user
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create("https://api.spoonacular.com/recipes/complexSearch?query=" +
                        searchTerm + "&number=100&apiKey=" +
                        secret.ApiKey.getKey("key")))
                .GET()
                .header("Accept", "application/json")
                .build();


        // Sending Get request & store HttpResponse in "response"
        HttpResponse<String> response = HttpClient
                .newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        return response;

    }

// Updated Ingredient API Call  that should be more accurate
    public static HttpResponse<String> searchIngredients(String searchTermOne, String searchTermTwo, String searchTermThree) throws IOException, InterruptedException {


        // Forming Get Request using the search term provided by user
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create("https://api.spoonacular.com/recipes/complexSearch?query=" +
                        searchTermOne + "&includeIngredients=" +
                        searchTermTwo + ",+"+
                        searchTermThree + "&number=100&apiKey=" +
                        secret.ApiKey.getKey("key")))
                .GET()
                .header("Accept", "application/json")
                .build();


        // Sending Get request & store HttpResponse in "response"
        HttpResponse<String> response = HttpClient
                .newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        return response;

    }

// Original Ingredient search logic that was giving fuzzy results ( not including required ingredients)
//    public static HttpResponse<String> searchIngredients(String searchTermOne, String searchTermTwo, String searchTermThree) throws IOException, InterruptedException {
//
//
//        // Forming Get Request using the search term provided by user
//        HttpRequest request = HttpRequest
//                .newBuilder()
//                .uri(URI.create("https://api.spoonacular.com/recipes/complexSearch?query=" +
//                        searchTermOne + "&includeIngredients=" +
//                        searchTermTwo + ","+
//                        searchTermThree + "&number=100&apiKey=" +
//                        ApiKey.getKey("key")))
//                .GET()
//                .header("Accept", "application/json")
//                .build();
//
//
//        // Sending Get request & store HttpResponse in "response"
//        HttpResponse<String> response = HttpClient
//                .newBuilder()
//                .build()
//                .send(request, HttpResponse.BodyHandlers.ofString());
//
//        return response;
//
//    }

    public static HttpResponse<String> recipeInfo(String foodId) throws IOException, InterruptedException {


        // Forming Get Request using the search term provided by user
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create("https://api.spoonacular.com/recipes/" +
                        foodId +
                        "/information?includeNutrition=false&&apiKey=" +
                        secret.ApiKey.getKey("key")))
                .GET()
                .header("Accept", "application/json")
                .build();


        // Sending Get request & store HttpResponse in "response"
        HttpResponse<String> response = HttpClient
                .newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        return response;

    }
}
