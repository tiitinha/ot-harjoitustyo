/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipebook.dao;

import recipebook.dao.UserDao;
import recipebook.dao.DatabaseUserDao;
import recipebook.domain.DatabaseService;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import recipebook.domain.User;

/**
 *
 * @author tiitinha
 */
public class UserDaoTest {
    
    private DatabaseService db;
    private String path;
    private UserDao dao;

    
    @Before
    public void setUp() {
        db = new DatabaseService();
        path = "./src/test/resources/database";
        db.createDatabase(path);
        dao = new DatabaseUserDao(path);
    }
    
    @Test
    public void createUserReturnsTrueIfCreationSuccessful() {
        User user = new User("testi", "123");
        assertTrue(dao.createUser(user));
    }
    
    @Test
    public void getAllUsersReturnsAListWithCorrectSize() {
        User user1 = new User("testi", "123");
        User user2 = new User("testi2", "1234");
        dao.createUser(user1);
        dao.createUser(user2);
        
        List<User> users = dao.getAllUsers();
        
        assertTrue(users.size() == 2);
    }
    
    @Test
    public void findByUsernameReturnsUserWithCorrectName() throws Exception {
        User user = new User("testiuser", "123");
        dao.createUser(user);
        
        User returnUser = dao.findByUserName("testiuser");
        
        assertEquals("testiuser", returnUser.getName());
    }

}
