/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipebook.domain;

/**
 * Class for users in the recipebook, handles checking password etc.
 * @author tiitinha
 */
public class User implements Comparable<User> {

    private String name;
    private String password;

    /**
     * Sets the name and the password of the user.
     * @param name name of the user
     * @param password password of the user
     */
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            return false;
        }
        
        User other = (User) obj;
        
        return name.equals(other.name);
    }
    
    /**
     * Checks whether the given password matches the password of the user.
     * @param pw The password to be checked
     * @return true if the given password is correct, otherwise false.
     */
    public boolean checkPassword(String pw) {
        return password.equals(pw);
    }


    @Override
    public int compareTo(User t) {
        return this.name.hashCode() - t.name.hashCode();
    }
    
}
