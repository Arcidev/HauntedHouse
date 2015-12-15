/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.MVC.Controllers;

import java.io.IOException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pa165.hauntedhouse.Dto.AbilityDTO;
import pa165.hauntedhouse.Dto.SpookDTO;
import pa165.hauntedhouse.Facade.AbilityFacade;
import pa165.hauntedhouse.Facade.SpookFacade;

/**
 *
 * @author Andrej Dobes
 */
@Controller
@RequestMapping("/webApi")
public class WebApi {
    @Autowired
    private AbilityFacade abilityFacade;
    
    @Autowired
    private SpookFacade spookFacade;
    
    @RequestMapping("/ability/{id}")
    public void abilityImage(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        AbilityDTO ability = abilityFacade.getAbilityById(id);
        setImage(ability.getImage(), ability.getImageMimeType(), request, response);
    }
    
    @RequestMapping("/spook/{id}")
    public void spookImage(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        SpookDTO spook = spookFacade.getSpookById(id);
        setImage(spook.getImage(), spook.getImageMimeType(), request, response);
    }
    
    private void setImage(byte[] image, String mimeType, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(image==null) {
            response.sendRedirect(request.getContextPath()+"/no-image.png");
        } else {
            response.setContentType(mimeType);
            ServletOutputStream out = response.getOutputStream();
            out.write(image);
            out.flush();
        }
    }
}
