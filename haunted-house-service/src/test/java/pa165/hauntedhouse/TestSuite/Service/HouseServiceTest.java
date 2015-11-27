/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pa165.hauntedhouse.TestSuite.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
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
    
    @Autowired
    private HouseService houseService;
    
    @Autowired
    private SpookService spookService;
    
    private final House house = new House();
    private final House house2 = new House();
    private final Spook spook = new Spook();
    private final Spook spook2 = new Spook();
    
    Calendar cal = Calendar.getInstance();
    Date date = new Date(cal.getTime().getTime());
    Time time = new Time(cal.getTime().getTime());
       
    @BeforeClass
    public void initData() {
        house.setAddress("KE");
        house.setName("House");
        house.setHistory("HistoryServiceTest");
        house.setHauntedSince(date);
        
        house2.setAddress("BA");
        house2.setName("House2");
        house2.setHistory("HistoryServiceTest2");
        house2.setHauntedSince(date);
        
//        spook.setName("Spook");
//        spook.setHistory("History");
//        spook.setHauntsSince(time);
//        spook.setHauntsUntil(time);
//        
//        spook2.setName("Spook2");
//        spook2.setHistory("History2");
//        spook2.setHauntsSince(time);
//        spook2.setHauntsUntil(time);
        
        houseService.create(house);
        houseService.create(house2);
    }
    
//    @Test
//    public void createTest(){
//        Assert.assertEquals(houseService.findAll().size(), 2);
//        Assert.assertEquals(houseService.findById(house.getId()), house);
//        Assert.assertEquals(houseService.findById(house2.getId()), house2);
//    }
        
    @Test
    public void updateTest() {
        house.setAddress("NY");
        houseService.update(house);
        Assert.assertEquals(houseService.findById(house.getId()).getAddress(), "NY");
    }
    
    @Test
    public void deleteTest() {  
        
        House house3 = new House();
        house3.setAddress("LA");
        house3.setName("Name");
        house3.setHistory("HistoryServiceTest3");
        house3.setHauntedSince(date);
        
        houseService.create(house3);
        int houses = houseService.findAll().size();
        Assert.assertEquals(houseService.findAll().size(), houses);
        houseService.delete(house3.getId());
        Assert.assertEquals(houseService.findAll().size(), houses-1);
        Assert.assertNull(houseService.findById(house3.getId()));
    }  
}
