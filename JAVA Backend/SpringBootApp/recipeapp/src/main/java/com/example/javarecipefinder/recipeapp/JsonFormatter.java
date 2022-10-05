import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class JsonFormatter {



    public static ArrayList<HashMap> processTitle(String resultsJson) throws IOException, InterruptedException {
        // Using Jackson Library to create a JSON Node object from the Json String
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode api = objectMapper.readTree(resultsJson);

        // Create Random integer for picking random search result later in the program
        Random randInt = new Random();
        int max = 0;


        // Logic to correct a bug that will cause choosing a random index fail. The total amount of returned food
        // items cannot be larger than 100 due to current limitations, even if the total amount in the database
        // is more than 100. Example - Database has 400 items that return for "apple", but the Json will only contain
        // a Maximum of 100. To avoid out of bounds index issues, setting up a conditional that will ensure we stay
        // within the correct range.

        if (api.size() > 100) {
            max = 99;
        } else {
            max = api.size();
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
        ArrayList <Integer> usedNum = new ArrayList<>();
        for (int i = 0; i < 3; i++) {

            // Logic to ensure number choice isn't reused by checking array for past choices
            boolean running = true;

            while (running) {
                int tempNum = randInt.nextInt(max);
                if (usedNum.contains(tempNum) == false) {
                    try {
                        chosenRecipesId.add(recipeResults.get(tempNum).get("id"));
                    }catch (Exception noMoreCredit) {
                        System.out.println("Error while pulling results from API (Are we out of credit?)");

                    }

                    usedNum.add(tempNum);
                    running = false;
                }
            }


        }

        //System.out.println(chosenRecipesId);


        // Loop that takes food ids from the Array chosenRecipesId, and gets the specific recipe information
        // per id, then assigned the key / value pair to the Array of Hashmaps ( chosenFoodList ) so that
        // we have 1 Array list, with 3 Hashmaps, that are the title, id, image & link for the specific
        // randomly chosen recipe. We'll use this data structure to pull the info we need for the Webpage using the
        // ".get" method on the Array Index for each Recipe.
        for (int i = 0; i < 3; i++) {

            HttpResponse<String> recipeResponse = SearchConnect.recipeInfo(chosenRecipesId.get(i).asText());
            JsonNode recipeJsonString = objectMapper.readTree(recipeResponse.body());
            chosenFoodList.get(i).put("id", recipeJsonString.get("id"));
            chosenFoodList.get(i).put("title", recipeJsonString.get("title"));
            chosenFoodList.get(i).put("sourceUrl", recipeJsonString.get("sourceUrl"));
            chosenFoodList.get(i).put("image", recipeJsonString.get("image"));

        }



        return chosenFoodList;
    }


    public static ArrayList<HashMap> processIngredients(String resultsJson) throws IOException, InterruptedException {
        // Using Jackson Library to create a JSON Node object from the Json String
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode api = objectMapper.readTree(resultsJson);

        // Create Random integer for picking random search result later in the program
        Random randInt = new Random();
        int max = 0;


        // Logic to correct a bug that will cause choosing a random index fail. The total amount of returned food
        // items cannot be larger than 100 due to current limitations, even if the total amount in the database
        // is more than 100. Example - Database has 400 items that return for "apple", but the Json will only contain
        // a Maximum of 100. To avoid out of bounds index issues, setting up a conditional that will ensure we stay
        // within the correct range.

        if (api.size() > 100) {
            max = 99;
        } else {
            max = api.size();
        }


        // Making JSON Object of just the Food Results , ie - Title="Pasta", ID="123456"
        //JsonNode recipeResults = api.get("results");
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

        // Gets three random food ids from the list of ingredient results
        for (int i = 0; i < 3; i++) {

            if (api.get(0).get("usedIngredientCount").asInt() >= 1) {
                chosenRecipesId.add(api.get(randInt.nextInt(max)).get("id"));
            }
        }

        // Loop that takes food ids from the Array chosenRecipesId, and gets the specific recipe information
        // per id, then assigned the key / value pair to the Array of Hashmaps ( chosenFoodList ) so that
        // we have 1 Array list, with 3 Hashmaps, that are the title, id, image & link for the specific
        // randomly chosen recipe. We'll use this data structure to pull the info we need for the Webpage using the
        // ".get" method on the Array Index for each Recipe.
        for (int i = 0; i < 3; i++) {

            HttpResponse<String> recipeResponse = SearchConnect.recipeInfo(chosenRecipesId.get(i).asText());
            JsonNode recipeJsonString = objectMapper.readTree(recipeResponse.body());
            chosenFoodList.get(i).put("id", recipeJsonString.get("id"));
            chosenFoodList.get(i).put("title", recipeJsonString.get("title"));
            chosenFoodList.get(i).put("sourceUrl", recipeJsonString.get("sourceUrl"));
            chosenFoodList.get(i).put("image", recipeJsonString.get("image"));

        }

        return chosenFoodList;
    }



}
