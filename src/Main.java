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
import org.json.simple.parser.ParseException;

public class Main {

    public static void creditStatus(String left, String used, String requested) {
        System.out.println("Current credit requested by call: " + requested);
        System.out.println("Current amount used today: " + used);
        System.out.println("Current amount left for today: " + left);
        System.out.println("yoooooo");
    }


    public static void main(String[] args) throws IOException, InterruptedException, ParseException {

        // Prompts user to enter string search term
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a search term:");
        String searchTerm = scan.nextLine();

        // Creating Search Object that will return JSON String Body
        SearchConnect food = new SearchConnect();
        HttpResponse<String> searchResponse = food.search(searchTerm);


        // Json String Body from the HTTP response
        String results = searchResponse.body();


        // Using Jackson Library to create a JSON Node object from the Json String
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode api = objectMapper.readTree(results);

        // Create Random integer for picking random search result later in the program
        Random randInt = new Random();
        int max = 0;

        // Logic to correct a bug that will cause choosing a random index fail. The total amount of returned food
        // items cannot be larger than 100 due to current limitations, even if the total amount in the database
        // is more than 100. Example - Database has 400 items that return for "apple", but the Json will only contain
        // a Maximum of 100. To avoid out of bounds index issues, setting up a conditional that will ensure we stay
        // within the correct range.
        if (api.get("totalResults").asInt() > 100) {
            max = 99;
        } else {
            max = api.get("totalResults").asInt();
        }

        // Making JSON Object of just the Food Results , ie - Title="Pasta", ID="123456"
        JsonNode recipeResults = api.get("results");
        ArrayList <JsonNode> chosenRecipesId = new ArrayList<>();

        // Initialize three empty HashMaps that will contain the info for chosen recipes
        HashMap<String, String> foodOne = new HashMap<>();
        HashMap<String, String> foodTwo = new HashMap<>();
        HashMap<String, String> foodThree = new HashMap<>();

        // Initialize an empty Array that will house the three HashMaps - then adds the HashMaps to the array
        ArrayList <HashMap> chosenFoodList = new ArrayList<>();
        chosenFoodList.add(foodOne);
        chosenFoodList.add(foodTwo);
        chosenFoodList.add(foodThree);

        // Gets three random food ids from the list of search results
        for (int i = 0; i < 3; i++) {
            chosenRecipesId.add(recipeResults.get(randInt.nextInt(max)).get("id"));
        }

        // Loop that takes food ids from the Array chosenRecipesId, and gets the specific recipe information
        // per id, then assigned the key / value pair to the Array of Hashmaps ( chosenFoodList ) so that
        // we have 1 Array list, with 3 Hashmaps, that are the title, id, image & link for the specific
        // randomly chosen recipe. We'll use this data structure to pull the info we need for the Webpage using the
        // ".get" method on the Array Index for each Recipe.
        for (int i = 0; i < 3; i++) {

            HttpResponse<String> recipeResponse = food.recipeInfo(chosenRecipesId.get(i).asText());
            JsonNode recipeJsonString = objectMapper.readTree(recipeResponse.body());
            chosenFoodList.get(i).put("id", recipeJsonString.get("id"));
            chosenFoodList.get(i).put("title", recipeJsonString.get("title"));
            chosenFoodList.get(i).put("sourceUrl", recipeJsonString.get("sourceUrl"));
            chosenFoodList.get(i).put("image", recipeJsonString.get("image"));

        }

        System.out.println(chosenFoodList);



        // Current additional output - Credits Left, Total Search Results, Ex
        String creditsRequested = searchResponse.headers().firstValue("x-api-quota-request").get();
        String creditsLeft = searchResponse.headers().firstValue("X-API-Quota-Left").get();
        String creditsUsed = searchResponse.headers().firstValue("x-api-quota-used").get();
        System.out.println("Current Page offset: " + api.get("offset"));
        System.out.println("Number of recipes per return: " + api.get("number"));
        System.out.println("Total number of recipes that match search: " + api.get("totalResults"));
        creditStatus(creditsLeft, creditsUsed, creditsRequested);

    }
}