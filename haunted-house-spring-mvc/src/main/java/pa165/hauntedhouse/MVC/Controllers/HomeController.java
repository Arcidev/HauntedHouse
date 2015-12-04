/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.MVC.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Andrej Dobes
 */
@Controller
@RequestMapping(value = {"", "/", "/home"})
public class HomeController {
    
    @Autowired
    private MessageSource messageSource; //resource bundle provided by Spring
    
    @RequestMapping(value = {"", "/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("title", "Haunted House");
        
        return "home/index";
    }
}
