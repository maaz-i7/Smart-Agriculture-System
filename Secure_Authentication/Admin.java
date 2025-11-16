import java.util.Scanner;
import Authentication_Exceptions.*;

public class Admin extends Person implements Authenticate {
    
    /*
     * currentUsersCount - stores the count of current number of users
     */
    public static int currentUsersCount = 0;

    public Admin(String name, String username, String accountType, String password, long phoneNum) {

        super(name, username, accountType, password, phoneNum, 100000);
    }

    /*
     * Updates the password of a user
     */
    public void updatePasswordOfUser() throws Exception {

        Scanner input = new Scanner(System.in);

        System.out.print("\nEnter username: ");
        String username = input.next();

        Person person = Authenticate.usernameExists(username);

        if(person == null)
        throw new UserDoesNotExistException();

        System.out.print("\nEnter new password: ");
        System.out.println(WeakPasswordException.conditions);
        String newPassword = input.next();
        Authenticate.checkPasswordStrength(newPassword);
        person.updatePassword(newPassword);
    }
    
    /*
     * Deletes a user from the database
     */
    public void deleteUser() throws Exception {

        Scanner input = new Scanner(System.in);

        System.out.print("\nEnter username: ");
        String username = input.next();

        Person person = Authenticate.usernameExists(username);

        if(person == null)
        throw new UserDoesNotExistException();

        System.out.print("\nEnter password: ");
        String password = input.next();
        
        if(!person.isPasswordMatch(password))
        throw new IncorrectPasswordException();

        int i = 0;

        for(; i < Admin.currentUsersCount; i++) {
            if(users[Admin.currentUsersCount].username.equals(username))
                break;
        }

        for(; i < Admin.currentUsersCount-1; i++) {

            Person tempUser = users[i];
            users[i] = users[i+1];
            users[i+1] = tempUser;
        }

        users[i] = null;
        Admin.currentUsersCount--;
    }
}
