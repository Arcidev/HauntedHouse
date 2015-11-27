/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Facade;

import java.util.List;
import pa165.hauntedhouse.Dto.AbilityDTO;
import pa165.hauntedhouse.Dto.HistoryDTO;
import pa165.hauntedhouse.Dto.SpookDTO;

/**
 *
 * @author Andrej Dobes
 */
public interface SpookFacade {

    /**
     * Creates new Spook
     * 
     * @param s
     * @return id of newly created spook
     */
    void createSpook(SpookDTO spk);

    /**
     * Removes existing spook 
     * 
     * @param s
     */
    void deleteSpook(int id);

    /**
     * Updates existing spook
     * 
     * @param s
     */
    void updateSpook(SpookDTO spk);

    /**
     * Adds ability to spook
     * 
     * 
     * @param spookId
     * @param abilityId
     */
    void addToAbility(int spookId, int abilityId);
    
    
    SpookDTO getHistorySpook(int historyId);
    
    List<SpookDTO> getAbilitySpooks(int abilityId);
    
     //SpookDTO getHistorySpook(int historyId);
    

    /**
     * Removes spook from ability
     * 
     * @param spookId
     * @param abilityId
     */
    void removeFromAbility(int spookId, int abilityId);

    /**
     * Adds history to spook
     * 
     * @param spook
     * @param history
     */
    void addHistory(SpookDTO spook, HistoryDTO history);

    /**
     * Removes history from spook
     * 
     * @param spook
     * @param history
     */
    void removeHistory(SpookDTO spook, HistoryDTO history);

    /**
     * Sets house to spook
     * 
     * @param spookId
     * @param houseId
     */
    //void setHouse(int spookId, int houseId);

    /**
     * Removes house from spook
     * 
     * @param spookId
     */
    //void removeHouse(int spookId);
    
    /**
     * Gets all spooks
     * 
     * @return all spooks
     */
    List<SpookDTO> getAllSpooks();

    /**
     * Gets spook by id
     * 
     * @param id
     * @return spook by id
     */
    SpookDTO getSpookById(int id);

    /**
     * Finds all spooks which name matches the filter
     * 
     * @param filterName
     * @return spooks matched by filter
     */
    List<SpookDTO> searchSpooksByName(String filterName);    
}
