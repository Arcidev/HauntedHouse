/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Service;

import java.util.List;
import pa165.hauntedhouse.Entity.Person;

/**
 *
 * @author Andrej Dobes
 */
public interface PersonService {
    /**
     * Creates new person
     * 
     * @param p
     * @param password
     * @return id of newly created person
     */
    public int create(Person p, String password);
    
    /**
     * Updates person
     *
     * @param p
     * @param newPassword
     */
    public void update(Person p, String newPassword);
    
    /**
     * Removes existing person
     * 
     * @param id
     */
    public void delete(int id);
    
    /**
     * Get person by id.
     * 
     * @param id
     * @return person by id
     */
    public Person findById(int id);
    
    /**
     * Get person by email.
     * 
     * @param email
     * @return person by email
     */
    public Person findPersonByEmail(String email);
    
    /**
     * Returns all persons.
     * 
     * @return all persons
     */
    public List<Person> findAll();
    
    /**
     * Finds all persons which name matches filter
     * 
     * @param filter
     * @return persons matched by filter
     */
    public List<Person> searchByLastName(String filter);
    
    /**
     * Returns <code>true</code> if the user is admin.
     * 
     * @param id
     * @return <code>true</code> if the user is admin;
     *         <code>false</code> otherwise
     */
    public boolean isAdmin(int id);
    
    /**
     * Authenticate of user
     * 
     * @param email
     * @param password
     * @return authenticate
     */
    public Person authenticate(String email, String password);
}
