package com.example.javarecipe.webapp;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;


public class InputForIngredients {

    static int userInput = 0;
    static String secondItem = null;
    static String thirdItem = null;
    static HttpResponse<String> searchResponse = null;

    public static String term(HashMap<String, String> testSearch) throws IOException, InterruptedException {

        // Using an arraylist for the contains() method to validate user choices for integer prompts
        ArrayList<Integer> validChoiceInt = new ArrayList<Integer>();
        validChoiceInt.add(1);
        validChoiceInt.add(2);

        // Using an arraylist for the contains() method to validate user choices for string prompts
        ArrayList<String> validChoiceStr = new ArrayList<String>();
        validChoiceStr.add("y");
        validChoiceStr.add("n");

        // Scanner object that reads input from user throughout program
        //Scanner scan = new Scanner(System.in);

        // While loop that resets if an invalid choice is entered by User
        boolean isModeSet = false;
        while (!isModeSet) {

            // Prompts user to choose search mode
            //System.out.println("Enter 1 to search by title or 2 to search by ingredient:");

            // Verifies valid choice was made by user. If valid moves to next step. If invalid, User reenters.
            try {
                //userInput = scan.nextInt();
                userInput = 2;

            // Moves the scanner forward to account for the new-line character after using nextInt()
            //    scan.nextLine();
            } catch (Exception notInt) {
                System.out.println("Please enter a valid choice...");
            //    scan.nextLine();
                continue;
            }
            if (validChoiceInt.contains(userInput) != true) {
                System.out.println("Please enter a valid choice...");
                //scan.nextLine();
                continue;
            }else{
                isModeSet = true;
            }

        }

        // Initialize string that will be overwritten with search results after user inputs
        String results = null;


        // Conditional for the title search feature
//        if (userInput == 1) {
//
//            // Prompts user to enter string search term
//            //System.out.println("Enter a search term:");
//            //String searchTerm = scan.nextLine();
//            String searchTerm = testSearch;
//
//            // Creating Search Object that will return JSON String Body
//            searchResponse = SearchConnect.searchTitle(searchTerm);
//
//            // Json String Body from the HTTP response
//            results = searchResponse.body();
//        }

        // Conditional for the ingredient search feature.
        // Uses While loop to ensure valid choices per additional ingredient

        if (userInput == 2) {

            // Prompts user to enter string search term
            //System.out.println("Enter the first ingredient:");
            String ingredientOne = testSearch.get("tag1");

            boolean addIngredientValid = false;
            while(!addIngredientValid) {

                //System.out.println("Would you like to add another ingredient?");
                //System.out.println("y for Yes, n for No:");
                secondItem = "y";

                if (validChoiceStr.contains(secondItem) != true) {
                    System.out.println("Invalid choice, please try again.");
                    continue;
                }else{
                    addIngredientValid = true;
                }
            }

            if (secondItem.equals("n")) {
                String ingredientTwo = "";
                String ingredientThree = "";
                searchResponse = SearchConnect.searchIngredients(ingredientOne, ingredientTwo, ingredientThree);
                results = searchResponse.body();


            } else if (secondItem.equals("y")) {
                //System.out.println("Enter the second ingredient:");
                String ingredientTwo = testSearch.get("tag2");

                addIngredientValid = false;
                while(!addIngredientValid) {

                    //System.out.println("Would you like to a final ingredient?");
                    //System.out.println("y for Yes, n for No:");
                    thirdItem = "y";

                    if (validChoiceStr.contains(thirdItem) != true) {
                        System.out.println("Invalid choice, please try again.");
                        continue;
                    }else{
                        addIngredientValid = true;
                    }
                }

                if (thirdItem.equals("n") ) {
                    String ingredientThree = "";
                    searchResponse = SearchConnect.searchIngredients(ingredientOne, ingredientTwo, ingredientThree);
                    results = searchResponse.body();


                } else if (thirdItem.equals("y")) {

                    //System.out.println("Enter the last ingredient:");
                    String ingredientThree = testSearch.get("tag3");


                    searchResponse = SearchConnect.searchIngredients(ingredientOne, ingredientTwo, ingredientThree);


                    results = searchResponse.body();

                }
            }

        }



        return results;
    }
}
