/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Service;

import java.util.List;
import pa165.hauntedhouse.Entity.House;
import pa165.hauntedhouse.Entity.Spook;

/**
 *
 * @author Milan
 */
public interface HouseService {
     /**
     * Creates new house
     * 
     * @param house
     * @return id of newly created house
     */
    int create(House house);
    
    /**
     * Updates house
     *
     * @param house
     */
    void update(House house);
    
    /**
     * Removes existing house
     * 
     * @param id
     */
    void delete(int id);
    
    /**
     * Get house by id.
     * 
     * @param id
     * @return house by id
     */
    House findById(int id);
    
    /**
     * Returns all houses.
     * 
     * @return all houses
     */
    List<House> findAll();
    
    /**
     * Gets all houses that are stored in database based on their visibility
     * 
     * @param visible
     * @return all visible or invisible housesS
     */
    List<House> findAllByVisibility(boolean visible);
    
    /**
     * Finds all abilities whiche name matches filter
     * 
     * @param filter
     * @return abilities matched by filter
     */
    List<House> searchHousesByName(String filter);
    
    /**
     * Adds house to spook
     *
     * @param houseId
     * @param spookId
     */
    void addToSpook(int houseId, int spookId);
    /**
     * Removes house from spook
     *
     * @param houseId
     * @param spookId
     */
    void removeFromSpook(int houseId, int spookId);
    
    /**
     * Sets house visibility
     * 
     * @param houseId
     * @param visible
     */
    void setVisible(int houseId, boolean visible);
    
    /**
     * Finds all spooks which haunt in this house
     * 
     * @param houseId
     * @return all spooks which haunt in this house
     */
    public List<Spook> getSpooksByHouseId(int houseId);
    
}
