/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.MVC.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pa165.hauntedhouse.Facade.AbilityFacade;

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
    
    @RequestMapping(value = { "" }, method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("title", messageSource.getMessage("navigation.abilities", null, LocaleContextHolder.getLocale()));
        model.addAttribute("activePage", "Abilities");
        model.addAttribute("abilities", abilityFacade.getAllAbilities());
        
        return "ability/all";
    }
}
