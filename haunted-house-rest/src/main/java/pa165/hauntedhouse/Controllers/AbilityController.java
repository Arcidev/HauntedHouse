/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pa165.hauntedhouse.Dto.AbilityDTO;
import pa165.hauntedhouse.Dto.AbilityInfoDTO;
import pa165.hauntedhouse.Dto.SpookInfoDTO;
import pa165.hauntedhouse.Exception.EntityNotFound;
import pa165.hauntedhouse.Facade.AbilityFacade;
import pa165.hauntedhouse.Facade.SpookFacade;

/**
 *
 * @author Andrej Dobes
 */
@RestController
@RequestMapping("ability")
public class AbilityController {
    
    @Autowired
    private AbilityFacade abilityFacade;
    
    @Autowired
    private SpookFacade spookFacade;
    
    @RequestMapping(value = "all/{visible}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<AbilityInfoDTO> abilities(@PathVariable boolean visible, @RequestParam(value = "searchFilter", required = false) String searchFilter) {
        return searchFilter != null ? abilityFacade.searchAbilitiesByName(searchFilter, visible) : abilityFacade.getAllAbilityInfoesByVisibility(visible);
    }
    
    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final AbilityInfoDTO ability(@PathVariable int id) {
        AbilityInfoDTO ability = abilityFacade.getAbilityInfoById(id);
        if (ability == null) {
            throw new EntityNotFound("There is no ability listed by id: " + id);
        }
        
        return ability;
    }
    
    @RequestMapping(value = "spooks/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<SpookInfoDTO> abilitySpooks(@PathVariable int id) {
        AbilityInfoDTO ability = abilityFacade.getAbilityInfoById(id);
        if (ability == null) {
            throw new EntityNotFound("There is no ability listed by id: " + id);
        }
        
        return spookFacade.getAbilitySpookInfoes(ability.getId());
    }

    
    @RequestMapping(value = "create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public AbilityInfoDTO create(@RequestBody AbilityDTO ability) {
        abilityFacade.createAbility(ability);
        return abilityFacade.getAbilityById(ability.getId());
    }
    
    @RequestMapping(value = { "edit" }, method = RequestMethod.PUT)
    public AbilityInfoDTO edit(@RequestBody AbilityDTO ability) {
        if (abilityFacade.getAbilityInfoById(ability.getId()) == null) {
            throw new EntityNotFound("There is no ability listed by id: " + ability.getId());
        }
        abilityFacade.updateAbility(ability);
        return abilityFacade.getAbilityById(ability.getId());
    }
    
    @RequestMapping(value = { "visible/{id}/{visible}" }, method = RequestMethod.GET)
    public AbilityInfoDTO setVisible(@PathVariable int id, @PathVariable boolean visible) {
        abilityFacade.setVisible(id, visible);
        
        return abilityFacade.getAbilityById(id);
    }
}
