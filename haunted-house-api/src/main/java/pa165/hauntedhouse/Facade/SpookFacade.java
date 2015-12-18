/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Facade;

import java.util.List;
import pa165.hauntedhouse.Dto.HistoryDTO;
import pa165.hauntedhouse.Dto.SpookDTO;
import pa165.hauntedhouse.Dto.SpookInfoDTO;

/**
 *
 * @author Andrej Dobes
 */
public interface SpookFacade {

    void createSpook(SpookDTO spk);

    void deleteSpook(int id);

    void updateSpook(SpookDTO spk);

    void addToAbility(int spookId, int abilityId);    
    
    SpookDTO getHistorySpook(int historyId);
    
    List<SpookDTO> getAbilitySpooks(int abilityId);
    /**
     * Gets all basic spook infoes
     * 
     * @return all spook infoes
     */
   // List<SpookInfoDTO> getAllSpookInfoes();
    /**
     * Gets spook info by id
     * 
     * @param id
     * @return spook info by id
     */
    SpookInfoDTO getSpookInfoById(int id);
        
    List<SpookInfoDTO> getAbilitySpookInfoes(int abilityId);
    
    List<SpookInfoDTO> getHouseSpookInfoes(int houseId);

    void removeFromAbility(int spookId, int abilityId);

    void addHistory(SpookDTO spook, HistoryDTO history);

    void removeHistory(SpookDTO spook, HistoryDTO history);  
        
    List<SpookDTO> getAllSpooks();

    SpookDTO getSpookById(int id);
    
    List<SpookDTO> searchSpooksByName(String filterName);
    /**
     * Sets spook visibility
     * 
     * @param spookId
     * @param visible
     */
    void setVisible(int spookId, boolean visible);
    /**
     * Gets all spooks that are stored in database based on their visibility
     * 
     * @param visible
     * @return all visible or invisible spooks
     */
    List<SpookInfoDTO> getAllSpookInfoesByVisibility(boolean visible);
}
