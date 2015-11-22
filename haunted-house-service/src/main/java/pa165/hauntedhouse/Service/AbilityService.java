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
     */
    void create(Ability ability);
    
    /**
     * Updates ability
     *
     * @param ability
     * @return updated ability
     */
    Ability update(Ability ability);
    
    /**
     * Removes existing ability
     * 
     * @param ability
     */
    void remove(Ability ability);
    
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
     * Adds ability to spook
     *
     * @param ability
     * @param spook
     */
    void addToSpook(Ability ability, Spook spook);
    /**
     * Removes ability from spook
     *
     * @param ability
     * @param spook
     */
    void removeFromSpook(Ability ability, Spook spook);
}
