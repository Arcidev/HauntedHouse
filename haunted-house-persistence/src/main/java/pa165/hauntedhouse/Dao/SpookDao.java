/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Dao;

import java.util.List;
import pa165.hauntedhouse.Entity.Spook;


/**
 *
 * @author Martin Durcansky
 */
public interface SpookDao {
    /**
     * Returns a spook with specified id from database
     * 
     * @param id
     * @return the spook with specified id
     */
     Spook findById(int id);
    
    /**
     * Creates new spook
     * 
     * @param spk
     */
     void create(Spook spk);
    /**
     * Updates existing spook
     * 
     * @param spk
     */
     void update(Spook spk);
    /**
     * Deletes existing spook
     * 
     * @param id
     */
    
     void delete(int id);
    /**
     * Gets all spooks that are stored in database
     * 
     * @return the list of all spooks
     */
     List<Spook> findAll();
    
    /**
     * Returns an spook with specified name from database
     * 
     * @param name
     * @return the spook with specified name
     */
     Spook findByName(String name);
    /**
     * Searches spooks name by filter
     * 
     * @param filter
     * @param visible
     * @return spooks that match the filter
     */
     List<Spook> searchByName(String filter, boolean visible);
    /**
     * Gets all spooks that are stored in database based on their visibility
     * 
     * @param visible
     * @return all visible or invisible spooks
     */
    List<Spook> findAllByVisibility(boolean visible);
}

