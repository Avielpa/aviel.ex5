public class User {
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private int userType;
    private String clubMember;
    private Employee employee;
    private Customer customer;
    private Cart cart;
    private double cartPrice;



    public User(String firstName, String lastName, String userName, String password, int userType,
                String clubMember, Cart cart) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.userType = userType;
        this.password = password;
        this.clubMember = clubMember;
        this.cart = cart;

    }

    public String getEmployeeType(){
        return employee.getEmployeeType();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public int getUserType() {
        return userType;
    }

    public Cart getCart() {
        return cart;
    }

    public String getClubMember(){
        boolean isVip = false;
        String vip = "";
        if (clubMember.equals("yes")){
            isVip = true;
        }
        if (isVip){
            vip = "(vip)";
        }
        return vip;
    }

    public String getFullName(){
        return getFirstName() + " " + getLastName() ;
    }

    public String toString(){
        return getFullName();
    }

    public double getCartPrice(){
        int currentCartPrice = 0;
        if (getClubMember().equals("vip")){
            for (int i = 0; i < getCart().getProducts().length; i++) {
                currentCartPrice += getCart().getProducts()[i].getProductPrice();
            }
        }
        else {
            for (int i = 0; i < getCart().getProducts().length; i++) {
                currentCartPrice += getCart().getProducts()[i].getProductPrice();
            }
        }
        return currentCartPrice;
    }

    public void setCartPrice(double cartPrice){
        this.cartPrice = cartPrice;
    }
}