package Smart_Agriculture_System.Users;

public class Farmer extends Person {
    
    /*
     * Overloaded Constructors
     */
    public Farmer(String name, String username, String password, long phoneNum) {

        super(name, username, "Farmer", password, phoneNum, 50000);
    }

    public Farmer(String name, String username, String password, long phoneNum, double salary) {

        super(name, username, "Farmer", password, phoneNum, salary);
    }
}