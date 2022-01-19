public class Employee extends Customer{
    private String employeeType;


    public Employee(String firstName, String lastName, String userName, String password, int userType,
                    String employeeType, String clubMember, Cart cart){
        super(firstName,lastName,userName,password,userType,clubMember,cart);
        this.employeeType = employeeType;
    }
    public String getEmployeeType() {
        return employeeType;
    }

    public String toString(){
        return "hello" + " " + getFullName() + getClubMember() + "(" + getEmployeeType() + ")";
    }
}