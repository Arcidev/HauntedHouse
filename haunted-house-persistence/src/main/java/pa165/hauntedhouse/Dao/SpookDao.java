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
    public Spook findById(int id);
    
    /**
     * Creates new spook
     * 
     * @param spk
     */
    public void create(Spook spk);
    /**
     * Updates existing spook
     * 
     * @param apk
     */
    public void update(Spook spk);
    /**
     * Deletes existing spook
     * 
     * @param spk
     */
    
    public void delete(Spook spk);
    /**
     * Gets all spooks that are stored in database
     * 
     * @return the list of all spooks
     */
    public List<Spook> findAll();
    
    /**
     * Returns an spook with specified name from database
     * 
     * @param name
     * @return the spook with specified name
     */
    public Spook findByName(String name);
}

