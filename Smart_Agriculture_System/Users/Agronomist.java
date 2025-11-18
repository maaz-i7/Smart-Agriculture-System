package Smart_Agriculture_System.Users;

public class Agronomist extends Person {
    
    /*
     * Overloaded Constructors
     */
    public Agronomist(String name, String username, String password, long phoneNum) {

        super(name, username, "Agronomist", password, phoneNum, 80000);
    }

    public Agronomist(String name, String username, String password, long phoneNum, double salary) {

        super(name, username, "Agronomist", password, phoneNum, salary);
    }
}