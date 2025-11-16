abstract class Person {

    /*
     * name        - full name of the person
     * username    - unique username for each person
     * accountType - the type of employee the person is, viz, {"Admin", "Agronomist", "Farmer"}
     * password    - password to login to account
     * phoneNum    - phoneNumber of the person
     * salary      - salary of the person
     */
    public String name;
    public String username;
    public String accountType;
    private String password;
    private long phoneNum;
    private double salary;

    public Person(String name, String username, String accountType, String password, long phoneNum, double salary) {

        this.name = name;
        this.username = username;
        this.accountType = accountType;
        this.password = password;
        this.phoneNum = phoneNum;
        this.salary = salary;
    }

    protected boolean isPasswordMatch(String enteredPassword) {
        return this.password.equals(enteredPassword);
    }

    protected long getPhoneNum() {
        return this.phoneNum;
    }

    protected double getSalary() {
        return this.salary;
    }

    protected void updatePassword(String newPassword) {
        this.password = newPassword;
    }

    protected void updatePhoneNum(long newPhoneNum) {
        this.phoneNum = newPhoneNum;
    }

    protected void updateSalary(double newSalary) {
        this.salary = newSalary;
    }
}