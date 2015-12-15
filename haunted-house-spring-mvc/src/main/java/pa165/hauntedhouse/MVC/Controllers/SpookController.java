/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.MVC.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pa165.hauntedhouse.Dto.AbilityInfoDTO;
import pa165.hauntedhouse.Dto.SpookInfoDTO;
import pa165.hauntedhouse.Exception.HttpNotFound;
import pa165.hauntedhouse.Facade.AbilityFacade;
import pa165.hauntedhouse.Facade.SpookFacade;

/**
 *
 * @author Martin Durcansky
 */
@Controller
@RequestMapping("/spook")
public class SpookController {
    @Autowired
    private MessageSource messageSource; //resource bundle provided by Spring
    
    @Autowired
    private AbilityFacade abilityFacade;
    
    @Autowired
    private SpookFacade spookFacade;
    
    @RequestMapping(value = { "" }, method = RequestMethod.GET)
    public String spooks(Model model) {
        model.addAttribute("title", messageSource.getMessage("navigation.spooks", null, LocaleContextHolder.getLocale()));
        model.addAttribute("activePage", "Spooks");
        model.addAttribute("spooks", spookFacade.getAllSpookInfoes());
        
        return "spook/all";
    }
    @RequestMapping(value = { "/{id}" }, method = RequestMethod.GET)
    public String spook(@PathVariable int id, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        model.addAttribute("title", messageSource.getMessage("navigation.spooks", null, LocaleContextHolder.getLocale()));
        model.addAttribute("activePage", "Spooks");
        
        SpookInfoDTO spook = spookFacade.getSpookInfoById(id);
        if (spook == null) {
            throw new HttpNotFound("There is no creature listed by id: " + id);
        }
        
        model.addAttribute("spook", spook);
        //model.addAttribute("abilities", abilityFacade.getAbilitySpookInfoes(spook.getId()));
        return "spook/view";
    }
    
}
