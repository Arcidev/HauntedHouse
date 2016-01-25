/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pa165.hauntedhouse.TestSuite.Facade;

import java.sql.Time;
import java.util.ArrayList;
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
import pa165.hauntedhouse.Dao.HouseDao;
import pa165.hauntedhouse.Dao.SpookDao;
import pa165.hauntedhouse.Dto.HouseDTO;
import pa165.hauntedhouse.Entity.House;
import pa165.hauntedhouse.Entity.Spook;
import pa165.hauntedhouse.Facade.HouseFacade;
import pa165.hauntedhouse.Service.HouseService;
import pa165.hauntedhouse.ServiceConfig.Service.BeanMappingService;
import pa165.hauntedhouse.ServiceConfig.ServiceConfiguration;

/**
 *
 * @author Milan
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class HouseFacadeTest extends AbstractTestNGSpringContextTests{
    
    @Mock
    private HouseDao houseDao;
    
    @Mock
    private SpookDao spookDao;
    
    @InjectMocks
    @Autowired
    private HouseService houseService;
        
    @Autowired
    private HouseFacade houseFacade;
        
    @Autowired
    private BeanMappingService mapper;
    
    @BeforeClass
    public void initClass() {
        MockitoAnnotations.initMocks(this);
    }
   
    private House house;
    private House house2;
    private Spook spook;
    private HouseDTO houseDto;
    
    @BeforeMethod
    public void initData(){
        house = new House();
        house2 = new House();
        spook = new Spook();
        
        house.setName("dom");
        house.setAddress("serus");
        house.setHistory("historyfacadetest1");
        house.setVisible(true);
        
        house2.setName("strom");
        house2.setAddress("nazdar");
        house2.setHistory("historyfacadetest2");
        house2.setVisible(true);
        
        spook.setName("meno");
        spook.setVisible(true);
        spook.setHistory("historia");
        spook.setHauntsSince(Time.valueOf("15:14:13"));
        spook.setHauntsUntil(Time.valueOf("10:15:30"));
        
        houseDto = mapper.mapTo(house, HouseDTO.class);
    }
    
    @Test
    public void createTest() {
        houseFacade.createHouse(houseDto);
        verify(houseDao).create(house);
    }
            
    @Test
    public void deleteTest(){
        houseFacade.deleteHouse(houseDto.getId());
        verify(houseDao).delete(house.getId());
    }
        
    @Test
    public void findHouse(){
        when(houseDao.findById(1)).thenReturn(house);
        Assert.assertEquals(house.getName(),houseFacade.getHouseById(1).getName());
    }
        
    @Test
    public void shouldFindAllHouses(){
        List<House> houses = new ArrayList<>();
        houses.add(house);
        houses.add(house2);
        when(houseDao.findAll()).thenReturn(houses);
        List <HouseDTO> housesDTO = houseFacade.getAllHouses();
        Assert.assertEquals(mapper.mapTo(houses, HouseDTO.class), housesDTO);
        Assert.assertEquals(houseService.findAll().size(), 2);
    }
    
    @Test
    public void testAddRemoveSpook(){
        when(houseDao.findById(1)).thenReturn(house);
        when(spookDao.findById(1)).thenReturn(spook);
        house.addSpook(spook);
        Assert.assertEquals(house.getSpooks().size(), 1);
        Assert.assertEquals(spook.getHouse().getName(), house.getName());
        house.removeSpook(spook);
        Assert.assertEquals(house.getSpooks().size(), 0);
    }
}
