import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Scanner;

public class CoffeeOrders {
    public static void main(String[] args) {
        Scanner gaby = new Scanner(System.in);

        // declare variables
        String []coffeeNames = {"1. Espresso",
                                "2. Latte",
                                "3. Cappuccino",
                                "4. Mocha"};
        double[] prices = {50.0,
                           70.0,
                           65.0,
                           80.0};
        double vatRate = 0.12;
        int [] orderQuantities = new int[4];
        double subtotal = 0;

        // display the coffee menu
        while (true) {
            System.out.println("--- Coffee Menu ---");
            for (int i = 0; i < 4; i++) {
                System.out.println(coffeeNames[i] + " - " + prices[i] + " PHP");
            }

            // allow the user to input their order
            System.out.println("0. Finish Order");
            System.out.print("Choose your coffee (1-4, 0 to finish): ");
            int coffee = gaby.nextInt();

            if (coffee == 0) {
                break;
            }

            //allow the user to input the quantity of their order
            if (coffee >= 1 && coffee <= 4) {
                System.out.print("Enter quantity: ");
                int quantity = gaby.nextInt();
                orderQuantities[coffee - 1] += quantity;
                subtotal += prices[coffee - 1] * quantity;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
        // get the grandtotal
        double receipt = 0;
        double vat = subtotal * vatRate;
        double grandTotal = subtotal + vat;

        // display the receipt and the results
        StringWriter gab = new StringWriter();
        gab.append("\n--- Coffee Order Receipt ---\n");
        for (int i = 0; i < 4; i++) {
            if(orderQuantities[i] > 0) {
                gab.append(String.format("%s x %d = %.2f PHP\n", coffeeNames[i],
                        orderQuantities[i], prices[i] * orderQuantities[i]));
            }

        }
        gab.append(String.format("Subtotal: %.2f PHP\n", subtotal));
        gab.append(String.format("VAT (12%%): %.2f PHP\n", vat));
        gab.append(String.format("Grand Total: %.2f PHP\n", grandTotal));

        try (FileWriter writer = new FileWriter("coffeeReceipt.txt")) {
            writer.write(gaby.toString());
            System.out.println("Receipt saved to coffeeReceipt.txt");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the receipt.");
            e.printStackTrace();

        }
        System.out.print(gab.toString());
    }

}







