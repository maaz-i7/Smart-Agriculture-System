package Smart_Agriculture_System.Users;
import java.io.Console;
import Smart_Agriculture_System.Secure_Authentication.Authentication;
import Smart_Agriculture_System.Secure_Authentication.Authentication_Exceptions.*;

public class Admin extends Person implements Authentication {

    /*
     * currentUsersCount - stores the count of current number of users
     */
    public static int currentUsersCount = 0;

    /*
     * Overloaded Constructors
     */
    public Admin(String name, String username, String password, long phoneNum) {

        super(name, username, "Admin", password, phoneNum, 100000);
    }

    public Admin(String name, String username, String password, long phoneNum, double salary) {

        super(name, username, "Admin", password, phoneNum, salary);
    }

    /*
     * Updates the password of a user.
     * First asks the username of the user
     */
    public void updatePasswordOfUser() throws Exception {

        Console consoleInput = System.console();

        Person person = Authentication.signIn();

        System.out.print("\nCreate a new password: ");
        System.out.println(WeakPasswordException.conditions);
        char[] passChars = consoleInput.readPassword("\nEnter new password: ");
        String newPassword = String.valueOf(passChars);
        Authentication.checkPasswordStrength(newPassword);
        person.updatePassword(newPassword);
    }
    
    /*
     * Deletes a user from the database
     */
    public void deleteUser() throws Exception {

        System.out.println("\nSign in with the user to be deleted:");

        Person person = Authentication.signIn();

        int i = 0;

        for(; i < currentUsersCount; i++) {
            if(users[i].username.equals(person.username))
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