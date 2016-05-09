package Java102.Hw3;

/*
*  Name : Rabia Abismail
*  ID   : 140201209
*  Project on GitHub : I'll upload it later under this link https://git.io/vwBMQ
*/

public class Product {
    private int productID;
    private String name;
    private double price, quantity;
    private boolean isPartial;

    Product(int productID, String name, double price,
            double quantity, boolean isPartial){
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.quantity = isPartial? quantity: Math.floor(quantity);
        this.isPartial = isPartial;
    }

    public int getID(){
        return productID;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public double getPrice(){
        return price;
    }
    public void setPrice(double price){
        if(price >= 0)
            this.price = price;
        else System.err.println("price cannot be negative");
    }
    public double getQuantity(){
        return quantity;
    }
    public boolean reduceQuantity(double amount){
        if (amount<0)
            System.err.println("The amount cannot be Negative");
        else if (quantity-amount<0)
            //in case amount cannot be satisfied
            System.err.println("The amount you required cannot be fully provided");
        else if (!isPartial && (amount%1!=0))
            // (amount % 1 != 0) is for checking whole number
            System.err.println("Sorry Bruh!! the amount has to be whole number");
        else {
            quantity -= amount;
            return true;
        }
        return false;
    }
    public boolean increaseQuantity(double amount){
        if (amount<0)
            System.err.println("The amount cannot be Negative");
        else if (!isPartial && (amount % 1 !=0))
            // (amount %1 != 0) is for checking whole number
            System.err.println("Sorry Bruh!! the amount has to be a whole number");
        else {
            quantity += amount;
            return true;
        }
        return false;
    }

    // getter for isPartial
    public boolean isPartial() {
        return isPartial;
    }
}
