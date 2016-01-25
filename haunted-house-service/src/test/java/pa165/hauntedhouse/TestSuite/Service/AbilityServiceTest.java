/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.TestSuite.Service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pa165.hauntedhouse.Dao.AbilityDao;
import pa165.hauntedhouse.Dao.SpookDao;
import pa165.hauntedhouse.Entity.Ability;
import pa165.hauntedhouse.Entity.Spook;
import pa165.hauntedhouse.Service.AbilityService;
import pa165.hauntedhouse.ServiceConfig.ServiceConfiguration;

/**
 *
 * @author Andrej Dobes
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class AbilityServiceTest extends AbstractTestNGSpringContextTests {
    
    @Mock
    private AbilityDao abilityDao;
    
    @Mock
    private SpookDao spookDao;
        
    @Autowired
    @InjectMocks
    private AbilityService abilityService;
    
    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }
    
    private Ability ability;
    private Ability ability2;
    private Spook spook;
    
    @BeforeMethod
    public void initData() {
        ability = new Ability();
        ability2 = new Ability();
        spook = new Spook();
        
        ability.setInfo("lol");
        ability.setName("troll");
        ability.setVisible(true);
        
        ability2.setInfo("lololo");
        ability2.setName("trololo");
        ability2.setVisible(true);
        
        Calendar cal = Calendar.getInstance();
        Time time = new Time(cal.getTime().getTime());
        spook.setName("Spookie");
        spook.setHistory("Foo");
        spook.setHauntsSince(time);
        spook.setHauntsUntil(time);
        spook.setVisible(true);
    }
    
    @Test
    public void createTest(){
        abilityService.create(ability);
        verify(abilityDao).create(ability);
    }
    
    @Test
    public void findById(){
        int id = 1;
        int id2 = 2;        
        when(abilityDao.findById(id)).thenReturn(ability);
        Assert.assertEquals(abilityService.findById(id), ability);
        
        when(abilityDao.findById(id2)).thenReturn(ability2);
        Assert.assertEquals(abilityService.findById(id2), ability2);
    }
                
    @Test
    public void deleteTest() {  
        abilityService.delete(ability.getId());
        verify(abilityDao).delete(ability.getId());
    }   
        
    @Test
    public void shouldGetAllAbilities()  {
        List<Ability> abilities = new ArrayList<>();
        abilities.add(ability);
        abilities.add(ability2);

        when(abilityDao.findAll()).thenReturn(abilities);
        Assert.assertEquals(abilityService.findAll(),abilities);
    }
    
    @Test
    public void testAssociation() {
        when(abilityDao.findById(1)).thenReturn(ability);
        when(spookDao.findById(1)).thenReturn(spook);
        abilityService.addToSpook(1, 1);
        int spooks = abilityService.getSpooksByAbilityId(1).size();
        Assert.assertEquals(spooks, 1);            
    } 
}
