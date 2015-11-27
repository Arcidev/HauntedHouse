/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.TestSuite.Facade;

import java.sql.Date;
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
    
    private Date getTestDate() {
        Calendar cal = Calendar.getInstance();
        cal.set( Calendar.YEAR, 1989 );
        cal.set( Calendar.MONTH, Calendar.FEBRUARY );
        cal.set( Calendar.DATE, 14 );
    
        cal.set( Calendar.HOUR_OF_DAY, 17 );
        cal.set( Calendar.MINUTE, 20 );
        cal.set( Calendar.SECOND, 30 );
        cal.set( Calendar.MILLISECOND, 0 );
        
        return new Date(cal.getTime().getTime());
    }
    
    private Time getTime(int hour, int min, int sec) {
        Calendar cal = Calendar.getInstance();
        cal.set( Calendar.HOUR_OF_DAY, hour );
        cal.set( Calendar.MINUTE, min );
        cal.set( Calendar.SECOND, sec );
        cal.set( Calendar.MILLISECOND, 0 );
        
        return new Time(cal.getTime().getTime());
    }
    
    @BeforeClass
    public void createData(){
        Date date = getTestDate();
        Time timeSince = getTime(20, 45, 30);
        Time timeUntil = getTime(6, 30, 00);
        spook.setName("Casper");
        spook.setHauntsSince(timeSince);
        spook.setHauntsUntil(timeUntil);
        spook.setHistory("Casper history");
        
        spook2.setName("Casper2");
        spook2.setHauntsSince(timeSince);
        spook2.setHauntsUntil(timeUntil);
        spook2.setHistory("Casper history2");    
                
        spookFacade.createSpook(spook);
        spookFacade.createSpook(spook2);
    }
    @Test
    public void testDataCreation() {
        Assert.assertEquals(spookFacade.getSpookById(spook.getId()), spook);
        Assert.assertEquals(spookFacade.getSpookById(spook2.getId()), spook2);
    }
    @Test
    public void testAssociation() {      
        AbilityDTO ability = new AbilityDTO();
        AbilityDTO ability2 = new AbilityDTO();
        ability.setName("Why Mr. Anderson ?!");
        ability.setInfo("What?");
        
        ability2.setName("Why you still persist?");
        ability2.setInfo("Because tests are for n00bs!!!");
        
        abilityFacade.createAbility(ability);
        abilityFacade.createAbility(ability2);
        
        
        spookFacade.addToAbility(spook.getId(), ability.getId());
        spookFacade.addToAbility(spook2.getId(), ability.getId());
        Assert.assertEquals(spookFacade.getAbilitySpooks(ability.getId()).size(), 2);
        
        
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
        Assert.assertNull(spookFacade.getSpookById(s.getId()));
    }
    @Test
    public void testSearch() {
        List<SpookDTO> spooks = spookFacade.searchSpooksByName("Casper2");
        Assert.assertEquals(spooks.size(), 1);
        Assert.assertTrue(spooks.get(0).getName().contains("Casper2"));        
                
    }
    
    
}
