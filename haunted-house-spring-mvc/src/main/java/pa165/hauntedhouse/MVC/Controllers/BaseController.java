/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.MVC.Controllers;

import java.io.IOException;
import javax.activation.MimetypesFileTypeMap;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import pa165.hauntedhouse.Enums.UserRole;
import pa165.hauntedhouse.Iterface.ImageContainer;

/**
 *
 * @author Andrej Dobes
 */
public abstract class BaseController {
    
    protected void setImageFromFile(MultipartFile file, ImageContainer imgContainer) throws IOException {
        if (file != null && file.getSize() > 0) {
            imgContainer.setImage(file.getBytes());
            imgContainer.setImageMimeType(new MimetypesFileTypeMap().getContentType(file.getName()));
        }
    }
    
    protected void inicializeCall(Model model, String title, String activePage) {
        model.addAttribute("title", title);
        model.addAttribute("activePage", activePage);
        
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null && authentication instanceof UsernamePasswordAuthenticationToken) {
            model.addAttribute("userName", authentication.getName());
            model.addAttribute("userRole", authentication.getAuthorities().isEmpty() ? UserRole.USER.toString() : authentication.getAuthorities().iterator().next());
        }
    }
}
