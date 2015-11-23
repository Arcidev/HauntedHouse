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
    public Ability findById(int id);
    
    /**
     * Creates new ability
     * 
     * @param a
     */
    public void create(Ability a);
    
    /**
     * Deletes existing ability
     * 
     * @param a
     */
    public void delete(Ability a);
    
    /**
     * Updates existing ability
     * 
     * @param a
     */
    public void update(Ability a);
    
    /**
     * Gets all abilities that are stored in database
     * 
     * @return the list of all abilities
     */
    public List<Ability> findAll();
    
    /**
     * Returns an ability with specified name from database
     * 
     * @param name
     * @return the ability with specified name
     */
    public Ability findByName(String name);
    
    /**
     * Searches abilities name by filter
     * 
     * @param filter
     * @return abilities that match the filter
     */
    public List<Ability> searchByName(String filter);
}
