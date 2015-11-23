/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.TestSuite;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pa165.hauntedhouse.Dto.AbilityDTO;
import pa165.hauntedhouse.Dto.HouseDTO;
import pa165.hauntedhouse.Dto.SpookDTO;
import pa165.hauntedhouse.Entity.Ability;
import pa165.hauntedhouse.Entity.House;
import pa165.hauntedhouse.Entity.Spook;
import pa165.hauntedhouse.ServiceConfig.Service.BeanMappingService;
import pa165.hauntedhouse.ServiceConfig.ServiceConfiguration;

/**
 *
 * @author Andrej Dobes
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class BeanMappingServiceTest extends AbstractTestNGSpringContextTests
{
    @Autowired
    private BeanMappingService beanMappingService;
    
    private final List<Ability> abilities = new ArrayList<>();
    private final List<AbilityDTO> abilitiesDTO = new ArrayList<>();
    private final Spook spook = new Spook();
    private final SpookDTO spookDTO = new SpookDTO();
    private final House house = new House();
    private final HouseDTO houseDTO = new HouseDTO();
    
    @BeforeClass
    public void createData(){
        spook.setName("Test spook");
        spook.setHistory("None");
        
        house.setName("Test house");
        house.setAddress("Some adress");
        house.setHistory("None");
        
        Ability ability = new Ability();
        ability.setInfo("Test info");
        ability.setName("Test name");
        abilities.add(ability);
        
        ability = new Ability();
        ability.setInfo("Another info");
        ability.setName("Another name");
        abilities.add(ability);
        
        spookDTO.setName("Test spook");
        spookDTO.setHistory("None");
        
        houseDTO.setName("Test house");
        houseDTO.setAddress("Some adress");
        houseDTO.setHistory("None");
        
        AbilityDTO abilityDto = new AbilityDTO();
        abilityDto.setInfo("Test info");
        abilityDto.setName("Test name");
        abilitiesDTO.add(abilityDto);
        
        abilityDto = new AbilityDTO();
        abilityDto.setInfo("Another info");
        abilityDto.setName("Another name");
        abilitiesDTO.add(abilityDto);
    }
    
    @Test
    public void testAbilityMap(){
        List<AbilityDTO> cdtos = beanMappingService.mapTo(abilities, AbilityDTO.class);
        Assert.assertEquals(cdtos.size(), 2);
        
        AbilityDTO ability = cdtos.get(0);
        Assert.assertEquals(ability.getInfo(), "Test info");
        Assert.assertEquals(ability.getName(), "Test name");
        
        ability = cdtos.get(1);
        Assert.assertEquals(ability.getInfo(), "Another info");
        Assert.assertEquals(ability.getName(), "Another name");
    }
    
    @Test
    public void testSpookMap(){
        SpookDTO spookDto = beanMappingService.mapTo(spook, SpookDTO.class);
        
        Assert.assertEquals(spookDto.getHistory(), "None");
        Assert.assertEquals(spookDto.getName(), "Test spook");
    }
    
    @Test
    public void testHouseMap(){
        HouseDTO houseDto = beanMappingService.mapTo(house, HouseDTO.class);
        
        Assert.assertEquals(houseDto.getHistory(), "None");
        Assert.assertEquals(houseDto.getName(), "Test house");
        Assert.assertEquals(houseDto.getAddress(), "Some adress");
    }
    
    @Test
    public void testAbilityDTOMap(){
        List<Ability> objects = beanMappingService.mapTo(abilitiesDTO, Ability.class);
        Assert.assertEquals(objects.size(), 2);
        
        Ability ability = objects.get(0);
        Assert.assertEquals(ability.getInfo(), "Test info");
        Assert.assertEquals(ability.getName(), "Test name");
        
        ability = objects.get(1);
        Assert.assertEquals(ability.getInfo(), "Another info");
        Assert.assertEquals(ability.getName(), "Another name");
    }
    
    @Test
    public void testSpookDTOMap(){
        Spook spookie = beanMappingService.mapTo(spookDTO, Spook.class);
        
        Assert.assertEquals(spookie.getHistory(), "None");
        Assert.assertEquals(spookie.getName(), "Test spook");
    }
    
    @Test
    public void testHouseDTOMap(){
        House h = beanMappingService.mapTo(houseDTO, House.class);
        
        Assert.assertEquals(h.getHistory(), "None");
        Assert.assertEquals(h.getName(), "Test house");
        Assert.assertEquals(h.getAddress(), "Some adress");
    }
}
