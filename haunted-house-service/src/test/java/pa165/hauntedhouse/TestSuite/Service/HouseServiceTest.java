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
import pa165.hauntedhouse.Dao.HouseDao;
import pa165.hauntedhouse.Dao.SpookDao;
import pa165.hauntedhouse.Entity.House;
import pa165.hauntedhouse.Entity.Spook;
import pa165.hauntedhouse.Service.HouseService;
import pa165.hauntedhouse.Service.SpookService;
import pa165.hauntedhouse.ServiceConfig.ServiceConfiguration;

/**
 *
 * @author Milan
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
public class HouseServiceTest extends AbstractTestNGSpringContextTests{
  
    @Mock
    private HouseDao houseDao;
    
    @Mock
    private SpookDao spookDao;
    
    @InjectMocks
    @Autowired
    private HouseService houseService;
    
    @InjectMocks
    @Autowired
    private SpookService spookService;
        
    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }
        
    private House house;
    private House house2;
    private Spook spook;
           
    @BeforeMethod
    public void prepareTestData() {
        house = new House();
        house2 = new House();
        spook = new Spook();
        
        house.setAddress("KE");
        house.setName("buildingbuilding");
        house.setHistory("HistoryServiceTestHistoryServiceTest");
        house.setVisible(true);

        house2.setAddress("BA");
        house2.setName("house2");
        house2.setHistory("HistoryServiceTest2");
        house2.setVisible(true);

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
        houseService.create(house);
        verify(houseDao).create(house);
    }
            
    @Test
    public void findById(){
        int id = 1;
        int id2 = 2;        
        when(houseDao.findById(id)).thenReturn(house);
        Assert.assertEquals(houseService.findById(id), house);
        
        when(houseDao.findById(id2)).thenReturn(house2);
        Assert.assertEquals(houseService.findById(id2), house2);
    }
                
    @Test
    public void deleteTest() {  
        houseService.delete(house.getId());
        verify(houseDao).delete(house.getId());
    }   
        
    @Test
    public void shouldGetAllHouses()  {
        List<House> houses = new ArrayList<>();
        houses.add(house);
        houses.add(house2);

        when(houseDao.findAll()).thenReturn(houses);
        Assert.assertEquals(houseService.findAll(),houses);
    }
    
    @Test
    public void testAssociation() {
        when(houseDao.findById(1)).thenReturn(house);
        when(spookDao.findById(1)).thenReturn(spook);
        houseService.addToSpook(1, 1);
        int spooks = houseService.getSpooksByHouseId(1).size();
        Assert.assertEquals(spooks, 1);            
    } 
}