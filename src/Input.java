import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Scanner;

public class Input {

    static int userInput = 0;
    static String secondItem = null;
    static String thirdItem = null;
    static HttpResponse<String> searchResponse = null;
    
    
    
    

    public static String term() throws IOException, InterruptedException {

        // Using an arraylist for the contains() method to validate user choices for integer prompts
        ArrayList<Integer> validChoiceInt = new ArrayList<Integer>();
        validChoiceInt.add(1);
        validChoiceInt.add(2);

        // Using an arraylist for the contains() method to validate user choices for string prompts
        ArrayList<String> validChoiceStr = new ArrayList<String>();
        validChoiceStr.add("y");
        validChoiceStr.add("n");

        // Prompts user to choose search mode
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter 1 to search by title or 2 to search by ingredient:");

        // Verifies valid choice was made by user. If valid moves to next step. If invalid, User reenters.
        try {
            userInput = scan.nextInt();

        // Moves the scanner forward to account for the new-line character after using nextInt()
            scan.nextLine();
        } catch (Exception notInt) {
            System.out.println("Please enter a valid choice...");
            term();
        }
        if (validChoiceInt.contains(userInput) != true) {
            System.out.println("Please enter a valid choice...");
            term();
        }


        String results = null;

        while (true) {

            if (userInput == 1) {

                // Prompts user to enter string search term
                System.out.println("Enter a search term:");
                String searchTerm = scan.nextLine();

                // Creating Search Object that will return JSON String Body
                searchResponse = SearchConnect.searchTitle(searchTerm);

                // Json String Body from the HTTP response
                results = searchResponse.body();

                // Breaks while loop and exits Input class
                break;
            }
            
            if (userInput == 2) {

                // Prompts user to enter string search term
                System.out.println("Enter the first ingredient:");
                String ingredientOne = scan.nextLine();

                // Working on logic that will allow up to 3 ingredients
                System.out.println("Would you like to add another ingredient?");
                System.out.println("y for Yes, n for No:");
                secondItem = scan.nextLine().toLowerCase();

                if (validChoiceStr.contains(secondItem) != true) {
                    System.out.println("Invalid choice, please try again.");
                    Input.term();
                }

                if (secondItem.equals("n")) {
                    String ingredientTwo = "";
                    String ingredientThree = "";
                    searchResponse = SearchConnect.searchIngredients(ingredientOne, ingredientTwo, ingredientThree);
                    results = searchResponse.body();
                    break;

                } else if (secondItem.equals("y")) {
                    System.out.println("Enter the second ingredient:");
                    String ingredientTwo = scan.nextLine();
                    System.out.println("Would you like to a final ingredient?");
                    System.out.println("y for Yes, n for No:");
                    thirdItem = scan.nextLine().toLowerCase();

                    if (validChoiceStr.contains(thirdItem) != true) {
                        System.out.println("Invalid choice, please try again.");
                        Input.term();
                    }

                    if (thirdItem.equals("n") ) {
                        String ingredientThree = "";
                        searchResponse = SearchConnect.searchIngredients(ingredientOne, ingredientTwo, ingredientThree);
                        results = searchResponse.body();
                        break;

                    } else if (thirdItem.equals("y")) {

                        System.out.println("Enter the last ingredient:");
                        String ingredientThree = scan.nextLine();

                        // Creating Search Object that will return JSON String Body
                        searchResponse = SearchConnect.searchIngredients(ingredientOne, ingredientTwo, ingredientThree);

                        // Json String Body from the HTTP response
                        results = searchResponse.body();
                        break;
                    }
                }

            }

            break;

        }

        return results;
    }
}
