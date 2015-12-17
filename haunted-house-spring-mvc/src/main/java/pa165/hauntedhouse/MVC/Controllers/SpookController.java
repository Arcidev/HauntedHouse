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
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;
import pa165.hauntedhouse.Dto.AbilityInfoDTO;
import pa165.hauntedhouse.Dto.SpookDTO;
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
public class SpookController extends BaseController {
    @Autowired
    private MessageSource messageSource; //resource bundle provided by Spring
    
    @Autowired
    private AbilityFacade abilityFacade;
    
    @Autowired
    private SpookFacade spookFacade;
    
    @RequestMapping(value = { "" }, method = RequestMethod.GET)
    public String spooks(Model model) {
        inicializeCall(model, messageSource.getMessage("navigation.spooks", null, LocaleContextHolder.getLocale()), "Spooks");
        model.addAttribute("spooks", spookFacade.getAllSpookInfoes());
        
        return "spook/all";
    }
    @RequestMapping(value = { "/{id}" }, method = RequestMethod.GET)
    public String spook(@PathVariable int id, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        inicializeCall(model, messageSource.getMessage("navigation.spooks", null, LocaleContextHolder.getLocale()), "Spooks");
        
        SpookInfoDTO spook = spookFacade.getSpookInfoById(id);
        if (spook == null) {
            throw new HttpNotFound("There is no creature listed by id: " + id);
        }
        
        model.addAttribute("spook", spook);
        //model.addAttribute("abilities", abilityFacade.getSpookAbilityInfoes(spook.getId()));
        return "spook/view";
    }
    
    @RequestMapping(value = { "new" }, method = RequestMethod.GET)
    public String newSpook(Model model) {
        inicializeCall(model, messageSource.getMessage("navigation.spooks", null, LocaleContextHolder.getLocale()), "Spooks");
        model.addAttribute("spookEdit", new SpookInfoDTO());
        
        return "spook/edit";
    }
    
    @RequestMapping(value = { "edit/{id}" }, method = RequestMethod.GET)
    public String editSpook(@PathVariable int id, Model model, HttpServletRequest request, HttpServletResponse response) {
        inicializeCall(model, messageSource.getMessage("navigation.spooks", null, LocaleContextHolder.getLocale()), "Spooks");
        
        SpookInfoDTO spook = spookFacade.getSpookInfoById(id);
        if (spook == null) {
            throw new HttpNotFound("There is no spook listed by id: " + id);
        }
        
        model.addAttribute("spookEdit", spook);
        return "spook/edit";
    }
    
    @RequestMapping(value = { "/edit" }, method = RequestMethod.POST)
    public String editPost(@Valid @ModelAttribute("spookEdit") SpookDTO spook, BindingResult bindingResult, @RequestParam(value = "file", required = false) MultipartFile file, Model model, UriComponentsBuilder uriBuilder) throws IOException {
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().stream().forEach((fe) -> {
                model.addAttribute(fe.getField() + "_error", fe.getDefaultMessage());
            });
            return "spook/edit";
        }
        
        setImageFromFile(file, spook);
        if (spook.getId() == 0)
            spookFacade.createSpook(spook);
        else
            spookFacade.updateSpook(spook);
        
        return "redirect:" + uriBuilder.path("/spook/" + spook.getId()).build().toString();
    }
    
}
