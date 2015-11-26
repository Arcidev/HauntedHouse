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
import pa165.hauntedhouse.Entity.Ability;
import pa165.hauntedhouse.Entity.Spook;
import pa165.hauntedhouse.Service.AbilityService;
import pa165.hauntedhouse.Service.SpookService;
import pa165.hauntedhouse.ServiceConfig.ServiceConfiguration;

/**
 *
 * @author Andrej
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class AbilityFacadeTest extends AbstractTestNGSpringContextTests {
    
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
        spook2.setName("Spookie 2");
        spook2.setHistory("Foo 2");
        spook2.setHauntsSince(time);
        spook2.setHauntsUntil(time);
        abilityService.create(ability);
        abilityService.create(ability2);
        spookService.create(spook);
        spookService.create(spook2);
    }
    
    @Test
    public void testCreation() {
        Assert.assertEquals(abilityService.findAll().size(), 2);
        Assert.assertEquals(abilityService.findById(ability.getId()), ability);
        Assert.assertEquals(abilityService.findById(ability2.getId()), ability2);
    }
    
    @Test
    public void testAssociation() {
        abilityService.addToSpook(ability.getId(), spook.getId());
        abilityService.addToSpook(ability2.getId(), spook.getId());
        Assert.assertEquals(spookService.getAbilitiesBySpookId(spook.getId()).size(), 2);
        
        abilityService.removeFromSpook(ability.getId(), spook.getId());
        Assert.assertEquals(spookService.getAbilitiesBySpookId(spook.getId()).size(), 1);
        
        abilityService.addToSpook(ability.getId(), spook2.getId());
        abilityService.addToSpook(ability2.getId(), spook2.getId());
        Assert.assertEquals(spookService.getAbilitiesBySpookId(spook2.getId()).size(), 2);
    }
    
    @Test
    public void testSearch() {
        List<Ability> abilities = abilityService.searchAbilitiesByName("lol");
        Assert.assertEquals(abilities.size(), 1);
        Assert.assertTrue(abilities.get(0).getName().contains("lol"));
        
        abilities = abilityService.searchAbilitiesByName("trol");
        Assert.assertEquals(abilities.size(), 2);
        for (Ability a : abilities) {
            Assert.assertTrue(a.getName().contains("trol"));
        }
    }
    
    @Test
    public void testUpdate() {
        ability.setInfo("New Info");
        abilityService.update(ability);
        
        Assert.assertEquals(abilityService.findById(ability.getId()).getInfo(), "New Info");
    }
    
    @Test
    public void testDelete() {
        Ability a = new Ability();
        a.setInfo("a");
        a.setName("b");
        
        abilityService.create(a);
        Assert.assertEquals(abilityService.findAll().size(), 3);
        abilityService.delete(a);
        Assert.assertEquals(abilityService.findAll().size(), 2);
        Assert.assertNull(abilityService.findById(a.getId()));
    }
}