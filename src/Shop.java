import java.util.Arrays;
import java.util.Scanner;

public class Shop {
    public static final int CREAT_ACCOUNT = 1;
    public static final int LOGIN = 2;
    public static final int EXIT = 3;
    private User[] users;
    Product[] products;


    public Shop() {
        this.users = new User[0];
        this.products = new Product[]{new Product("milk", 10,0,0.85),
                new Product("eggs", 20,0,0.85),
                new Product("bread", 7,0,0.85)};
    }


    public String getProduct(int choice){
        return products[choice].getProductName();
    }

    public Object[] getUsers() {
        return users;
    }

    public Product getProducts(int index) {
        return products[index];
    }

    public void openingScreen() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        do {
            System.out.println("Hello Choose your action by type number: "
                    + "\n" + "1 - creat account"
                    + "\n" + "2 - login"
                    + "\n" + "3 - exit");
            choice = scanner.nextInt();
        } while (choice != 1 && choice != 2 && choice != 3);
        openingScreenAct(choice);
    }

    public void openingScreenAct(int choice) {
        switch (choice) {
            case CREAT_ACCOUNT:
                creatAccount();
                openingScreen();
                break;
            case LOGIN:
                int type = 0;
                User user = login();
                if (user != null) {
                    System.out.println(user);
                    type = user.getUserType();
                } else {
                    System.out.println("Wrong details!");
                    openingScreen();
                }
                afterLogin(type,user);
                break;
            case EXIT:
                break;
        }
    }

    public void creatAccount() {
        Cart cart = new Cart();
        Scanner scanner = new Scanner(System.in);
        String firstName,lastName,userName,password;
        int userType = userType();
        do {
            System.out.println("Enter your first name");
            firstName = scanner.nextLine();
        } while (fineSyntax(firstName));
        do {
            System.out.println("Enter your last name: ");
            lastName = scanner.nextLine();
        } while (fineSyntax(lastName));
        do {
            System.out.println("Enter wanted username: ");
            userName = scanner.nextLine();
        } while (userNameExists(userName) || userName.length() == 0);
        do {
            System.out.println("Enter a password");
            password = scanner.nextLine();
        } while (password.length() <= 5);
        String isClubMember = "";
        if (userType == 1){
            isClubMember = isClubMember();
            this.users = (User[]) addToArray(users,new Customer(firstName,lastName,userName,password,
                    userType,isClubMember,cart));
        }
        else {
            String employeeType = employeeType();
            isClubMember = isClubMember();
            this.users = (User[]) addToArray(users,new Employee(firstName,lastName,userName,password,
                    userType,employeeType,isClubMember,cart));
        }
    }

    public Object[] addToArray(Object[] oldArray, Object toAdd) {
        Object[] newArray = new Object[oldArray.length + 1];
        for (int i = 0; i < oldArray.length; i++) {
            newArray[i] = oldArray[i];
        }
        newArray[oldArray.length] = toAdd;
        newArray = Arrays.asList(newArray).toArray(oldArray);
        return newArray;
    }

    public boolean fineSyntax(String s1) {
        boolean fineSyntax = false;
        if (s1.length() == 0) {
            fineSyntax = true;
        } else {
            for (int i = 0; i < s1.length(); i++) {
                if (!Character.isAlphabetic(s1.charAt(i))) {
                    fineSyntax = true;
                    break;
                }
            }
        }
        return fineSyntax;
    }

    public boolean userNameExists(String userName) {
        boolean userNameExists = false;
        for (int i = 0; i < users.length; i++) {
            if (users[i].getUserName().equals(userName)) {
                userNameExists = true;
            }

        }
        return userNameExists;
    }

    public static int userType() {
        Scanner scanner = new Scanner(System.in);
        int userType = 0;
        do {
            System.out.println("Enter wanted user type: "
                    + "\n" + "1 - Customer user"
                    + "\n" + "2 - Employee user");
            userType = scanner.nextInt();
        } while (userType != 1 && userType != 2);
        return userType;
    }

    public String userTypeAct(int userType) {
        Scanner scanner = new Scanner(System.in);
        String userTypeAct = "";
        int choice = 0;
        if (userType == 1) {
            userTypeAct = isClubMember();
        } else {
            userTypeAct = employeeType();
        }
        return userTypeAct;
    }

    public static String employeeType() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        String employeeType = "";
        do {
            System.out.println("Enter your employee type :"
                    + "\n" + "1 - regular"
                    + "\n" + "2 - manager"
                    + "\n" + "3 - manager team");
            choice = scanner.nextInt();
        } while (choice != 1 && choice != 2 && choice != 3);
        switch (choice) {
            case 1 -> employeeType = "regular";
            case 2 -> employeeType = "manager";
            case 3 -> employeeType = "manager team";
        }
        return employeeType;
    }


    public String isClubMember() {
        Scanner scanner = new Scanner(System.in);
        String isClubMember = "";
        int choice;
        do {
            System.out.println("Are you a club member?"
                    + "\n" + "1 - yes"
                    + "\n" + "2 - no");
            choice = scanner.nextInt();
        } while (choice != 1 && choice != 2);
        if (choice == 1) {
            isClubMember = "yes";
        } else {
            isClubMember = "no";
        }
        return isClubMember;
    }

    public User login() {
        Scanner scanner = new Scanner(System.in);
        boolean loginSuccessfully = false;
        String userName;
        String password;
        int userType;
        System.out.println("Enter username: ");
        userName = scanner.nextLine();
        System.out.println("Enter password: ");
        password = scanner.nextLine();
        return detailsCheck(userName, password);
    }

    public User detailsCheck(String userName, String password) {
        User user = null;
        for (int i = 0; i < users.length; i++) {
            if (users[i].getUserName().equals(userName) && users[i].getPassword().equals(password)){
                user = users[i];
                break;
            }
        }

        return user;
    }

    public void buying(User user) {
        Scanner scanner = new Scanner(System.in);
        int amount = 0;
        int choice = 0;
        do {
            System.out.println("Choose wanted product by type a number" + "\n" + "For exit press -1"
                    + "\n" + toString());
            choice = scanner.nextInt();
        } while (choice != -1 && !productExists(choice));
        if (choice == -1) {
            System.out.println("price to pay : " + user.getCartPrice());
            openingScreen();
        } else {
            do {
                System.out.println("type wanted amount: ");
                amount = scanner.nextInt();
            } while (amount < 0);
            buyingAct(choice, user, amount);
            System.out.println(Arrays.toString(user.getCart().getProducts()));
            buying(user);
        }
    }

    public void buyingAct(int choice, User user, int amount){
        int length = user.getCart().getProducts().length;
        if (user.getCart().getProducts() == null || !(productInCart(user,length,choice))){
           addNewProduct(user,length,amount,choice);
        }
        else{
            productUpdate(user,length,amount,choice);
            }
    }

    public void addNewProduct(User user,int length,int amount,int choice){
        user.getCart().setProducts((Product[]) addToArray(user.getCart().getProducts(),new Product(products[choice - 1])));
        if (user.getClubMember().equals("(vip)")){
            user.getCart().getProducts()[length].setProductPrice(products[choice - 1].getProductPrice()*amount*
                    products[choice - 1].getDiscount());
        }
        else {
            user.getCart().getProducts()[length].setProductPrice(products[choice - 1].getProductPrice()*amount);
        }
        user.getCart().getProducts()[length].setAmount(amount);
        System.out.println("your current cart price: " +
                (user.getCart().getProducts()[length].getProductPrice() + user.getCartPrice()));
    }

    public void productUpdate(User user, int length, int amount, int choice) {
        for (int i = 0; i < length; i++) {
            if (getProduct(choice - 1).equals(user.getCart().getProducts()[i].getProductName())) {
                user.getCart().getProducts()[i].setAmount((user.getCart().getProducts()[i].getAmount() + amount));
                if (user.getClubMember().equals("(vip)")) {
                    user.getCart().getProducts()[i].setProductPrice(user.getCart().getProducts()[i].getProductPrice() +
                            (products[choice - 1].getProductPrice()*products[choice - 1].getDiscount()*amount));
                    break;

                } else {
                    user.getCart().getProducts()[i].setProductPrice(user.getCart().getProducts()[i].getProductPrice() +
                            (products[i].getProductPrice() * amount));
                    System.out.println("your current cart price: " + user.getCartPrice());
                    break;
                }
            }
        }
    }

    public boolean productInCart(User user,int length,int choice){
        boolean productInCart = false;
        for (int i = 0; i < length; i++) {
            if (user.getCart().getProducts()[i].getProductName().equals(products[choice - 1].getProductName())) {
                productInCart = true;
                break;
            }
        }
        return productInCart;
    }

    public boolean productExists(int choice){
        boolean productExists = true;
        if (choice > products.length){
            productExists = false;
        }
        return productExists;
    }



    public void afterLogin(int userType, User user){
        if (userType == 1){
            buying(users[users.length - 1]);
        }
        else {
            userIsEmployee(user);
        }
    }

    public void userIsEmployee(User user){
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        do {
            System.out.println("choose an act by type a number: " +
                    "\n" + "1 - print all customers" +
                    "\n" + "2 - print only vip customers" +
                    "\n" + "3 - print the highest cart's value customer" +
                    "\n" + "4 - print customer who made on purchase at least" +
                    "\n" + "5 - add a new product" +
                    "\n" + "6 - to shop" +
                    "\n" + "7 - exit");
            choice = scanner.nextInt();
        }while (choice < 0 || choice > 7);
        userIsEmployeeAct(choice,user);
    }

    public void userIsEmployeeAct(int choice,User user){
        switch (choice){
            case 1:
                printAllCustomers();
                userIsEmployee(user);
                break;
            case 2:
                printVip();
                userIsEmployee(user);
                break;
            case 3:
                customerMadePurchase();
                userIsEmployee(user);
                break;
            case 4:
                highestCartValue();
                userIsEmployee(user);
                break;
            case 5:
                addNewProductToList();
                break;
            case 6:
                employeeDiscount(user);
                break;
            case 7:
                openingScreen();
                break;
        }
    }

    public boolean cartPrice(User user){
        boolean madePurchase = false;
        for (int i = 0; i < user.getCart().getProducts().length; i++) {
            if (user.getCartPrice() > 0){
                madePurchase = true;
                break;
            }
        }
        return madePurchase;
    }

    public void printAllCustomers(){
        for (int i = 0; i < this.users.length; i++) {
            System.out.println((i+1) + "." + users[i].getFullName() + "\n");
        }
    }

    public void printVip(){
        for (int i = 0; i < this.users.length; i++) {
            if (users[i].getClubMember().equals("(vip)")){
                System.out.println((i+1) + "." + users[i].getFullName() + "\n");
            }
        }
    }

    public void customerMadePurchase(){
        for (int i = 0; i < users.length; i++) {
            if (cartPrice(users[i])){
                System.out.println(users[i].getFullName());
            }
        }
    }

    public void highestCartValue(){
        double highestCartValue = 0;
        for (int i = 0; i < users.length; i++) {
            if (users[i].getCartPrice() > highestCartValue){
                highestCartValue = users[i].getCartPrice();
            }
        }
        System.out.println(highestCartValue);
    }

    public void addNewProductToList(){
        Scanner scanner = new Scanner(System.in);
        String productName = "";
        double productPrice = 0.0;
        double vipDiscount = 0.0;
        System.out.println("enter product Name :");
        productName = scanner.nextLine();
        System.out.println("enter product price :");
        productPrice = scanner.nextDouble();
        System.out.println("enter vip discount :");
        vipDiscount = scanner.nextDouble();
        this.products = (Product[]) addToArray(products,new Product(productName,productPrice,0,vipDiscount));
        System.out.println(Arrays.toString(products));
    }

    public void employeeDiscount(User user){
        buying(user);
        if (user.getEmployeeType().equals("regular")){
            user.setCartPrice(user.getCartPrice() * (0.9));
        }
        else if (user.getEmployeeType().equals("manager")){
            user.setCartPrice(user.getCartPrice() * 0.8);
        }
        else {
            user.setCartPrice(user.getCartPrice() * 0.7);
        }
    }



    @Override
    public String toString() {
        String toString = "";
        for (int i = 0; i < products.length; i++) {
            toString +=
                    (i+1) + "." + "product name:" + " " +products[i].getProductName() + "," + " " +
                            "price:" + " " + products[i].getProductPrice() + "\n";
        }
        return toString;
    }
}