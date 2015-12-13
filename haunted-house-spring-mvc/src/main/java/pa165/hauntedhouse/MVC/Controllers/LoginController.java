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
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Andrej Dobes
 */
@Controller
public class LoginController {
    
    @Autowired
    private MessageSource messageSource; //resource bundle provided by Spring
    
    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("title", messageSource.getMessage("navigation.login", null, LocaleContextHolder.getLocale()));
        model.addAttribute("activePage", "Login");
        
        return "login/login";
    }
    
    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("title", messageSource.getMessage("navigation.signUp", null, LocaleContextHolder.getLocale()));
        model.addAttribute("activePage", "SignUp");
        
        return "login/register";
    }
    
    @RequestMapping(value="/authenticate", method = RequestMethod.POST)
    public String authenticate(Model model, UriComponentsBuilder uriBuilder) {
        
        return "redirect:" + uriBuilder.path("/home").build().toString();
    }
}
