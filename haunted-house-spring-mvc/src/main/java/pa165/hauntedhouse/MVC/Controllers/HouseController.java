/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.MVC.Controllers;

import java.io.IOException;
import java.util.List;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;
import pa165.hauntedhouse.Dto.HouseDTO;
import pa165.hauntedhouse.Dto.HouseInfoDTO;
import pa165.hauntedhouse.Dto.SpookInfoDTO;
import pa165.hauntedhouse.Enums.UserRole;
import pa165.hauntedhouse.Exception.HttpNotFound;
import pa165.hauntedhouse.Facade.HouseFacade;
import pa165.hauntedhouse.Facade.SpookFacade;

/**
 *
 * @author Milan Matijka
 */
@Controller
@RequestMapping("/house")
public class HouseController extends BaseController{
    
    @Autowired
    private MessageSource messageSource; //resource bundle provided by Spring
    
    @Autowired
    private HouseFacade houseFacade;
    
    @Autowired
    private SpookFacade spookFacade;
    
    @RequestMapping(value = { "" }, method = RequestMethod.GET)
    public String houses(Model model) {
        inicializeCall(model, messageSource.getMessage("navigation.houses", null, LocaleContextHolder.getLocale()), "Houses");
        model.addAttribute("houses", houseFacade.getAllHouseInfoesByVisibility(true));
        if (UserRole.ADMIN.toString().equals(getUserRole())) {
            model.addAttribute("hiddenHouses", houseFacade.getAllHouseInfoesByVisibility(false));
        }
        
        return "house/all";
    }
    
    @RequestMapping(value = { "/{id}" }, method = RequestMethod.GET)
    public String house(@PathVariable int id, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        inicializeCall(model, messageSource.getMessage("navigation.houses", null, LocaleContextHolder.getLocale()), "Houses");
        
        HouseInfoDTO house = houseFacade.getHouseInfoById(id);
        if (house == null) {
            throw new HttpNotFound("There is no house listed by id: " + id);
        }
        
        List<SpookInfoDTO> spooks = spookFacade.getHouseSpookInfoes(house.getId());
        model.addAttribute("house", house);
        model.addAttribute("spooks", spookFacade.getHouseSpookInfoes(house.getId()));
        if (UserRole.ADMIN.toString().equals(getUserRole())) {
            List<SpookInfoDTO> notAssignedSpooks = spookFacade.getAllSpookInfoesByVisibility(true);
            notAssignedSpooks.removeAll(spooks);
            model.addAttribute("notAssignedSpooks", notAssignedSpooks);
        }
        return "house/view";
    }
    
    @RequestMapping(value = { "new" }, method = RequestMethod.GET)
    public String newHouse(Model model) {
        inicializeCall(model, messageSource.getMessage("navigation.houses", null, LocaleContextHolder.getLocale()), "Houses");
        model.addAttribute("houseEdit", new HouseInfoDTO());
        
        return "house/edit";
    }
    
    @RequestMapping(value = { "edit/{id}" }, method = RequestMethod.GET)
    public String editHouse(@PathVariable int id, Model model, HttpServletRequest request, HttpServletResponse response) {
        inicializeCall(model, messageSource.getMessage("navigation.houses", null, LocaleContextHolder.getLocale()), "Houses");
        
        HouseInfoDTO house = houseFacade.getHouseInfoById(id);
        if (house == null) {
            throw new HttpNotFound("There is no house listed by id: " + id);
        }
        
        model.addAttribute("houseEdit", house);
        return "house/edit";
    }
    
    @RequestMapping(value = {"delete/{deleteId}"}, method = RequestMethod.POST)
    public String deleteHouse(@PathVariable int deleteId,Model model, HttpServletRequest request, HttpServletResponse response){
        System.out.println("SOM VYPIS");
        inicializeCall(model, messageSource.getMessage("navigation.houses", null, LocaleContextHolder.getLocale()), "Houses");
        HouseDTO house = houseFacade.getHouseById(deleteId);
        
        houseFacade.deleteHouse(deleteId);
        model.addAttribute("houseDelete", house);
        
        return "house/all";
    }
    
    @RequestMapping(value = { "/edit" }, method = RequestMethod.POST)
    public String editPost(@Valid @ModelAttribute("houseEdit") HouseDTO house, BindingResult bindingResult, @RequestParam(value = "file", required = false) MultipartFile file, Model model, UriComponentsBuilder uriBuilder) throws IOException {
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().stream().forEach((fe) -> {
                model.addAttribute(fe.getField() + "_error", fe.getDefaultMessage());
            });
            return "house/edit";
        }
        
        setImageFromFile(file, house);
        if (house.getId() == 0)
            houseFacade.createHouse(house);
        else
            houseFacade.updateHouse(house);
        
        return "redirect:" + uriBuilder.path("/house/" + house.getId()).build().toString();
    }
    
    @RequestMapping(value = { "visible/{id}/{visible}" }, method = RequestMethod.GET)
    public String setVisible(@PathVariable int id, @PathVariable boolean visible, Model model, UriComponentsBuilder uriBuilder) {
        houseFacade.setVisible(id, visible);
        
        return "redirect:" + uriBuilder.path("/house/" + id).build().toString();
    }
    
    @RequestMapping(value = { "removeSpook/{houseId}/{spookId}" }, method = RequestMethod.GET)
    public String removeSpook(@PathVariable int houseId, @PathVariable int spookId, UriComponentsBuilder uriBuilder) {
        houseFacade.removeSpook(houseId, spookId);
        
        return "redirect:" + uriBuilder.path("/house/" + houseId).build().toString();
    }
    
    @RequestMapping(value = { "addSpook" }, method = RequestMethod.POST)
    public String addSpook(@RequestParam("houseId") int houseId, @RequestParam("spookId") int spookId, UriComponentsBuilder uriBuilder) {
        houseFacade.addSpook(houseId, spookId);
        
        return "redirect:" + uriBuilder.path("/house/" + houseId).build().toString();
    }
}
