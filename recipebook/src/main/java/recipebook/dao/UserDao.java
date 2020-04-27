/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipebook.dao;

import java.util.List;
import recipebook.domain.User;

/**
 *
 * @author tiitinha
 */
public interface UserDao {
    
    boolean fetchUsers();
    
    boolean createUser(User user);
    
    User findByUserName(String username);
    
    List<User> getAllUsers();
    
}
