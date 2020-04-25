/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipebook.domain;

import org.junit.Before;

/**
 *
 * @author tiitinha
 */
public class RecipeBookServiceRecipeTest {
    
    private FakeUserDao userDao;
    private FakeRecipeDao recipeDao;
    private User user1;
    private RecipeBookService recipebook;

    @Before
    public void setUp() {

        userDao = new FakeUserDao();
        recipeDao = new FakeRecipeDao();

        user1 = new User("Testi", "salasana");
        userDao.createUser(user1);

        recipebook = new RecipeBookService(recipeDao, userDao);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}