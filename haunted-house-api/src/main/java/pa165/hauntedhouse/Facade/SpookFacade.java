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
    
    List<SpookInfoDTO> getAbilitySpookInfoes(int abilityId);

    void removeFromAbility(int spookId, int abilityId);

    void addHistory(SpookDTO spook, HistoryDTO history);

    void removeHistory(SpookDTO spook, HistoryDTO history);  
        
    List<SpookDTO> getAllSpooks();

    SpookDTO getSpookById(int id);
    
    List<SpookDTO> searchSpooksByName(String filterName);    
}
