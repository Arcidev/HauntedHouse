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
import pa165.hauntedhouse.Dto.AbilityDTO;
import pa165.hauntedhouse.Dto.AbilityInfoDTO;
import pa165.hauntedhouse.Entity.Ability;
import pa165.hauntedhouse.Service.AbilityService;
import pa165.hauntedhouse.Service.SpookService;
import pa165.hauntedhouse.ServiceConfig.Service.BeanMappingService;

/**
 *
 * @author Andrej Dobes
 */
@Service
@Transactional
public class AbilityFacadeImpl implements AbilityFacade {

    @Autowired
    private BeanMappingService beanMappingService;
    
    @Autowired
    private AbilityService abilityService;
    
    @Autowired
    private SpookService spookService;
    
    @Override
    public void createAbility(AbilityDTO a) {
        a.setId(abilityService.create(beanMappingService.mapTo(a, Ability.class)));
    }

    @Override
    public void deleteAbility(int id) {
        abilityService.delete(id);
    }

    @Override
    public void updateAbility(AbilityDTO a) {
        abilityService.update(beanMappingService.mapTo(a, Ability.class));
    }

    @Override
    public void addToSpook(int abilityId, int spookId) {
        abilityService.addToSpook(abilityId, spookId);
    }

    @Override
    public void removeFromSpook(int abilityId, int spookId) {
         abilityService.removeFromSpook(abilityId, spookId);
    }
    
    @Override
    public void setVisible(int abilityId, boolean visible) {
        abilityService.setVisible(abilityId, visible);
    }

    @Override
    public List<AbilityDTO> getAllAbilities() {
        return beanMappingService.mapTo(abilityService.findAll(), AbilityDTO.class);
    }
    
    @Override
    public List<AbilityInfoDTO> getAllAbilityInfoesByVisibility(boolean visible) {
        return beanMappingService.mapTo(abilityService.findAllByVisibility(visible), AbilityInfoDTO.class);
    }

    @Override
    public AbilityDTO getAbilityById(int id) {
        Ability a = abilityService.findById(id);
        if (a == null) {
            return null;
        }
        
        return beanMappingService.mapTo(a, AbilityDTO.class);
    }
    
    @Override
    public AbilityInfoDTO getAbilityInfoById(int id) {
        Ability a = abilityService.findById(id);
        if (a == null) {
            return null;
        }
        
        return beanMappingService.mapTo(a, AbilityInfoDTO.class);
    }

    @Override
    public List<AbilityDTO> getSpookAbilities(int spookId) {
        return beanMappingService.mapTo(spookService.getAbilitiesBySpookId(spookId), AbilityDTO.class);
    }

    @Override
    public List<AbilityInfoDTO> searchAbilitiesByName(String filter, boolean visible) {
        return beanMappingService.mapTo(abilityService.searchAbilitiesByName(filter, visible), AbilityInfoDTO.class);
    }
    @Override
    public List<AbilityInfoDTO> getSpookAbilityInfoes(int spookId) {
        return beanMappingService.mapTo(spookService.getAbilitiesBySpookId(spookId), AbilityInfoDTO.class);
    }
    
}
