/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.MVC.Controllers;

import java.io.IOException;
import javax.activation.MimetypesFileTypeMap;
import org.springframework.web.multipart.MultipartFile;
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
}
