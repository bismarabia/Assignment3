package Java102.Hw3;

/*
*  Name : Rabia Abismail
*  ID   : 140201209
*  Project on GitHub : I'll upload it later under this link https://git.io/vwBMQ
*/

public class Store {
    private String name;
    private Product[] products = new Product[100];

    public Store(String name, Product[] products){
        this.name = name;
        this.products = products;
    }

    public String getName(){
        return name;
    }
    public void productList(){
        System.out.println("ID\t\tName\t\tQty\t\tPrice");
        for (Product product : products)
            System.out.println(product.getID() + "\t\t" + product.getName()
                    + "\t\t" + product.getQuantity() + "\t\t" + product.getPrice());
    }
    public double sellProduct(int productID, double amount){
        //looking for the product
        for (Product product : products)
            if (product.getID() == productID)
                if (product.reduceQuantity(amount)) {
                    System.out.println(product.getName() + ": " + product.getQuantity() + " sold at "
                            + product.getPrice());
                    return product.getPrice() * product.getQuantity();
                }

        // if the product is not found or negative or cannot be served partially
        return -1;
    }

    public boolean addQuantity(int productID, double amount){
        //looking for the product
        for (Product product : products)
            if (product.getID() == productID)
                if (product.increaseQuantity(amount)) {
                    System.out.println(product.getName() + ": " + amount + " added");
                    return true;
                }
        // if the product is not found or negative or cannot be served partially
        return false;
    }
    public void discount(int productID, double percentage){
        if (percentage<0 || percentage>1)
            // if the percentage is not valid
            return;

        for (Product product : products) {
            if (product.getID() == productID) {
                product.setPrice(product.getPrice() * (1 - percentage));
                System.out.println(product.getName() + "'s new price: " + product.getPrice());
                return;
            }
        }
        // if the product is not found
        System.err.println("Item not found");
    }

}
