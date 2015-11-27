/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.TestSuite.Service;

import java.sql.Time;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
//import pa165.hauntedhouse.Dto.SpookDTO;
import pa165.hauntedhouse.Entity.Ability;
import pa165.hauntedhouse.Entity.Spook;
import pa165.hauntedhouse.Service.AbilityService;
import pa165.hauntedhouse.Service.SpookService;
import pa165.hauntedhouse.ServiceConfig.ServiceConfiguration;

/**
 *
 * @author Martin Durcansky
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class SpookServiceTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private AbilityService abilityService;
    
    @Autowired
    private SpookService spookService;
    
    private final Ability ability = new Ability();
    private final Ability ability2 = new Ability();
    private final Spook spook = new Spook();
    private final Spook spook2 = new Spook();
    
    @BeforeClass
    public void initData() {
        ability.setInfo("lol");
        ability.setName("troll");
        
        ability2.setInfo("lololo");
        ability2.setName("trololo");
        
        Calendar cal = Calendar.getInstance();
        Time time = new Time(cal.getTime().getTime());
        spook.setName("Spookie");
        spook.setHistory("Foo");
        spook.setHauntsSince(time);
        spook.setHauntsUntil(time);
        spook2.setName("Spookie 24");
        spook2.setHistory("Foo 2");
        spook2.setHauntsSince(time);
        spook2.setHauntsUntil(time);
        abilityService.create(ability);
        abilityService.create(ability2);
        spookService.create(spook);
        spookService.create(spook2);
    }
   
    
    @Test
    public void testAssociation() {
        spookService.addToAbility(spook.getId(), ability.getId());
        spookService.addToAbility(spook2.getId(), ability.getId());
        Assert.assertEquals(abilityService.getSpooksByAbilityId(ability.getId()).size(), 2);        
        
    }
    @Test
    public void testUpdate() {
        spook.setHistory("New history");
        spookService.update(spook);
        
        Assert.assertEquals(spookService.findById(spook.getId()).getHistory(), "New history");
    }
    @Test
    public void testDelete() {       
        Calendar cal = Calendar.getInstance();
        Time time = new Time(cal.getTime().getTime());
        Spook s = new Spook();
        s.setName("Casper9");
        s.setHauntsSince(time);
        s.setHauntsUntil(time);
        s.setHistory("Casper history9");     
        
        spookService.create(s);
        int spooksCount = spookService.findAll().size();
        spookService.delete(s.getId());
        Assert.assertEquals(spookService.findAll().size(), spooksCount - 1);
        
    }
    @Test
    public void testSearch() {
        List<Spook> spooks2 = spookService.searchSpooksByName("Spookie 24");
        Assert.assertEquals(spooks2.size(), 1);
        Assert.assertTrue(spooks2.get(0).getName().contains("Spookie 24"));        
        
    }
    
}
