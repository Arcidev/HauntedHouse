/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pa165.hauntedhouse.Dao;

import java.util.List;
import pa165.hauntedhouse.Entity.House;

/**
 *
 * @author Milan
 */
public interface HouseDao {
    /**
     * Returns a house with specified id from database
     * 
     * @param id
     * @return the house with specified id
     */
    House findById(int id);
    
    /**
     * Creates new house
     * 
     * @param hs
     */
    void create(House hs);
    
    /**
     * Deletes existing house
     * 
     * @param id
     */
    void delete(int id);
    /**
     * Updates existing house
     * 
     * @param hs
     */
    void update(House hs);
    /**
     * Gets all houses that are stored in database
     * 
     * @return the list of all houses
     */
    List<House> findAll();
    
    /**
     * Gets all houses that are stored in database based on their visibility
     * 
     * @param visible
     * @return all visible or invisible houses
     */
    List<House> findAllByVisibility(boolean visible);
    
    /**
     * Returns a house with specified name from database
     * 
     * @param name
     * @return the house with specified name
     */
    House findByName(String name);
    
    /**
     * Searches houses name by filter
     * 
     * @param filter
     * @param visible
     * @return house that match the filter
     */
    List<House> searchByName(String filter, boolean visible);
}
