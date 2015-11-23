/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Facade;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import pa165.hauntedhouse.Dto.AbilityDTO;
import pa165.hauntedhouse.Dto.SpookDTO;
import pa165.hauntedhouse.Entity.Ability;
import pa165.hauntedhouse.Entity.Spook;
import pa165.hauntedhouse.Service.AbilityService;
import pa165.hauntedhouse.Service.SpookService;
import pa165.hauntedhouse.ServiceConfig.Service.BeanMappingService;

/**
 *
 * @author Andrej Dobes
 */
public class AbilityFacadeImpl implements AbilityFacade {

    @Autowired
    private BeanMappingService beanMappingService;
    
    @Autowired
    private AbilityService abilityService;
    
    @Autowired
    private SpookService spookService;
    
    @Override
    public int createAbility(AbilityDTO a) {
        return abilityService.create(beanMappingService.mapTo(a, Ability.class));
    }

    @Override
    public void deleteAbility(AbilityDTO a) {
        abilityService.delete(beanMappingService.mapTo(a, Ability.class));
    }

    @Override
    public void updateAbility(AbilityDTO a) {
        abilityService.update(beanMappingService.mapTo(a, Ability.class));
    }

    @Override
    public void addToSpook(AbilityDTO ability, SpookDTO spook) {
        abilityService.addToSpook(beanMappingService.mapTo(ability, Ability.class), beanMappingService.mapTo(spook, Spook.class));
    }

    @Override
    public void removeFromSpook(AbilityDTO ability, SpookDTO spook) {
         abilityService.removeFromSpook(beanMappingService.mapTo(ability, Ability.class), beanMappingService.mapTo(spook, Spook.class));
    }

    @Override
    public List<AbilityDTO> getAllAbilities() {
        return beanMappingService.mapTo(abilityService.findAll(), AbilityDTO.class);
    }

    @Override
    public AbilityDTO getAbilityById(int id) {
        return beanMappingService.mapTo(abilityService.findById(id), AbilityDTO.class);
    }

    @Override
    public List<AbilityDTO> getSpookAbilities(int spookId) {
        return beanMappingService.mapTo(spookService.getAbilitiesBySpookId(spookId), AbilityDTO.class);
    }

    @Override
    public List<AbilityDTO> searchAbilitiesByName(String filter) {
        return beanMappingService.mapTo(abilityService.searchAbilitiesByName(filter), AbilityDTO.class);
    }
    
}
