import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        // Create customer objects
        Customer customer1 = new Customer("John");
        Customer customer2 = new Customer("David");

        // Create grocery item objects
        GroceryItem groceryItem1 = new GroceryItem("001","Chocolate Biscuit ", "01/02/2023", "01/02/2024", "Malibun", 150.00, 100.00, 0.00);
        GroceryItem groceryItem2 = new GroceryItem("002","Cream Cracker 400g", "01/01/2023", "01/04/2024", "Malibun", 500.00, 400.00, 0.00);
        GroceryItem groceryItem3 = new GroceryItem("003","Orange Fruits     ", "16/02/2023", "01/03/2023", "FreshFruitsPVT", 150.00, 50.00, 10.00);
        GroceryItem groceryItem4 = new GroceryItem("004","Fresh Milk 400ml  ", "16/02/2023", "01/05/2023", "Kothmale", 450.00, 400.00, 20.00);

        // Create cashier objects
        Cashier cashier1 = new Cashier("Spongebob");
        Cashier cashier2 = new Cashier("Frodo");

        // Create a HashMap to store grocery items by code
        HashMap<String, GroceryItem> groceryItemList = new HashMap<>();
        groceryItemList.put("001",groceryItem1);
        groceryItemList.put("002",groceryItem2);
        groceryItemList.put("003",groceryItem3);
        groceryItemList.put("004",groceryItem4);

        // Create an empty list to store bill items
        List<BillItem> itemlist = new ArrayList<>();

        // Create an instance of the POS class and pass the grocery item list
        POS pos = new POS(groceryItemList);

        // Create a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Start the billing process
        while (true) {
            // Get item details from the POS system
            GroceryItem item = pos.getItemDetails();
            System.out.println("Enter Quantity: ");
            int q = scanner.nextInt();
            scanner.nextLine();

            // Create a bill item and add it to the list
            BillItem billItem = new BillItem(item, q);
            itemlist.add(billItem);

            System.out.println("Continue Billing Press 1");
            System.out.println("End Billing Press 2");
            int x = scanner.nextInt();

            // If user chooses to end billing, generate and print the bill
            if (x == 2) {
                Bill bill = new Bill("Moratuwa", customer1, cashier2, itemlist);
                bill.printBill();
                break;
            }
        }
    }
}