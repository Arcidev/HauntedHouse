/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Facade;

import java.util.List;
import pa165.hauntedhouse.Dto.PersonDTO;

/**
 *
 * @author Andrej Dobes
 */
public interface PersonFacade {
    
    /**
     * Creates new person
     * 
     * @param p
     */
    public void create(PersonDTO p);
    
    /**
     * Updates existing peson
     * 
     * @param p
     */
    public void update(PersonDTO p);
    
    /**
     * Removes existing person by id
     * 
     * @param id
     */
    public void delete(int id);
    
    /**
     * Finds person by id
     * 
     * @param id
     * @return person by id
     */
    public PersonDTO findById(int id);
    
    /**
     * Gets person by email
     * 
     * @param email
     * @return person by email
     */
    public PersonDTO findPersonByEmail(String email);
    
    /**
     * Gets all persons (users)
     * 
     * @return all persons
     */
    public List<PersonDTO> findAll();
    
    /**
     * Finds all persons (users) which lastName matches filter
     * 
     * @param filter
     * @return users matched by filter
     */
    public List<PersonDTO> searchByLastName(String filter);
    
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
    public PersonDTO authenticate(String email, String password);
}
