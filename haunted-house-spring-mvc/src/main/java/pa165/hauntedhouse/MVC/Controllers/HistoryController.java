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
import pa165.hauntedhouse.Dto.HistoryDTO;
import pa165.hauntedhouse.Dto.AbilityInfoDTO;
import pa165.hauntedhouse.Dto.SpookDTO;
import pa165.hauntedhouse.Dto.SpookInfoDTO;
import pa165.hauntedhouse.Dao.SpookDao;
import pa165.hauntedhouse.Entity.Spook;
import pa165.hauntedhouse.Exception.HttpNotFound;
import pa165.hauntedhouse.Facade.HistoryFacade;
import pa165.hauntedhouse.Facade.SpookFacade;

/**
 *
 * @author Lucie Smidova
 */
@Controller
@RequestMapping("history")
public class HistoryController extends BaseController {
    
    @Autowired
    private MessageSource messageSource; //resource bundle provided by Spring
    
    @Autowired
    private HistoryFacade historyFacade;
    
    
    @RequestMapping(value = { "all/{id}" }, method = RequestMethod.GET)
    public String histories(@PathVariable int id, Model model, HttpServletRequest request, HttpServletResponse response) {
        inicializeCall(model, messageSource.getMessage("navigation.abilities", null, LocaleContextHolder.getLocale()), "Histories");
        model.addAttribute("histories", historyFacade.getAllSpooksHistories(id));
        
        return "history/all";
    }
    
    @RequestMapping(value = { "edit/{id}" }, method = RequestMethod.GET)
    public String editHistory(@PathVariable int id, Model model, HttpServletRequest request, HttpServletResponse response) {
        inicializeCall(model, messageSource.getMessage("navigation.abilities", null, LocaleContextHolder.getLocale()), "Histories");
        
        HistoryDTO h = historyFacade.getHistoryById(id);
        if (h == null) {
            throw new HttpNotFound("There is no history listed by id: " + id);
        }
        
        model.addAttribute("historyEdit", h);
        return "history/edit";
    }
    
}
