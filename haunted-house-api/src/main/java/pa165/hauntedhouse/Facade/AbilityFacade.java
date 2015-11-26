/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Facade;

import java.util.List;
import pa165.hauntedhouse.Dto.AbilityDTO;
import pa165.hauntedhouse.Dto.SpookDTO;

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
     * Gets all abilities
     * 
     * @return all abilities
     */
    List<AbilityDTO> getAllAbilities();

    /**
     * Gets ability by id
     * 
     * @param id
     * @return ability by id
     */
    AbilityDTO getAbilityById(int id);

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
     * @return abilities matched by filter
     */
    List<AbilityDTO> searchAbilitiesByName(String filter);
}
