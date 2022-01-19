public class Customer extends User{

    public Customer(String firstName, String lastName, String userName, String password, int userType, String clubMember, Cart cart){
        super(firstName,lastName,userName,password,userType,clubMember,cart);

    }

    public String toString(){
        return "hello" + " " + getFullName() + getClubMember();
    }
}