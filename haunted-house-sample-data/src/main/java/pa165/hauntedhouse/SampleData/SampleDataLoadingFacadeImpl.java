/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.SampleData;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pa165.hauntedhouse.Entity.Ability;
import pa165.hauntedhouse.Entity.House;
import pa165.hauntedhouse.Entity.Person;
import pa165.hauntedhouse.Entity.Spook;
import pa165.hauntedhouse.Enums.UserRole;
import pa165.hauntedhouse.Service.AbilityService;
import pa165.hauntedhouse.Service.HouseService;
import pa165.hauntedhouse.Service.PersonService;
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
    public static final String HOUSES_FOLDER = "houses/";
    
    @Autowired
    private AbilityService abilityService;
    
    @Autowired
    private SpookService spookService;
    
    @Autowired
    private HouseService houseService;
    
    @Autowired
    private PersonService personService;
    
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
        
        House h1 = house("Sorotity House",                                      //name
            "Sorority Street, NY",                                              //address
            "House..................................",                          // history
            getDate(1896, 05, 02),                                              // hauntedSince                                                 // hauntsUntil
            HOUSES_FOLDER + "sorority.jpg",                                     // imageFile
            JPEG); 
        
        House h2 = house("Frat House",                                          //name
            "Haunt street, LA",                                                 //address
            "House2..................................",                         // history
            getDate(1967, 10, 07),                                              // hauntedSince                                                 // hauntsUntil
            HOUSES_FOLDER + "frat.jpg",                                         // imageFile
            JPEG); 
        
        person("John", "Newman", "newman@admin.com", "Heslo1234", UserRole.ADMIN);
        
        abilityService.addToSpook(a1.getId(), s1.getId());
        abilityService.addToSpook(a2.getId(), s1.getId());
        abilityService.addToSpook(a3.getId(), s1.getId());
        
        abilityService.addToSpook(a2.getId(), s2.getId());
        abilityService.addToSpook(a3.getId(), s2.getId());
        abilityService.addToSpook(a4.getId(), s2.getId());
        
        houseService.addToSpook(h1.getId(), s1.getId());
        houseService.addToSpook(h2.getId(), s2.getId());
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
    
    private Person person(String name, String lastName, String email, String password, UserRole role) {
        Person person = new Person();
        person.setFirstName(name);
        person.setLastName(lastName);
        person.setEmail(email);
        person.setUserRole(role);
        personService.create(person, password);
        return person;
    }
    
    private House house(String name, String address, String history, Date hauntedSince, String imageFile, String mimeType) throws IOException {
        House h = new House();
        h.setName(name);
        h.setAddress(address);
        h.setHistory(history);
        h.setHauntedSince(hauntedSince);
        h.setImage(readImage(imageFile));
        h.setImageMimeType(mimeType);
        houseService.create(h);
        return h;
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
    
    private Date getDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set( Calendar.YEAR, year );
        cal.set( Calendar.MONTH, month );
        cal.set( Calendar.DAY_OF_MONTH, day );        
        return new Date(cal.getTime().getTime());
    }
}
