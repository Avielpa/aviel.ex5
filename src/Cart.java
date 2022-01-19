public class Cart {
    private Product[] products;
    private Product product;

    public Cart(){
        this.products = new Product[0];
    }

    public Product[] getProducts() {
        return products;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

}
