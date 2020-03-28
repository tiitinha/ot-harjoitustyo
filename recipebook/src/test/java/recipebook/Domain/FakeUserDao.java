/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipebook.Domain;

import java.util.ArrayList;
import java.util.List;
import recipebook.Dao.UserDao;

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
    public User createUser(User user) {
        users.add(user);
        return user;
    }

    @Override
    public User findByUserName(String username) {
        return users.stream().filter(u -> u.getName().equals(username)).findFirst().orElse(null);
    }

    @Override
    public List<User> getAll() {
        return users;
    }
    
}
