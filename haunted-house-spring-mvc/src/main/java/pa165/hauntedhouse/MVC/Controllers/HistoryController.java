/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.MVC.Controllers;

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
import org.springframework.web.util.UriComponentsBuilder;
import pa165.hauntedhouse.Dto.HistoryDTO;
import pa165.hauntedhouse.Exception.HttpNotFound;
import pa165.hauntedhouse.Facade.HistoryFacade;

/**
 *
 * @author Lucie Smidova
 */
@Controller
@RequestMapping("history")
public class HistoryController extends BaseController {
    
    @Autowired
    private MessageSource messageSource;
    
    @Autowired
    private HistoryFacade historyFacade;
    
    
    @RequestMapping(value = { "all/{id}" }, method = RequestMethod.GET)
    public String histories(@PathVariable int id, Model model) {
        inicializeCall(model, messageSource.getMessage("navigation.abilities", null, LocaleContextHolder.getLocale()), "Spooks");
        model.addAttribute("histories", historyFacade.getAllSpooksHistories(id));
        model.addAttribute("spookId", id);
        
        return "history/all";
    }
    
    @RequestMapping(value = { "edit/{id}" }, method = RequestMethod.GET)
    public String editHistory(@PathVariable int id, Model model) {
        inicializeCall(model, messageSource.getMessage("navigation.abilities", null, LocaleContextHolder.getLocale()), "Spooks");
        
        HistoryDTO h = historyFacade.getHistoryById(id);
        if (h == null) {
            throw new HttpNotFound("There is no history listed by id: " + id);
        }
        
        model.addAttribute("historyEdit", h);
        return "history/edit";
    }
    
    @RequestMapping(value = { "new/{spookId}" }, method = RequestMethod.GET)
    public String newHistory(@PathVariable int spookId, Model model) {
        inicializeCall(model, messageSource.getMessage("navigation.abilities", null, LocaleContextHolder.getLocale()), "Spooks");
        
        HistoryDTO h = new HistoryDTO();
        h.setSpookId(spookId);
        model.addAttribute("historyEdit", h);
        
        return "history/edit";
    }
    
    @RequestMapping(value = { "edit" }, method = RequestMethod.POST)
    public String editPost(@Valid @ModelAttribute("historyEdit") HistoryDTO history, BindingResult bindingResult, Model model, UriComponentsBuilder uriBuilder) {
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().stream().forEach((fe) -> {
                model.addAttribute(fe.getField() + "_error", fe.getDefaultMessage());
            });
            return "history/edit";
        }
        
        if (history.getId() == 0)
            historyFacade.createHistory(history);
        else
            historyFacade.updateHistory(history);
        
        return "redirect:" + uriBuilder.path("/history/all/" + history.getSpookId()).build().toString();
    }
    
    @RequestMapping(value = { "remove/{spookId}/{historyId}" }, method = RequestMethod.GET)
    public String removeHistory(@PathVariable("spookId") int spookId, @PathVariable("historyId") int historyId, UriComponentsBuilder uriBuilder) {
        historyFacade.deleteHistory(historyId);
        
        return "redirect:" + uriBuilder.path("/history/all/" + spookId).build().toString();
    }
}
