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

/**
 *
 * @author Andrej Dobes
 */
@Controller
public class HomeController extends BaseController {
    
    @Autowired
    private MessageSource messageSource; //resource bundle provided by Spring
    
    @RequestMapping(value = { "", "/home" }, method = RequestMethod.GET)
    public String home(Model model) {
        inicializeCall(model, messageSource.getMessage("navigation.project", null, LocaleContextHolder.getLocale()), "Home");
        
        return "home";
    }
}
