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
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pa165.hauntedhouse.Exception.AccessDenied;
import pa165.hauntedhouse.Exception.HttpNotFound;

/**
 *
 * @author Andrej Dobes
 */
@Controller
@ControllerAdvice
public class ErrorHandlerController extends BaseController {
    
    @Autowired
    private MessageSource messageSource; //resource bundle provided by Spring
    
    @RequestMapping(value = { "denied" }, method = RequestMethod.GET)
    public String accessDenied(Model model) {
        inicializeCall(model, null, null);
        model.addAttribute("errorTitle", messageSource.getMessage("error.deniedTitle", null, LocaleContextHolder.getLocale()));
        model.addAttribute("errorMessage", messageSource.getMessage("error.deniedMessage", null, LocaleContextHolder.getLocale()));
        return "error";
    }
    
    @ExceptionHandler(AccessDenied.class)
    public ModelAndView handleAccessDeniedException(AccessDenied ex) {
        ModelAndView model = new ModelAndView("error");
        model.addObject("errorTitle", messageSource.getMessage("error.deniedTitle", null, LocaleContextHolder.getLocale()));
        model.addObject("errorMessage", messageSource.getMessage("error.deniedMessage", null, LocaleContextHolder.getLocale()));
        return model;
    }
    
    @ExceptionHandler(HttpNotFound.class)
    public ModelAndView handleHttpNotFound(HttpNotFound ex) {
        ModelAndView model = new ModelAndView("error");
        model.addObject("errorTitle", messageSource.getMessage("error.notFoundTitle", null, LocaleContextHolder.getLocale()));
        model.addObject("errorMessage", messageSource.getMessage("error.notFoundMessage", null, LocaleContextHolder.getLocale()));
        return model;
    }
    
    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(Exception ex) {
        ModelAndView model = new ModelAndView("error");
        model.addObject("errorTitle", messageSource.getMessage("error.title", null, LocaleContextHolder.getLocale()));
        model.addObject("errorMessage", messageSource.getMessage("error.message", null, LocaleContextHolder.getLocale()));
        return model;
    }
}
