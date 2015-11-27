/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.TestSuite.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import pa165.hauntedhouse.Entity.Person;
import pa165.hauntedhouse.Enums.UserRole;
import pa165.hauntedhouse.Service.PersonService;
import pa165.hauntedhouse.ServiceConfig.ServiceConfiguration;

/**
 *
 * @author Andrej Dobes
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class PersonServiceTest extends AbstractTestNGSpringContextTests {
    
    @Autowired
    PersonService personService;
    
    @Test
    public void testEntityManipulation() {
        Person person = new Person();
        person.setFirstName("Java");
        person.setLastName("Sux");
        person.setEmail("java@totalylame.com");
        person.setUserRole(UserRole.USER);
        
        personService.create(person, "AM I EVIL?");
        Assert.assertEquals(personService.findById(person.getId()), person);
        
        person.setFirstName(".NET");
        person.setLastName("RULEZ!!!");
        personService.update(person, null);
        Assert.assertEquals(personService.findById(person.getId()), person);
        
        personService.delete(person.getId());
        Assert.assertNull(personService.findById(person.getId()));
    }
    
    @Test
    public void testSearchMethods() {
        Person person = new Person();
        person.setFirstName("such");
        person.setLastName("test");
        person.setEmail("much@wow.net");
        person.setUserRole(UserRole.USER);
        personService.create(person, "trololo");
        
        Assert.assertEquals(personService.searchByLastName("test").size(), 1);
        Assert.assertEquals(personService.findPersonByEmail("much@wow.net"), person);
    }
    
    @Test
    public void testAuthentication() {
        Person person = new Person();
        person.setFirstName("hmm");
        person.setLastName("hmm");
        person.setEmail("kde@nikde.sk");
        person.setUserRole(UserRole.USER);
        personService.create(person, "trololo");
        
        Assert.assertEquals(personService.authenticate("kde@nikde.sk", "trololo"), person);
    }
    
    @Test
    public void testRole() {
        Person person = new Person();
        person.setFirstName("So");
        person.setLastName("Many");
        person.setEmail("Persons@nikde.sk");
        person.setUserRole(UserRole.USER);
        personService.create(person, "trololo");
        Assert.assertFalse(personService.isAdmin(person.getId()));
        
        person = new Person();
        person.setFirstName("So");
        person.setLastName("Many");
        person.setEmail("Persons@again.sk");
        person.setUserRole(UserRole.ADMIN);
        personService.create(person, "trololo");
        Assert.assertTrue(personService.isAdmin(person.getId()));
    }
}
