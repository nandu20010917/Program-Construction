public class BillItem
{
    private GroceryItem Item;
    private double Quantity, NetPrice, DiscountPrice, TotDiscountPrice;

    public BillItem(GroceryItem item, double quantity) {
        this.Item = item;
        this.Quantity = quantity;
        this.NetPrice = CalculateNetPrice();
        this.DiscountPrice = CalculateDis();
    }

    public GroceryItem getItem() {
        return Item;
    }

    public void setItem(GroceryItem item) {
        Item = item;
    }

    public double getQuantity() {
        return Quantity;
    }

    public void setQuantity(double quantity) {
        Quantity = quantity;
    }

    public double getNetPrice() {
        return NetPrice;
    }

    // Calculate the discount price of the item based on its price and discount percentage
    public double CalculateDis(){
        DiscountPrice = (getItem().getPrice() * getItem().getDiscount()/100);
        return DiscountPrice;
    }

    // Calculate the total discount price by multiplying the discount price with the quantity
    public double CalculateTotDis(){
        TotDiscountPrice = (getItem().getPrice() * getItem().getDiscount()/100)*Quantity;
        return TotDiscountPrice;
    }
    // Calculate the net price after deducting the discount from the item price, multiplied by the quantity
    public double CalculateNetPrice() {
        NetPrice = (getItem().getPrice() - this.CalculateDis())*Quantity;
        return NetPrice;

    }
}
