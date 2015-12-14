/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.SampleData;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pa165.hauntedhouse.Entity.Ability;
import pa165.hauntedhouse.Service.AbilityService;

/**
 *
 * @author Andrej
 */
@Component
@Transactional
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {
    
    public static final String JPEG = "image/jpeg";
    public static final String ABILITIES_FOLDER = "abilities/";
    
    @Autowired
    private AbilityService abilityService;
    
    @Override
    @SuppressWarnings("unused")
    public void loadData() throws IOException {
        ability("Fear",                                             // name
            "Strikes fear in close creatures, disorienting them",   // info
            ABILITIES_FOLDER + "fear.jpg",                          // imageFile
            JPEG);                                                  // mimeType
        
        ability("Invisibility",                                     // name
            "Turns the spook invisible",                            // info
            ABILITIES_FOLDER + "invisibility.jpg",                  // imageFile
            JPEG);                                                  // mimeType
        
        ability("Possession",                                       // name
            "Possess the target, inhabiting their body",            // info
            ABILITIES_FOLDER + "fear.jpg",                          // imageFile
            JPEG);                                                  // mimeType
        
        ability("Polymorph",                                        // name
            "Transforms the spook into an animal",                  // info
            ABILITIES_FOLDER + "polymorph.jpg",                     // imageFile
            JPEG);                                                  // mimeType
    }
    
    private Ability ability(String name, String info, String imageFile, String mimeType) throws IOException {
        Ability a = new Ability();
        a.setName(name);
        a.setInfo(info);

        a.setImage(readImage(imageFile));
        a.setImageMimeType(mimeType);
        abilityService.create(a);
        return a;
    }
    
    private byte[] readImage(String file) throws IOException {
        try (InputStream is = this.getClass().getResourceAsStream("/" + file)) {
            int nRead;
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            byte[] data = new byte[1024];
            while ((nRead = is.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            buffer.flush();
            return buffer.toByteArray();
        }
    }
}
