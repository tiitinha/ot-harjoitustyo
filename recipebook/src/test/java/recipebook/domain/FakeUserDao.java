/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipebook.domain;

import recipebook.domain.User;
import java.util.ArrayList;
import java.util.List;
import recipebook.dao.UserDao;

/**
 *
 * @author tiitinha
 */
public class FakeUserDao implements UserDao {
    
    List<User> users;

    public FakeUserDao() {
        users = new ArrayList<>();
    }


    
    @Override
    public boolean createUser(User user) {
        users.add(user);
        return true;
    }

    @Override
    public User findByUserName(String username) {
        return users.stream().filter(u -> u.getName().equals(username)).findFirst().orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public boolean fetchUsers() {
        return true;
    }
    
}
