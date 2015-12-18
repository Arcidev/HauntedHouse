/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Facade;

import java.util.List;
import pa165.hauntedhouse.Dto.AbilityDTO;
import pa165.hauntedhouse.Dto.AbilityInfoDTO;

/**
 *
 * @author Andrej Dobes
 */
public interface AbilityFacade {

    /**
     * Creates new ability
     * 
     * @param a
     */
    void createAbility(AbilityDTO a);

    /**
     * Removes existing ability
     * 
     * @param id
     */
    void deleteAbility(int id);

    /**
     * Updates existing ability
     * 
     * @param a
     */
    void updateAbility(AbilityDTO a);

    /**
     * Adds ability to spook
     * 
     * @param abilityId
     * @param spookId
     */
    void addToSpook(int abilityId, int spookId);

    /**
     * Removes abilty from spook
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
     * Gets all abilities
     * 
     * @return all abilities
     */
    List<AbilityDTO> getAllAbilities();

    /**
     * Gets all abilities that are stored in database based on their visibility
     * 
     * @param visible
     * @return all visible or invisible abilities
     */
    List<AbilityInfoDTO> getAllAbilityInfoesByVisibility(boolean visible);
    
    /**
     * Gets ability by id
     * 
     * @param id
     * @return ability by id
     */
    AbilityDTO getAbilityById(int id);

    /**
     * Gets ability info by id
     * 
     * @param id
     * @return ability info by id
     */
    AbilityInfoDTO getAbilityInfoById(int id);
    
    /**
     * Gets spook abilities
     * 
     * @param spookId
     * @return all spook abilities
     */
    List<AbilityDTO> getSpookAbilities(int spookId);

    /**
     * Finds all abilities whiche name matches filter
     * 
     * @param filter
     * @param visible
     * @return abilities matched by filter
     */
    List<AbilityInfoDTO> searchAbilitiesByName(String filter, boolean visible);
    
    /**
     * Gets spook ability infoes
     * 
     * @param spookId
     * @return all spook ability infoes
     */
    List<AbilityInfoDTO> getSpookAbilityInfoes(int spookId);
}
