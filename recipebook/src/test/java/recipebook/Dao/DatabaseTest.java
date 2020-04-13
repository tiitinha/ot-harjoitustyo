/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipebook.Dao;

import recipebook.Domain.DatabaseService;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tiitinha
 */
public class DatabaseTest {
    
    private DatabaseService db;
    private String path;
    
    @Before
    public void setUp() {
        db = new DatabaseService();
        path = "./src/test/resources/database";
    }
    
    @Test
    public void createDatabseReturnsTrueIfDatabaseSuccessfullyCreated() {
        assertTrue(db.createDatabase(path));
    }
}
