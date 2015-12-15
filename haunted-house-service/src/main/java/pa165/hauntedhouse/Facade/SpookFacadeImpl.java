/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Facade;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pa165.hauntedhouse.Dto.HistoryDTO;
import pa165.hauntedhouse.Dto.SpookDTO;
import pa165.hauntedhouse.Dto.SpookInfoDTO;
import pa165.hauntedhouse.Entity.History;
import pa165.hauntedhouse.Entity.Spook;
import pa165.hauntedhouse.Service.AbilityService;
import pa165.hauntedhouse.Service.HistoryService;
import pa165.hauntedhouse.Service.SpookService;
import pa165.hauntedhouse.ServiceConfig.Service.BeanMappingService;

/**
 *
 * @author Martin Durcansky
 */
@Service
@Transactional
public class SpookFacadeImpl implements SpookFacade {
    @Autowired
    private BeanMappingService beanMappingService;
    
    @Autowired
    private AbilityService abilityService;
    
    @Autowired
    private SpookService spookService;
    @Autowired
    private HistoryService historyService;

    @Override
    public void createSpook(SpookDTO s) {        
        s.setId(spookService.create(beanMappingService.mapTo(s, Spook.class)));
    }

    @Override
    public void deleteSpook(int id) {
        spookService.delete(id);
    }

    @Override
    public void updateSpook(SpookDTO s) {        
        spookService.update(beanMappingService.mapTo(s, Spook.class));
    }

    @Override
    public void addToAbility(int spookId, int abilityId) {     
         spookService.addToAbility(spookId,abilityId);
    }

    @Override
    public void removeFromAbility(int spookId, int abilityId) {       
       abilityService.removeFromSpook(spookId, abilityId);
    }
    
    @Override
    public List<SpookDTO> getAbilitySpooks(int abilityId) {
        return beanMappingService.mapTo(abilityService.getSpooksByAbilityId(abilityId), SpookDTO.class);
    }
    
    @Override
    public List<SpookInfoDTO> getAbilitySpookInfoes(int abilityId) {
        return beanMappingService.mapTo(abilityService.getSpooksByAbilityId(abilityId), SpookInfoDTO.class);
    }
    
    @Override
    public void addHistory(SpookDTO s, HistoryDTO h) {
        spookService.addHistory(beanMappingService.mapTo(s, Spook.class), beanMappingService.mapTo(h, History.class));        
    }

    @Override
    public void removeHistory(SpookDTO s, HistoryDTO h) {
        spookService.removeHistory(beanMappingService.mapTo(s, Spook.class), beanMappingService.mapTo(h, History.class));
    }
       

   @Override
    public List<SpookDTO> getAllSpooks() {
        return beanMappingService.mapTo(spookService.findAll(), SpookDTO.class);
    }

    @Override
    public SpookDTO getSpookById(int id) {
        return beanMappingService.mapTo(spookService.findById(id), SpookDTO.class);
    }

    @Override
    public List<SpookDTO> searchSpooksByName(String filter) {
        return beanMappingService.mapTo(spookService.searchSpooksByName(filter), SpookDTO.class);
    }
    
    @Override
    public SpookDTO getHistorySpook(int historyId) {
        return beanMappingService.mapTo(historyService.getSpookByHistoryId(historyId), SpookDTO.class);
    }      
  
    @Override
    public List<SpookInfoDTO> getAllSpookInfoes() {
        return beanMappingService.mapTo(spookService.findAll(), SpookInfoDTO.class);
    }
    public SpookInfoDTO getSpookInfoById(int id) {
        Spook s = spookService.findById(id);
        if (s == null) {
            return null;
        }
        
        return beanMappingService.mapTo(s, SpookInfoDTO.class);
    }
    
}
