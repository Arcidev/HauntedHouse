/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.TestSuite.Facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import pa165.hauntedhouse.Dto.PersonDTO;
import pa165.hauntedhouse.Entity.Person;
import pa165.hauntedhouse.Enums.UserRole;
import pa165.hauntedhouse.Facade.PersonFacade;
import pa165.hauntedhouse.ServiceConfig.ServiceConfiguration;

/**
 *
 * @author Andrej Dobes
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class PersonFacadeTest extends AbstractTestNGSpringContextTests {
    
    @Autowired
    PersonFacade personFacade;
    
    @Test
    public void testEntityManipulation() {
        PersonDTO person = new PersonDTO();
        person.setFirstName("Java");
        person.setLastName("Sux");
        person.setEmail("java@totalylame.com");
        person.setUserRole(UserRole.USER);
        person.setPassword("AM I EVIL?");
        
        personFacade.create(person);
        Assert.assertEquals(personFacade.findById(person.getId()), person);
        
        person.setFirstName(".NET");
        person.setLastName("RULEZ!!!");
        personFacade.update(person);
        Assert.assertEquals(personFacade.findById(person.getId()), person);
        
        personFacade.delete(person.getId());
        Assert.assertNull(personFacade.findById(person.getId()));
    }
    
    @Test
    public void testSearchMethods() {
        PersonDTO person = new PersonDTO();
        person.setFirstName("such");
        person.setLastName("eh");
        person.setEmail("much@wowagain.net");
        person.setUserRole(UserRole.USER);
        person.setPassword("trololo");
        personFacade.create(person);
        
        Assert.assertEquals(personFacade.searchByLastName("eh").size(), 1);
        Assert.assertEquals(personFacade.findPersonByEmail("much@wowagain.net"), person);
    }
    
    @Test
    public void testAuthentication() {
        PersonDTO person = new PersonDTO();
        person.setFirstName("hmm");
        person.setLastName("hmm");
        person.setEmail("kde@vsade.sk");
        person.setUserRole(UserRole.USER);
        person.setPassword("trololo");
        personFacade.create(person);
        
        Assert.assertEquals(personFacade.authenticate("kde@vsade.sk", "trololo"), person);
    }
    
    @Test
    public void testRole() {
        PersonDTO person = new PersonDTO();
        person.setFirstName("So");
        person.setLastName("Many");
        person.setEmail("Persons@nikde.sk");
        person.setUserRole(UserRole.USER);
        person.setPassword("trololo");
        personFacade.create(person);
        Assert.assertFalse(personFacade.isAdmin(person.getId()));
        
        person = new PersonDTO();
        person.setFirstName("So");
        person.setLastName("Many");
        person.setEmail("Persons@again.sk");
        person.setUserRole(UserRole.ADMIN);
        person.setPassword("trololo");
        personFacade.create(person);
        Assert.assertTrue(personFacade.isAdmin(person.getId()));
    }
}
