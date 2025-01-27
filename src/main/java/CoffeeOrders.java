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
        double vat = subtotal * vatRate;
        double grandTotal = subtotal + vat;

        // display the receipt and the results
        System.out.println("\n--- Coffee Order Receipt ---");
        for (int i = 0; i < 4; i++) {
        }
        System.out.printf("Subtotal: %.2f PHP\n", subtotal);
        System.out.printf("VAT (12%%): %.2f PHP\n", vat);
        System.out.printf("Grand Total: %.2f PHP\n", grandTotal);
    }
}







