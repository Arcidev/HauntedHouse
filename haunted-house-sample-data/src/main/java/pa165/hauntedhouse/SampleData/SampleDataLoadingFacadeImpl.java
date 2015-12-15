/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.SampleData;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Time;
import java.util.Calendar;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pa165.hauntedhouse.Entity.Ability;
import pa165.hauntedhouse.Entity.Spook;
import pa165.hauntedhouse.Service.AbilityService;
import pa165.hauntedhouse.Service.SpookService;

/**
 *
 * @author Andrej Dobes
 */
@Component
@Transactional
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {
    
    public static final String JPEG = "image/jpeg";
    public static final String ABILITIES_FOLDER = "abilities/";
    public static final String SPOOKS_FOLDER = "spooks/";
    
    @Autowired
    private AbilityService abilityService;
    
    @Autowired
    private SpookService spookService;
    
    @Override
    @SuppressWarnings("unused")
    public void loadData() throws IOException {
        Ability a1 = ability("Fear",                                // name
            "Strikes fear in close creatures, disorienting them",   // info
            ABILITIES_FOLDER + "fear.jpg",                          // imageFile
            JPEG);                                                  // mimeType
        
        Ability a2 = ability("Invisibility",                        // name
            "Turns the spook invisible",                            // info
            ABILITIES_FOLDER + "invisibility.jpg",                  // imageFile
            JPEG);                                                  // mimeType
        
        Ability a3 = ability("Possession",                          // name
            "Possess the target, inhabiting their body",            // info
            ABILITIES_FOLDER + "fear.jpg",                          // imageFile
            JPEG);                                                  // mimeType
        
        Ability a4 = ability("Polymorph",                           // name
            "Transforms the spook into an animal",                  // info
            ABILITIES_FOLDER + "polymorph.jpg",                     // imageFile
            JPEG);                                                  // mimeType
        
        Spook s1 = spook("Ghost",                                       // name
            "Scary ghost that haunts everyone... Why? Because he can!", // history
            getTime(18, 30, 00),                                        // hauntsSince
            getTime(6, 45, 00),                                         // hauntsUntil
            SPOOKS_FOLDER + "ghost.jpg",                                // imageFile
            JPEG);                                                      // mimeType
        
        Spook s2 = spook("Vampire",                                             // name
            "Killed by humans for cannibalism... Now he haunts for eternity.",  // history
            getTime(21, 15, 00),                                                // hauntsSince
            getTime(5, 30, 00),                                                 // hauntsUntil
            SPOOKS_FOLDER + "vampire.jpg",                                      // imageFile
            JPEG);                                                              // mimeType
        
        abilityService.addToSpook(a1.getId(), s1.getId());
        abilityService.addToSpook(a2.getId(), s1.getId());
        abilityService.addToSpook(a3.getId(), s1.getId());
        
        abilityService.addToSpook(a2.getId(), s2.getId());
        abilityService.addToSpook(a3.getId(), s2.getId());
        abilityService.addToSpook(a4.getId(), s2.getId());
    }
    
    private Ability ability(String name, String info, String imageFile, String mimeType) throws IOException {
        Ability a = new Ability();
        a.setName(name);
        a.setInfo(info);
        a.setImage(readImage(imageFile));
        a.setImageMimeType(mimeType);
        a.setVisible(true);
        abilityService.create(a);
        return a;
    }
    
    private Spook spook(String name, String history, Time hauntsSince, Time hauntsUntil, String imageFile, String mimeType) throws IOException {
        Spook s = new Spook();
        s.setName(name);
        s.setHistory(history);
        s.setHauntsSince(hauntsSince);
        s.setHauntsUntil(hauntsUntil);
        s.setImage(readImage(imageFile));
        s.setImageMimeType(mimeType);
        spookService.create(s);
        return s;
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
    
    private Time getTime(int hours, int minutes, int seconds) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hours);
        cal.set(Calendar.MINUTE, minutes);
        cal.set(Calendar.SECOND, seconds);
        cal.set(Calendar.MILLISECOND, 0);
        
        return new Time(cal.getTime().getTime());
    }
}
