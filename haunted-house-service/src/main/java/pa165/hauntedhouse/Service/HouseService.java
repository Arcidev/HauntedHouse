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
     * @param house
     */
    void delete(House house);
    
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
     * Finds all abilities whiche name matches filter
     * 
     * @param filter
     * @return abilities matched by filter
     */
    List<House> searchHousesByName(String filter);
    
    /**
     * Adds house to spook
     *
     * @param house
     * @param spook
     */
    void addToSpook(House house, Spook spook);
    /**
     * Removes house from spook
     *
     * @param house
     * @param spook
     */
    void removeFromSpook(House house, Spook spook);
    
}
