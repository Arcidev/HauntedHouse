/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.MVC.Controllers;

import java.io.IOException;
import javax.servlet.ServletOutputStream;
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
import pa165.hauntedhouse.Dto.AbilityDTO;
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
        model.addAttribute("abilities", abilityFacade.getAllAbilityInfoes());
        
        return "ability/all";
    }
    
    @RequestMapping("/image/{id}")
    public void productImage(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        AbilityDTO ability = abilityFacade.getAbilityById(id);
        byte[] image = ability.getImage();
        if(image==null) {
            response.sendRedirect(request.getContextPath()+"/no-image.png");
        } else {
            response.setContentType(ability.getImageMimeType());
            ServletOutputStream out = response.getOutputStream();
            out.write(image);
            out.flush();
        }
    }
}
