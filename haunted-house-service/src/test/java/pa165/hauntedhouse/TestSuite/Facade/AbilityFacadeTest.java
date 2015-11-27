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
import pa165.hauntedhouse.Facade.AbilityFacade;
import pa165.hauntedhouse.Facade.SpookFacade;
import pa165.hauntedhouse.ServiceConfig.ServiceConfiguration;

/**
 *
 * @author Andrej Dobes
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class AbilityFacadeTest extends AbstractTestNGSpringContextTests  {
    
    @Autowired
    AbilityFacade abilityFacade;
    
    @Autowired
    SpookFacade spookFacade;
    
    private final AbilityDTO ability = new AbilityDTO();
    private final AbilityDTO ability2 = new AbilityDTO();
    
    @BeforeClass
    public void createData(){
        ability.setName("Why Mr. Anderson ?!");
        ability.setInfo("What?");
        
        ability2.setName("Why you still persist?");
        ability2.setInfo("Because tests are for n00bs!!!");
        
        abilityFacade.createAbility(ability);
        abilityFacade.createAbility(ability2);
    }
    
    @Test
    public void testDataCreation() {
        Assert.assertEquals(abilityFacade.getAbilityById(ability.getId()), ability);
        Assert.assertEquals(abilityFacade.getAbilityById(ability2.getId()), ability2);
    }
    
    @Test
    public void testAssociation() {
        Calendar cal = Calendar.getInstance();
        Time time = new Time(cal.getTime().getTime());
        SpookDTO spook = new SpookDTO();
        SpookDTO spook2 = new SpookDTO();
        spook.setHistory("I");
        spook.setName("Really");
        spook.setHauntsSince(time);
        spook.setHauntsUntil(time);
        spook2.setHistory("Hate");
        spook2.setName("Testing");
        spook2.setHauntsSince(time);
        spook2.setHauntsUntil(time);
        //spook.setId(spookFacade.createSpook(spook));
        //spook2.setId(spookFacade.createSpook(spook2));
        spookFacade.createSpook(spook);
        spookFacade.createSpook(spook2);
        
        
        abilityFacade.addToSpook(ability.getId(), spook.getId());
        abilityFacade.addToSpook(ability2.getId(), spook.getId());
        Assert.assertEquals(abilityFacade.getSpookAbilities(spook.getId()).size(), 2);
        
        abilityFacade.removeFromSpook(ability.getId(), spook.getId());
        Assert.assertEquals(abilityFacade.getSpookAbilities(spook.getId()).size(), 1);
        
        abilityFacade.addToSpook(ability.getId(), spook2.getId());
        abilityFacade.addToSpook(ability2.getId(), spook2.getId());
        Assert.assertEquals(abilityFacade.getSpookAbilities(spook2.getId()).size(), 2);
    }
    
    @Test
    public void testSearch() {
        List<AbilityDTO> abilities = abilityFacade.searchAbilitiesByName("persist");
        Assert.assertEquals(abilities.size(), 1);
        Assert.assertTrue(abilities.get(0).getName().contains("persist"));
        
        abilities = abilityFacade.searchAbilitiesByName("why"); // must be searching case insensitive
        Assert.assertEquals(abilities.size(), 2);
        for (AbilityDTO a : abilities) {
            Assert.assertTrue(a.getName().contains("Why")); // I don't believe that Java can handle case insensitive
        }
    }
    
    @Test
    public void testUpdate() {
        ability.setInfo("New Info");
        abilityFacade.updateAbility(ability);
        
        Assert.assertEquals(abilityFacade.getAbilityById(ability.getId()).getInfo(), "New Info");
    }
    
    @Test
    public void testDelete() {
        AbilityDTO a = new AbilityDTO();
        a.setInfo("a");
        a.setName("b");
        
        abilityFacade.createAbility(a);
        int abilitiesCount = abilityFacade.getAllAbilities().size();
        abilityFacade.deleteAbility(a.getId());
        Assert.assertEquals(abilityFacade.getAllAbilities().size(), abilitiesCount - 1);
        Assert.assertNull(abilityFacade.getAbilityById(a.getId()));
    }
}
