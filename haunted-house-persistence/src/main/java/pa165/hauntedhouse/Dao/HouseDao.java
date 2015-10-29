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
    public House findById(int id);
    
    /**
     * Creates new house
     * 
     * @param hs
     */
    public void create(House hs);
    
    /**
     * Deletes existing house
     * 
     * @param hs
     */
    public void delete(House hs);
    /**
     * Updates existing house
     * 
     * @param hs
     */
    public void update(House hs);
    /**
     * Gets all houses that are stored in database
     * 
     * @return the list of all houses
     */
    public List<House> findAll();
    
    /**
     * Returns a house with specified name from database
     * 
     * @param name
     * @return the house with specified name
     */
    public House findByName(String name);
}
