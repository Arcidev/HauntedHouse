/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.MVC.Controllers;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pa165.hauntedhouse.Dto.AbilityInfoDTO;
import pa165.hauntedhouse.Exception.HttpNotFound;
import pa165.hauntedhouse.Facade.AbilityFacade;
import pa165.hauntedhouse.Facade.SpookFacade;

/**
 *
 * @author Andrej Dobes
 */
@Controller
@RequestMapping("/ability")
public class AbilityController {
    
    @Autowired
    private MessageSource messageSource; //resource bundle provided by Spring
    
    @Autowired
    private AbilityFacade abilityFacade;
    
    @Autowired
    private SpookFacade spookFacade;
    
    @RequestMapping(value = { "" }, method = RequestMethod.GET)
    public String abilities(Model model) {
        model.addAttribute("title", messageSource.getMessage("navigation.abilities", null, LocaleContextHolder.getLocale()));
        model.addAttribute("activePage", "Abilities");
        model.addAttribute("abilities", abilityFacade.getAllAbilityInfoes());
        
        return "ability/all";
    }
    
    @RequestMapping(value = { "/{id}" }, method = RequestMethod.GET)
    public String ability(@PathVariable int id, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        model.addAttribute("title", messageSource.getMessage("navigation.abilities", null, LocaleContextHolder.getLocale()));
        model.addAttribute("activePage", "Abilities");
        
        AbilityInfoDTO ability = abilityFacade.getAbilityInfoById(id);
        if (ability == null) {
            throw new HttpNotFound("There is no creature listed by id: " + id);
        }
        
        model.addAttribute("ability", ability);
        model.addAttribute("spooks", spookFacade.getAbilitySpookInfoes(ability.getId()));
        return "ability/view";
    }
}
