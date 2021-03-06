/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.MVC.Controllers;

import java.util.Collections;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;
import pa165.hauntedhouse.Dto.PersonCreateDTO;
import pa165.hauntedhouse.Enums.UserRole;
import pa165.hauntedhouse.Facade.PersonFacade;

/**
 *
 * @author Andrej Dobes
 */
@Controller
public class LoginController extends BaseController {
    
    @Autowired
    private MessageSource messageSource; //resource bundle provided by Spring
    
    @Autowired
    private PersonFacade personFacade;
    
    @RequestMapping(value="login", method = RequestMethod.GET)
    public String login(Model model) {
        inicializeCall(model, messageSource.getMessage("navigation.login", null, LocaleContextHolder.getLocale()), "Login");
        
        return "login/login";
    }
    
    @RequestMapping(value="register", method = RequestMethod.GET)
    public String register(Model model) {
        inicializeCall(model, messageSource.getMessage("navigation.signUp", null, LocaleContextHolder.getLocale()), "SignUp");
        model.addAttribute("userCreate", new PersonCreateDTO());
        
        return "login/register";
    }
    
    @RequestMapping(value="register", method = RequestMethod.POST)
    public String registerPost(@Valid @ModelAttribute("userCreate") PersonCreateDTO user, BindingResult bindingResult, Model model, UriComponentsBuilder uriBuilder) {
        if (bindingResult.hasErrors() || !user.getPasswordAgain().equals(user.getPassword())) {
            bindingResult.getFieldErrors().stream().forEach((fe) -> {
                model.addAttribute(fe.getField() + "_error", fe.getDefaultMessage());
            });
            
            if (!user.getPasswordAgain().equals(user.getPassword())) {
                model.addAttribute("passwordAgain_error", "passwords do not match");
            }
            return "login/register";
        }
        
        // check if user already exists
        if (personFacade.findPersonByEmail(user.getEmail()) != null) {
            model.addAttribute("email_error", "user already exists");
            return "login/register";
        }
        
        personFacade.create(user);
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(UserRole.USER.toString()))));
        return "redirect:" + uriBuilder.path("/home").build().toString();
    }
}
