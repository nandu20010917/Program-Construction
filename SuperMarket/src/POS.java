import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class POS
{
    public HashMap<String, GroceryItem> database;

    // Constructor to initialize the POS system with the database
    public POS (HashMap<String, GroceryItem> database){
        this.database = database;
    }
    public GroceryItem getItemDetails() {
        String item_code;
        GroceryItem item = null;
        boolean validity = true;
        while(validity) {
            try {
                System.out.println("Enter item code: ");
                InputStreamReader r = new InputStreamReader(System.in);
                BufferedReader br = new BufferedReader(r);
                item_code = br.readLine();
                // Check if the item code exists in the database
                if (database.containsKey(item_code)){
                    item = database.get(item_code);
                }
                // Throw ItemCodeNotFound exception if item code not found
                else
                    throw new ItemCodeNotFound("Item didn't found: " + item_code);
                validity = false;   // Exit the loop if item code is valid

                // Fetch item details from the database
               // br.close();
               // r.close();
            } catch (ItemCodeNotFound | IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return item;
    }
}