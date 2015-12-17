/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.MVC.Controllers;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;
import pa165.hauntedhouse.Dto.AbilityDTO;
import pa165.hauntedhouse.Dto.AbilityInfoDTO;
import pa165.hauntedhouse.Exception.HttpNotFound;
import pa165.hauntedhouse.Facade.AbilityFacade;
import pa165.hauntedhouse.Facade.SpookFacade;

/**
 *
 * @author Andrej Dobes
 */
@Controller
@RequestMapping("ability")
public class AbilityController extends BaseController {
    
    @Autowired
    private MessageSource messageSource; //resource bundle provided by Spring
    
    @Autowired
    private AbilityFacade abilityFacade;
    
    @Autowired
    private SpookFacade spookFacade;
    
    @RequestMapping(value = { "" }, method = RequestMethod.GET)
    public String abilities(Model model) {
        inicializeCall(model, messageSource.getMessage("navigation.abilities", null, LocaleContextHolder.getLocale()), "Abilities");
        model.addAttribute("abilities", abilityFacade.getAllAbilityInfoes());
        
        return "ability/all";
    }
    
    @RequestMapping(value = { "{id}" }, method = RequestMethod.GET)
    public String ability(@PathVariable int id, Model model, HttpServletRequest request, HttpServletResponse response) {
        inicializeCall(model, messageSource.getMessage("navigation.abilities", null, LocaleContextHolder.getLocale()), "Abilities");
        
        AbilityInfoDTO ability = abilityFacade.getAbilityInfoById(id);
        if (ability == null) {
            throw new HttpNotFound("There is no ability listed by id: " + id);
        }
        
        model.addAttribute("ability", ability);
        model.addAttribute("spooks", spookFacade.getAbilitySpookInfoes(ability.getId()));
        return "ability/view";
    }
    
    @RequestMapping(value = { "new" }, method = RequestMethod.GET)
    public String newAbility(Model model) {
        inicializeCall(model, messageSource.getMessage("navigation.abilities", null, LocaleContextHolder.getLocale()), "Abilities");
        model.addAttribute("abilityEdit", new AbilityInfoDTO());
        
        return "ability/edit";
    }
    
    @RequestMapping(value = { "edit/{id}" }, method = RequestMethod.GET)
    public String editAbility(@PathVariable int id, Model model, HttpServletRequest request, HttpServletResponse response) {
        inicializeCall(model, messageSource.getMessage("navigation.abilities", null, LocaleContextHolder.getLocale()), "Abilities");
        
        AbilityInfoDTO ability = abilityFacade.getAbilityInfoById(id);
        if (ability == null) {
            throw new HttpNotFound("There is no ability listed by id: " + id);
        }
        
        model.addAttribute("abilityEdit", ability);
        return "ability/edit";
    }
    
    @RequestMapping(value = { "edit" }, method = RequestMethod.POST)
    public String editPost(@Valid @ModelAttribute("abilityEdit") AbilityDTO ability, BindingResult bindingResult, @RequestParam(value = "file", required = false) MultipartFile file, Model model, UriComponentsBuilder uriBuilder) throws IOException {
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().stream().forEach((fe) -> {
                model.addAttribute(fe.getField() + "_error", fe.getDefaultMessage());
            });
            return "ability/edit";
        }
        
        setImageFromFile(file, ability);
        if (ability.getId() == 0)
            abilityFacade.createAbility(ability);
        else
            abilityFacade.updateAbility(ability);
        
        return "redirect:" + uriBuilder.path("/ability/" + ability.getId()).build().toString();
    }
}
