public class Product {
    private String productName;
    private double productPrice;
    private int amount;
    private double discount;


    public Product(String productName, double productPrice, int amount, double discount) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.amount = amount;
        this.discount = discount;
    }

    public Product(Product product){
        this.productName = product.getProductName();
        this.productPrice = product.getProductPrice();
        this.amount = product.getAmount();
        this.discount = product.getDiscount();
    }

    public double getDiscount() {
        return discount;
    }

    public String getProductName() {
        return productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }


    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", amount=" + amount +
                '}';
    }
}