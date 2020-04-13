/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipebook.Dao;

import recipebook.Domain.DatabaseService;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import recipebook.Domain.User;

/**
 *
 * @author tiitinha
 */
public class UserDaoTest {
    
    private DatabaseService db;
    private String path;

    
    @Before
    public void setUp() {
        db = new DatabaseService();
        path = "./src/test/resources/database";
        db.createDatabase(path);
        UserDao dao = new DatabaseUserDao(path);
    }
    
    @Test
    public void createUserReturnsCorrectUser() throws Exception {
        User user = new User("testi", "123");
        UserDao dao = new DatabaseUserDao(path);
        var returnUser = dao.createUser(user);
        assertEquals("testi", returnUser.getName());
    }
    
    @Test
    public void getAllUsersReturnsAListWithCorrectSize() throws Exception {
        User user1 = new User("testi", "123");
        User user2 = new User("testi2", "1234");
        UserDao dao = new DatabaseUserDao(path);
        dao.createUser(user1);
        dao.createUser(user2);
        
        List<User> users = dao.getAllUsers();
        
        assertTrue(users.size() == 2);
    }
    
    @Test
    public void findByUsernameReturnsUserWithCorrectName() throws Exception {
        User user = new User("testiuser", "123");
        UserDao dao = new DatabaseUserDao(path);
        dao.createUser(user);
        
        User returnUser = dao.findByUserName("testiuser");
        
        assertEquals("testiuser", returnUser.getName());
    }

}
