/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.TestSuite.Facade;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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
import pa165.hauntedhouse.Dto.AbilityDTO;
import pa165.hauntedhouse.Dto.AbilityInfoDTO;
import pa165.hauntedhouse.Dto.SpookDTO;
import pa165.hauntedhouse.Entity.Ability;
import pa165.hauntedhouse.Entity.Spook;
import pa165.hauntedhouse.Facade.AbilityFacade;
import pa165.hauntedhouse.Facade.SpookFacade;
import pa165.hauntedhouse.Service.AbilityService;
import pa165.hauntedhouse.ServiceConfig.Service.BeanMappingService;
import pa165.hauntedhouse.ServiceConfig.ServiceConfiguration;

/**
 *
 * @author Andrej Dobes
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class AbilityFacadeTest extends AbstractTestNGSpringContextTests  {
    
    @Mock
    private AbilityDao abilityDao;
    
    @Mock
    private SpookDao spookDao;
    
    @InjectMocks
    @Autowired
    private AbilityService abilityService;
    
    @Autowired
    private AbilityFacade abilityFacade;
    
    @Autowired
    private BeanMappingService mapper;
    
    @BeforeClass
    public void initClass() {
        MockitoAnnotations.initMocks(this);
    }
    
    private Ability ability;
    private Ability ability2;
    private Spook spook;
    private AbilityDTO abilityDto;
    private AbilityDTO abilityDto2;
    
    @BeforeMethod
    public void createData(){
        ability = new Ability();
        ability2 = new Ability();
        spook = new Spook();
        
        ability.setName("Why Mr. Anderson ?!");
        ability.setInfo("What?");
        ability.setVisible(true);
        
        ability2.setName("Why you still persist?");
        ability2.setInfo("Because tests are for n00bs!!!");
        ability2.setVisible(true);
        
        spook.setName("meno");
        spook.setVisible(true);
        spook.setHistory("historia");
        spook.setHauntsSince(Time.valueOf("15:14:13"));
        spook.setHauntsUntil(Time.valueOf("10:15:30"));
        
        abilityDto = mapper.mapTo(ability, AbilityDTO.class);
        abilityDto2 = mapper.mapTo(ability2, AbilityDTO.class);
    }
    
    @Test
    public void createTest() {
        abilityFacade.createAbility(abilityDto);
        verify(abilityDao).create(ability);
    }
    
    @Test
    public void deleteTest(){
        abilityFacade.deleteAbility(abilityDto.getId());
        verify(abilityDao).delete(ability.getId());
    }
    
    @Test
    public void findAbility(){
        List <Ability> abilities = new ArrayList<>();
        abilities.add(ability);
        when(abilityDao.findById(1)).thenReturn(ability);
        Assert.assertEquals(ability.getName(),abilityFacade.getAbilityById(1).getName());
    }
    
    @Test
    public void shouldFindAllAbilities(){
        List <Ability> abilities = new ArrayList<>();
        abilities.add(ability);
        abilities.add(ability2);
        when(abilityDao.findAll()).thenReturn(abilities);
        List <AbilityDTO> abilitiesDTO = abilityFacade.getAllAbilities();
        Assert.assertEquals(mapper.mapTo(abilities, AbilityDTO.class), abilitiesDTO);
        Assert.assertEquals(abilityService.findAll().size(), 2);
    }
    
    @Test
    public void testAssociation() {
        when(abilityDao.findById(1)).thenReturn(ability);
        when(spookDao.findById(1)).thenReturn(spook);
        ability.addSpook(spook);
        Assert.assertEquals(ability.getSpooks().size(), 1);
        Assert.assertEquals(spook.getAbilities().size(), 1);
        ability.remove(spook);
        Assert.assertEquals(ability.getSpooks().size(), 0);
    }
}
