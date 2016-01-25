/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.TestSuite.Facade;

import java.sql.Time;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pa165.hauntedhouse.Dto.AbilityDTO;
import pa165.hauntedhouse.Dto.SpookDTO;
import pa165.hauntedhouse.Dto.SpookInfoDTO;
import pa165.hauntedhouse.Facade.AbilityFacade;
import pa165.hauntedhouse.Facade.SpookFacade;
import pa165.hauntedhouse.ServiceConfig.ServiceConfiguration;

/**
 *
 * @author Martin Durcansky
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class SpookFacadeTest extends AbstractTestNGSpringContextTests{
    
    @Autowired
    AbilityFacade abilityFacade;
    
    @Autowired
    SpookFacade spookFacade;
    private final SpookDTO spook = new SpookDTO();
    private final SpookDTO spook2 = new SpookDTO();    
    
    private Time getTime(int hour, int min, int sec) {
        Calendar cal = Calendar.getInstance();
        cal.set( Calendar.HOUR_OF_DAY, hour );
        cal.set( Calendar.MINUTE, min );
        cal.set( Calendar.SECOND, sec );      
        
        return new Time(cal.getTime().getTime());
    }
    
    @BeforeClass
    public void createData(){       
        Time timeSince = getTime(20, 45, 30);
        Time timeUntil = getTime(6, 30, 00);
        spook.setName("Casper");
        spook.setHauntsSince(timeSince);
        spook.setHauntsUntil(timeUntil);
        spook.setHistory("Casper history");
        spook.setVisible(true);
        
        spook2.setName("Casper2");
        spook2.setHauntsSince(timeSince);
        spook2.setHauntsUntil(timeUntil);
        spook2.setHistory("Casper history2"); 
        spook2.setVisible(true);
                
        spookFacade.createSpook(spook);
        spookFacade.createSpook(spook2);
    }
    
    @Test
    public void testUpdate() {
        spook.setHistory("Casper history8");
        spookFacade.updateSpook(spook);
        
        Assert.assertEquals(spookFacade.getSpookById(spook.getId()).getHistory(), "Casper history8");
    }
    
    @Test
    public void testDelete() {
        Time timeSince = getTime(20, 45, 30);
        Time timeUntil = getTime(6, 30, 00);
        SpookDTO s = new SpookDTO();
        s.setName("Casper9");
        s.setHauntsSince(timeSince);
        s.setHauntsUntil(timeUntil);
        s.setHistory("Casper history9");     
        
        spookFacade.createSpook(s);
        int spooksCount = spookFacade.getAllSpooks().size();
        spookFacade.deleteSpook(s.getId());
        Assert.assertEquals(spookFacade.getAllSpooks().size(), spooksCount - 1);
       
    }
    
    @Test
    public void testSearch() {
        List<SpookInfoDTO> spooks = spookFacade.searchSpooksByName("Casper2",true);
        Assert.assertEquals(spooks.size(), 1);
        Assert.assertTrue(spooks.get(0).getName().contains("Casper2"));        
                
    }
    
    
}
