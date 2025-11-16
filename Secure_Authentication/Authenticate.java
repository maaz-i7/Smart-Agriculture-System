import java.util.*;
import Authentication_Exceptions.*;

public class Authenticate {

    /*
     * users             - stores the list of all users, initialized to a size of 100
     * usersTypes        - stores the three types of users, viz "Admin", "Agronomist", "Farmer"
     * currentUsersCount - stores the count of current number of users
     */
    private static Person[] users = new Person[100];
    private static String[] usersTypes = {"Admin", "Agronomist", "Farmer"};
    private static int currentUsersCount = 0;

    /*
     * checks if the username already exists in the database
     * If true, returns the user
     * else, returns null
     */
    private static Person usernameExists(String username) {
 
        if(username == null)
        return null;
        
        for(int i = 0; i < currentUsersCount; i++) {
            
            if(users[i].username.equals(username))
                return users[i];
        }

        return null;           
    }

    /*
     * Checks the validity of username as per rules:
     * - Username must contain only letters a-z or A-Z
     * - Username must contain only digits 0-9
     * - Username must contain only symbols _ or .
     * - Username must not start with .
     * 
     * If username is valid, returns
     * Otherwise, throws InvalidUsernameException()
     */
    private static void checkValidUsername(String username) throws Exception {
 
        if (username == null || username.charAt(0) == '.')
            throw new InvalidUsernameException();

        for (int i = 0; i < username.length(); i++) {

            if (Character.isLetterOrDigit(username.charAt(i)))
                continue;

            if (username.charAt(i) == '.' || username.charAt(i) == '_')
                continue;

            throw new InvalidUsernameException();
        }    
    }

    /*
     * Checks if name of user entered is valid:
     * 
     * If name has only letters a-z and A-Z are present, returns
     * Otherwise throws InvalidNameException()
     */
    private static void checkValidName(String name) throws Exception {

        if(name == null)
        throw new InvalidNameException();

        for(int i = 0; i < name.length(); i++) {

            if(Character.isLetter(name.charAt(i)))
            continue;

            throw new InvalidNameException();
        }
    }

    /*
     * Checks password strength based on rules:
     * - Password must be atleast 8 characters in length
     * - Password must contain atleast one letter a-z
     * - Password must contain atleast one letter from A-Z 
     * - Password must contain atleast one number from 0-9 
     * - Password must contain ateast one special character
     * 
     * If above all conditions are met, password is strong and returns
     * Otherwise, throws WeakPasswordException()
     */
    private static void checkPasswordStrength(String password) throws Exception {

        if(password == null || password.length() < 8)
        throw new WeakPasswordException();

        boolean lowerCasePresent = false;
        boolean upperCasePresent = false;
        boolean numberPresent = false;
        boolean specialCharPresent = false;

        for(int i = 0; i < password.length(); i++) {

            if(Character.isLowerCase(password.charAt(i)))
            lowerCasePresent = true;

            else if(Character.isUpperCase(password.charAt(i)))
            upperCasePresent = true;

            else if(Character.isDigit(password.charAt(i)))
            numberPresent = true;

            else
            specialCharPresent = true;
        }

        if(!lowerCasePresent || !upperCasePresent || !numberPresent || !specialCharPresent)
        throw new WeakPasswordException();
    }
    
    /*
     * Creates a new user in database
     */
    public static void signUp() throws Exception {

        Scanner input = new Scanner(System.in);

        System.out.println("\nEnter the type of user to register");
        System.out.println("\t0 - " + usersTypes[0]);
        System.out.println("\t1 - " + usersTypes[1]);
        System.out.println("\t2 - " + usersTypes[2]);
        int employeeType = input.nextInt();

        if(employeeType > 2)
        throw new InvalidUserTypeException();

        System.out.print("\nEnter your full name: ");
        String name = input.next();
        checkValidName(name);

        System.out.print("\nCreate a username: ");
        System.out.println(InvalidUsernameException.conditions);
        String username = input.next();
        checkValidUsername(username);

        while (usernameExists(username) != null) {
            
            System.out.println("\nUsername already exists!");
            username = input.next();
        }

        System.out.print("\nCreate a strong password");
        System.out.println(WeakPasswordException.conditions);
        String password = input.next();
        checkPasswordStrength(password);

        System.out.print("\nEnter your phone number: ");
        long phoneNum = input.nextLong();

        Person person;

        if(employeeType == 0)
        person = new Admin(name, username, usersTypes[0], password, phoneNum);

        else if(employeeType == 1)
        person = new Agronomist(name, username, usersTypes[1], password, phoneNum);

        else 
        person = new Farmer(name, username, usersTypes[2], password, phoneNum);

        users[currentUsersCount++] = person;
    }

    /*
     * Checks and returns an already existing user
     */
    public static Person signIn() throws Exception {

        Scanner input = new Scanner(System.in);

        System.out.print("\nEnter username: ");
        String username = input.next();

        Person person = usernameExists(username);

        if(person == null)
        throw new UserDoesNotExistException();

        System.out.print("\nEnter password: ");
        String password = input.next();
        
        if(!person.isPasswordMatch(password))
        throw new IncorrectPasswordException();

        return person;
    }
    
    /*
     * Updates the password of a user
     */
    public static void updatePassword() throws Exception {

        Scanner input = new Scanner(System.in);

        System.out.print("\nEnter username: ");
        String username = input.next();

        Person person = usernameExists(username);

        if(person == null)
        throw new UserDoesNotExistException();

        System.out.print("\nEnter new password: ");
        System.out.println(WeakPasswordException.conditions);
        String newPassword = input.next();
        checkPasswordStrength(newPassword);
        person.updatePassword(newPassword);
    }
    
    /*
     * Deletes a user from the database
     */
    public static void deleteUser() throws Exception {

        Scanner input = new Scanner(System.in);

        System.out.print("\nEnter username: ");
        String username = input.next();

        Person person = usernameExists(username);

        if(person == null)
        throw new UserDoesNotExistException();

        System.out.print("\nEnter password: ");
        String password = input.next();
        
        if(!person.isPasswordMatch(password))
        throw new IncorrectPasswordException();

        int i = 0;

        for(; i < currentUsersCount; i++) {
            if(users[currentUsersCount].username.equals(username))
                break;
        }

        for(; i < currentUsersCount-1; i++) {

            Person tempUser = users[i];
            users[i] = users[i+1];
            users[i+1] = tempUser;
        }

        users[i] = null;
        currentUsersCount--;
    }
}
