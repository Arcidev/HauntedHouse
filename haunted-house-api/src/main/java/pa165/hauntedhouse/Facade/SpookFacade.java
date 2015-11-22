/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Facade;

import java.util.List;
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
    int createSpook(SpookDTO s);

    /**
     * Removes existing spook by id
     * 
     * @param spookId
     */
    void deleteSpook(int spookId);

    /**
     * Updates existing spook
     * 
     * @param s
     */
    void updateSpook(SpookDTO s);

    /**
     * Adds ability to spook
     * 
     * @param spookId
     * @param abilityId
     */
    void addAbility(int spookId, int abilityId);

    /**
     * Removes ability from spook
     * 
     * @param spookId
     * @param abilityId
     */
    void removeAbility(int spookId, int abilityId);

    /**
     * Adds history to spook
     * 
     * @param spookId
     * @param historyId
     */
    void addHistory(int spookId, int historyId);

    /**
     * Removes history from spook
     * 
     * @param spookId
     * @param historyId
     */
    void removeHistory(int spookId, int historyId);

    /**
     * Sets house to spook
     * 
     * @param spookId
     * @param houseId
     */
    void setHouse(int spookId, int houseId);

    /**
     * Removes house from spook
     * 
     * @param spookId
     */
    void removeHouse(int spookId);
    
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
