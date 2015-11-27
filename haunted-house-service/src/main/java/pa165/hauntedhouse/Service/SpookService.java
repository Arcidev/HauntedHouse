/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Service;

import java.util.List;
import pa165.hauntedhouse.Entity.Ability;
import pa165.hauntedhouse.Entity.History;
import pa165.hauntedhouse.Entity.House;
import pa165.hauntedhouse.Entity.Spook;

/**
 *
 * @author Martin Durcansky
 */
public interface SpookService {
    
    /**
     * Creates new spook
     * 
     * @param spook
     * @return id of newly created spook
     */
    int create(Spook spook);
    
    /**
     * Updates spook
     *
     * @param spook
     */
    void update(Spook spook);
    
    /**
     * Removes existing spook
     * 
     * @param id
     */    
    void delete(int id);
    
    /**
     * Get spooky by id.
     * 
     * @param id
     * @return spook by id
     */
    Spook findById(int id);
    
    /**
     * Returns all spooks.
     * 
     * @return all spooks
     */
    List<Spook> findAll();
    
        void addToAbility(int spookId, int abilityId);
    
    /**
     * Removes spook from ability
     *
     * @param abilityId
     * @param spookId
     */
    void removeFromAbility(int spookId, int abilityId);
    /**
     * Adds history to spook
     *
     * @param history
     * @param spook
     */
    void addHistory(Spook spook, History history);
    
    /**
     * Removes history from spook
     *
     * @param history
     * @param spook
     */
    void removeHistory(Spook spook, History history);
    
    /**
     * Gets spook abilities
     * 
     * @param spookId
     * @return all spook abilities
     */
    public List<Ability> getAbilitiesBySpookId(int spookId);
    
    /**
     * Gets spook's house
     * 
     * @param spookId
     * @return spook's house
     */
    public House getHouseBySpookId(int spookId);
    
    /**
     * Finds all spooks which name matches filter
     * 
     * @param filter
     * @return spooks matched by filter
     */
    List<Spook> searchSpooksByName(String filter);
    
}
