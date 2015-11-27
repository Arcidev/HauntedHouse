/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pa165.hauntedhouse.Dao;

import java.util.List;
import pa165.hauntedhouse.Entity.Person;

/**
 *
 * @author Milan
 */
public interface PersonDao {
    
    /**
     * Creates new person
     * 
     * @param p
     */
    public void create(Person p);
    
    /**
     * Updates existing person
     * 
     * @param p
     */
    public void update(Person p);
    
    /**
     * Deletes existing person
     * 
     * @param p
     */
    public void delete(Person p);
    
    /**
     * Returns a person with specified id from database
     * 
     * @param id
     * @return the person with specified id
     */
    public Person findById(int id);
    
    /**
     * Returns a person with specified email from database
     * 
     * @param email
     * @return the person with specified email
     */
    public Person findPersonByEmail(String email);
    
    /**
     * Gets all persons (users) that are stored in database
     * 
     * @return the list of all persons
     */
    public List<Person> findAll();
    
    /**
     * Searches persons (users) by filter
     * 
     * @param filter
     * @return person that match the filter
     */
    public List<Person> searchByLastName(String filter);
}
