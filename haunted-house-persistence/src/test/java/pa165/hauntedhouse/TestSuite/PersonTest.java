/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pa165.hauntedhouse.TestSuite;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.Assert;
import org.testng.annotations.Test;
import pa165.hauntedhouse.Dao.PersonDao;
import pa165.hauntedhouse.Entity.Person;
import pa165.hauntedhouse.Enums.UserRole;
import pa165.hauntedhouse.Exception.DbException;
import pa165.hauntedhouse.PersistenceConfig.PersistenceApplicationContext;

/**
 *
 * @author Milan
 */
@ContextConfiguration(classes = PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class PersonTest extends AbstractTestNGSpringContextTests{
    
    @Autowired
    private PersonDao personDao;  
    
    @Test
    public void haveToFindAll(){
        Person person1 = new Person();
        Person person2 = new Person();
        
        person1.setFirstName("Johny");
        person1.setLastName("Wick");
        person1.setEmail("john.wick@gmail.com");
        person1.setUserRole(UserRole.USER);
        person1.setPasswordHash("b");
        
        person2.setFirstName("Will");
        person2.setLastName("Smith");
        person2.setEmail("will.smith@gmail.com");
        person2.setUserRole(UserRole.ADMIN);
        person2.setPasswordHash("d");
        
        personDao.create(person1);
        personDao.create(person2);
        List<Person> person = personDao.findAll();
        
        Assert.assertEquals(person.size(), 2);
        Assert.assertTrue(person.contains(person1));
        Assert.assertTrue(person.contains(person2));
    }
    
    @Test(expectedExceptions = DbException.class)
    public void test(){
        Person person1 = new Person();
        person1.setFirstName("Johny");
        person1.setLastName("Wick");
        person1.setEmail("john.wickmail@mail.com");
        person1.setUserRole(UserRole.USER);
        
        personDao.create(person1);
    }
    
    @Test
    public void haveToFindByID() {
        Person person1 = new Person();
        person1.setFirstName("Johny");
        person1.setLastName("Wick");
        person1.setEmail("john.wick@mail.com");
        person1.setUserRole(UserRole.USER);
        person1.setPasswordHash("what");

        personDao.create(person1);

        Assert.assertEquals(personDao.findById(person1.getId()), person1);
    }
}
