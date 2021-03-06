/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Dao;

import java.util.List;
import pa165.hauntedhouse.Entity.Ability;

/**
 *
 * @author Andrej Dobes
 */
public interface AbilityDao {

    /**
     * Returns an ability with specified id from database
     * 
     * @param id
     * @return the ability with specified id
     */
    Ability findById(int id);
    
    /**
     * Creates new ability
     * 
     * @param a
     */
    void create(Ability a);
    
    /**
     * Deletes existing ability
     * 
     * @param id
     */
    void delete(int id);
    
    /**
     * Updates existing ability
     * 
     * @param a
     */
    void update(Ability a);
    
    /**
     * Gets all abilities that are stored in database
     * 
     * @return the list of all abilities
     */
    List<Ability> findAll();
    
    /**
     * Gets all abilities that are stored in database based on their visibility
     * 
     * @param visible
     * @return all visible or invisible abilities
     */
    List<Ability> findAllByVisibility(boolean visible);
    
    /**
     * Returns an ability with specified name from database
     * 
     * @param name
     * @return the ability with specified name
     */
    Ability findByName(String name);
    
    /**
     * Searches abilities name by filter
     * 
     * @param filter
     * @param visible
     * @return abilities that match the filter
     */
    List<Ability> searchByName(String filter, boolean visible);
}
