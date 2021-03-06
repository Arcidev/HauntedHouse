/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Service;

import java.util.List;
import pa165.hauntedhouse.Entity.Ability;
import pa165.hauntedhouse.Entity.Spook;

/**
 *
 * @author Andrej Dobes
 */
public interface AbilityService {
    /**
     * Creates new ability
     * 
     * @param ability
     * @return id of newly created ability
     */
    int create(Ability ability);
    
    /**
     * Updates ability
     *
     * @param ability
     */
    void update(Ability ability);
    
    /**
     * Removes existing ability
     * 
     * @param id
     */
    void delete(int id);
    
    /**
     * Get ability by id.
     * 
     * @param id
     * @return ability by id
     */
    Ability findById(int id);
    
    /**
     * Returns all abilities.
     * 
     * @return all abilities
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
     * Adds ability to spook
     *
     * @param abilityId
     * @param spookId
     */
    void addToSpook(int abilityId, int spookId);
    /**
     * Removes ability from spook
     *
     * @param abilityId
     * @param spookId
     */
    void removeFromSpook(int abilityId, int spookId);
    
    /**
     * Sets ability visibility
     * 
     * @param abilityId
     * @param visible
     */
    void setVisible(int abilityId, boolean visible);
    
    /**
     * Finds all abilities which name matches filter
     * 
     * @param filter
     * @param visible
     * @return abilities matched by filter
     */
    List<Ability> searchAbilitiesByName(String filter, boolean visible);
    
    /**
     * Finds all spooks associated with passed abilityId
     * 
     * @param abilityId
     * @return all spooks associated with passed abilityId
     */
    public List<Spook> getSpooksByAbilityId(int abilityId);
}
