import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Input {

    static int choice = 0;
    static HttpResponse<String> searchResponse = null;

    public static String term() throws IOException, InterruptedException {
        // Prompts user to choose search mode
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter 1 to search by title or 2 to search by ingredient:");


        try {
            choice = scan.nextInt();

        } catch (Exception e){
            System.out.println("Please enter a valid choice...");
        }

        scan.nextLine();
        String results = null;

        while (choice != 1 || choice != 2) {

            if (choice == 1) {

                // Prompts user to enter string search term
                System.out.println("Enter a search term:");
                String searchTerm = scan.nextLine();

                // Creating Search Object that will return JSON String Body
                searchResponse = SearchConnect.searchTitle(searchTerm);

                // Json String Body from the HTTP response
                results = searchResponse.body();
                break;

            } else if (choice == 2) {

                // Prompts user to enter string search term
                System.out.println("Enter the first ingredient:");
                String ingredientOne = scan.nextLine();


                System.out.println("Enter the second ingredient:");
                String ingredientTwo = scan.nextLine();


                System.out.println("Enter the last ingredient:");
                String ingredientThree = scan.nextLine();

                // Creating Search Object that will return JSON String Body
                searchResponse = SearchConnect.searchIngredients(ingredientOne, ingredientTwo, ingredientThree);

                // Json String Body from the HTTP response
                results = searchResponse.body();
                break;

            } else {
                System.out.println("Please enter a valid choice...");
                System.out.println("Enter 1 to search by title or 2 to search by ingredient:");
                choice = scan.nextInt();
                scan.nextLine();
            }

        }

        return results;
    }
}
