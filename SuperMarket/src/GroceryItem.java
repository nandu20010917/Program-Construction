import java.util.ArrayList;

public class GroceryItem {
    private String ItemCode, ItemName, DateOfManufacturing, DateOfExpiry, ManufacturerName;
    private double Price, WeightSize, Discount;

    // Constructor to initialize the GroceryItem with all the attributes
    public GroceryItem(String itemCode, String itemName, String dateOfManufacturing, String dateOfExpiry, String manufacturerName, double price, double weightSize, double discount) {
        this.ItemCode = itemCode;
        this.ItemName = itemName;
        this.DateOfManufacturing = dateOfManufacturing;
        this.DateOfExpiry = dateOfExpiry;
        this.ManufacturerName = manufacturerName;
        this.Price = price;
        this.WeightSize = weightSize;
        this.Discount = discount;
    }

    // Setter methods for each attribute
    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public void setDateOfManufacturing(String dateOfManufacturing) {
        DateOfManufacturing = dateOfManufacturing;
    }

    public void setDateOfExpiry(String dateOfExpiry) {
        DateOfExpiry = dateOfExpiry;
    }

    public void setManufacturerName(String manufacturerName) {
        ManufacturerName = manufacturerName;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public void setWeightSize(double weightSize) {
        WeightSize = weightSize;
    }

    public void setDiscount(double discount) {
        Discount = discount;
    }

    // Getter methods for each attribute
    public String getItemCode() {
        return ItemCode;
    }

    public String getItemName() {
        return ItemName;
    }

    public String getDateOfManufacturing() {
        return DateOfManufacturing;
    }

    public String getDateOfExpiry() {
        return DateOfExpiry;
    }

    public String getManufacturerName() {
        return ManufacturerName;
    }

    public double getPrice() {
        return Price;
    }

    public double getWeightSize() {
        return WeightSize;
    }

    public double getDiscount() {
        return Discount;
    }

    // Static method to get the details of an item by its code
    public static String getItemDetails(String code, ArrayList<GroceryItem> items) {
        for (GroceryItem item : items) {
            if (item.ItemCode.equals(code)) {
                String attributes = "Code: " + code + "\n"
                        + "Price: " + item.getPrice() + "\n"
                        + "Weight: " + item.WeightSize + "\n"
                        + "Manufacturing Date: " + item.DateOfManufacturing + "\n"
                        + "Expiry Date: " + item.DateOfExpiry + "\n"
                        + "Manufacturer Name: " + item.ManufacturerName + "\n"
                        + "Discount: " + item.Discount + "\n";
                return attributes;
            }
        }
        return " ";
    }
}
