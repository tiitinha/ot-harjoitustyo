/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipebook.Dao;
import java.util.ArrayList;
import java.util.List;
import recipebook.Domain.User;

/**
 *
 * @author tiitinha
 */
public class DatabaseUserDao implements UserDao {
    private String database;
    private List<User> users;

    public DatabaseUserDao(String database) {
        this.database = database;
        
        users = new ArrayList<>();
        
        try {
            
        } catch (Exception e) {
            
        }
    }

    @Override
    public User createUser(User user) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User findByUserName(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
