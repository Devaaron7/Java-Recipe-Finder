import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import secret.ApiKey;

public class SearchConnect {


    public static HttpResponse<String> searchTitle(String searchTerm) throws IOException, InterruptedException {


        // Forming Get Request using the search term provided by user
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create("https://api.spoonacular.com/recipes/complexSearch?query=" +
                        searchTerm + "&number=100&apiKey=" +
                        ApiKey.getKey("key")))
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


    public static HttpResponse<String> searchIngredients(String searchTermOne, String searchTermTwo, String searchTermThree) throws IOException, InterruptedException {


        // Forming Get Request using the search term provided by user
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create("https://api.spoonacular.com/recipes/findByIngredients?ingredients=" +
                        searchTermOne + ",+" +
                        searchTermTwo + ",+"+
                        searchTermThree + "&number=100&apiKey=" +
                        ApiKey.getKey("key")))
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


    public static HttpResponse<String> recipeInfo(String foodId) throws IOException, InterruptedException {


        // Forming Get Request using the search term provided by user
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create("https://api.spoonacular.com/recipes/" +
                        foodId +
                        "/information?includeNutrition=false&&apiKey=" +
                        ApiKey.getKey("key")))
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
