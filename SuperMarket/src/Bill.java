import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;

public class Bill
{
    private String Branch;
    private Customer Customer;
    private Cashier Cashier;
    private List<BillItem> ItemList;
    private double TotalDiscount, TotalPrice;
    private Date DateTime = new Date();

    public Bill(String branch, Customer customer, Cashier cashier, List<BillItem> itemList) {
        this.Branch = branch;
        this.Customer = customer;
        this.Cashier = cashier;
        this.ItemList = itemList;
        this.TotalDiscount = CalculateDis();
        this.TotalPrice = CalculateTot();
    }

    public String getBranch() {
        return Branch;
    }

    public void setBranch(String branch) {
        Branch = branch;
    }

    public Customer getCustomer() {
        return Customer;
    }

    public void setCustomer(Customer customer) {
        Customer = customer;
    }

    public Cashier getCashier() {
        return Cashier;
    }

    public void setCashier(Cashier cashier) {
        Cashier = cashier;
    }

    public List<BillItem> getItemList() {
        return ItemList;
    }

    public void setItemList(ArrayList<BillItem> itemList) {
        ItemList = itemList;
    }

    public double getTotalDiscount() {
        return TotalDiscount;
    }

    public void setTotalDiscount(double totalDiscount) {
        TotalDiscount = totalDiscount;
    }

    public double getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        TotalPrice = totalPrice;
    }

    // Calculate the total price of the bill by summing up the net prices of all bill items
    public double CalculateTot(){
        double TotPrice = 0.00;
        for(BillItem item : ItemList){
            TotPrice = TotPrice + item.getNetPrice();
        }
        return TotPrice;
    }

    // Calculate the total discount of the bill by summing up the total discount prices of all bill items
    public double CalculateDis(){
        double TotDis = 0.00;
        for(BillItem item : ItemList){
            TotDis = TotDis + item.CalculateTotDis();
        }
        return TotDis;
    }

    // Print the bill details
    public void printBill()
    {
        System.out.println("------------------New Food City------------------\n");
        System.out.println("Time And Data: " + DateTime);
        System.out.println("Branch: " + Branch);
        System.out.println("Customer Name: " + Customer.getName());
        System.out.println("Cashier Name: " + Cashier.getName() + "\n");
        System.out.println("Items: ");
        for(BillItem item : ItemList) {
            System.out.println("\t" + item.getItem().getItemCode() + "\t\t" + item.getItem().getItemName() + "\t\t" + item.getItem().getPrice() + "\t\t" + item.getQuantity() + "\t\t" + String.format("%4.3f",item.CalculateTotDis()) + "\t\t" + item.getNetPrice());

        }
        System.out.println();
        System.out.println("Total Discount: " + TotalDiscount);
        System.out.println("Total Price: " + TotalPrice + "\n");
        System.out.println("Thank You For Visiting!");
    }

    // Serialize the bill object and save it to a file
    public void serialize(String filename) {
        try (FileOutputStream fos = new FileOutputStream(filename);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(this);
            System.out.println("Bill serialized to " + filename);
        } catch (Exception e) {
            System.out.println("Error serializing bill to " + filename);
            e.printStackTrace();
        }
    }
}



