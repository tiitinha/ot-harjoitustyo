/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipebook.Domain;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import recipebook.Domain.User;

/**
 *
 * @author tiitinha
 */
public class UserTest {
    
    private User user;
    
    @Before
    public void setUp() {
        user = new User("matti", "123");
    }
    
    @Test
    public void equalsReturnsTrueIfSameUsername() {
        User testUser = new User("matti", "321");
        assertTrue(user.equals(testUser));
    }
    
    @Test
    public void equalsReturnsFalseIfDifferentUsername() {
        User testUser = new User("mikko", "123");
        assertFalse(user.equals(testUser));
    }
    
    @Test
    public void checkPasswordReturnsFalseIfIncorrect() {
        assertFalse(user.checkPassword("111"));
    }
    
    @Test
    public void checkPasswordReturnsTrueIfCorrect() {
        assertTrue(user.checkPassword("123"));
    }

}
