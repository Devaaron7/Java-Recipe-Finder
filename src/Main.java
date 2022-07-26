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
    }


    public static void main(String[] args) throws IOException, InterruptedException, ParseException {

        // Prompts user to choose search mode
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter 1 to search by title or 2 to search by ingredient:");
        int choice = 0;
        try {
            choice = scan.nextInt();

        } catch (Exception e){
            System.out.println("Please enter a valid choice...");
        }

        scan.nextLine();
        String results = null;
        SearchConnect food = new SearchConnect();

        HttpResponse<String> searchResponse = null;

        while (choice != 1 || choice != 2) {

            if (choice == 1) {

                // Prompts user to enter string search term
                System.out.println("Enter a search term:");
                String searchTerm = scan.nextLine();

                // Creating Search Object that will return JSON String Body

                searchResponse = food.searchTitle(searchTerm);

                // Json String Body from the HTTP response
                results = searchResponse.body();

                //System.out.println(choice);
                break;

            } else if (choice == 2) {

                // Prompts user to enter string search term
                System.out.println("Enter the first ingredient:");
                //String ingredientOne = scan.nextLine();
                String ingredientOne = "cheese";

                System.out.println("Enter the second ingredient:");
                //String ingredientTwo = scan.nextLine();
                String ingredientTwo = "flour";

                System.out.println("Enter the last ingredient:");
                //String ingredientThree = scan.nextLine();
                String ingredientThree = "tomatosauce";

                // Creating Search Object that will return JSON String Body

                searchResponse = food.searchIngredients(ingredientOne, ingredientTwo, ingredientThree);

                // Json String Body from the HTTP response
                results = searchResponse.body();
                //System.out.println(choice);
                break;

            } else {
                System.out.println("Please enter a valid choice...");
                System.out.println("Enter 1 to search by title or 2 to search by ingredient:");
                //System.out.println(choice);
                choice = scan.nextInt();
                scan.nextLine();
            }

        }


        // return value from json class
        //System.out.println(chosenFoodList);


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