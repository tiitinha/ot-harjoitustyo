/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipebook.Domain;

/**
 *
 * @author tiitinha
 */
public class User implements Comparable<User> {

    private String name;
    private String password;

    /**
     * 
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
    
    public boolean checkPassword(String pw) {
        return password.equals(pw);
    }


    @Override
    public int compareTo(User t) {
        return this.name.hashCode() - t.name.hashCode();
    }
    
}
