import java.util.Arrays;

abstract class Person {

    /*      
     * name              - full name of the person
     * username          - unique username for each person
     * accountType       - the type of person, viz, {"Admin", "Agronomist", "Farmer"}
     * hashedPassword[]  - an array of numbers with password hashed in it
     * phoneNum          - phoneNumber of the person
     * salary            - salary of the person
     */
    public String name;
    public String username;
    public String accountType;
    private int[] hashedPassword;
    private long phoneNum;
    private double salary;
    
    public Person(String name, String username, String accountType, String password, long phoneNum, double salary) {

        this.name = name;
        this.username = username;
        this.accountType = accountType;
        this.hashedPassword = hashPassword(password);
        this.phoneNum = phoneNum;
        this.salary = salary;
    }
    
    /*
     * Simulates the hashing of a password String
     * First the password is reversed
     * Then, from 0th index of the reversed password String, the characters' (ASCII values + index_number) is hashed in password[]
     */
    private static int[] hashPassword(String password) {
        
        int[] hashedPassword = new int[100];
        Arrays.fill(hashedPassword, -1);

        String reversedPassword = "";

        //password string reversed
        for(int i = password.length()-1; 0 <= i; i--)
        reversedPassword += Character.toString(password.charAt(i));

        //(ASCII value of password.charAt(i) + i) is hashed in password[]
        for(int i = 0; i < reversedPassword.length(); i++)
        hashedPassword[i] = ((int)reversedPassword.charAt(i)) + i;

        return hashedPassword;
    }
    
    /*
     * Checks if the user entered a correct password to login
     * dehashes the password and checks it with the enteredPasssword
     * If password matches, returns true
     * Otherwise, returns false
     */
    protected boolean isPasswordMatch(String enteredPassword) {

        int i;
        int[] hashedPassword = Arrays.copyOf(this.hashedPassword, this.hashedPassword.length);

        for(i = 0; i < 100; i++) {
            
            if(hashedPassword[i] == -1)
            break;

            hashedPassword[i] -= i;
        }

        i--;

        String password = "";

        for(; 0 <= i; i--) {

            if(hashedPassword[i] == -1)
            break;

            password += Character.toString((char)(hashedPassword[i]));
        }

        return password.equals(enteredPassword);
    }

    protected long getPhoneNum() {
        return this.phoneNum;
    }

    protected double getSalary() {
        return this.salary;
    }

    protected void updatePassword(String newPassword) {
        this.hashedPassword = hashPassword(newPassword);
    }

    protected void updatePhoneNum(long newPhoneNum) {
        this.phoneNum = newPhoneNum;
    }

    protected void updateSalary(double newSalary) {
        this.salary = newSalary;
    }
}
