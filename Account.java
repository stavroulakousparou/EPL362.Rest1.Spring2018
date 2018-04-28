package gui;

/**
 * This class saves the account and every information of that account. It has a
 * singleton instance because only one account should be created in the application. It
 * has all the get and set methods for all the fields.
 */

public class Account {

    private static Account uniqueInstance = null; // The singleton instance
    private String username = null; // The account's username
    private String password = null; // The account's password
    private String id = null; // the user's id


    /**
     * This is the method that creates the singleton instance if this is the first
     * time that one class tries to create an object of this class.
     *
     * @return the instance.
     */
    public static Account getUniqueInstance() {

        if (uniqueInstance == null)
            uniqueInstance = new Account();

        return uniqueInstance;
    }

    /*
     * This is the private constructor of this class. It exists only to defeat
     * instatiation.
     */
    private Account() {
    }

    /**
     * This is the set method for the account's username.
     *
     * @param username, the account's username.
     */
    public void setAccountUsername(String username) {
        this.username = username;
    }

    /**
     * This is the set method for the account's id.
     *
     * @param id, the account's id.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This is the set method for the account's password.
     *
     * @param password, the account's password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * It passes the account's username.
     *
     * @return the account's username.
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * It passes the account's password.
     *
     * @return the account's password.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * It passes the account's id.
     *
     * @return the account's id.
     */
    public String getId() {
        return this.id;
    }
}
