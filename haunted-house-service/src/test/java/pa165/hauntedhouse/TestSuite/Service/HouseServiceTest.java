/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pa165.hauntedhouse.TestSuite.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pa165.hauntedhouse.Entity.House;
import pa165.hauntedhouse.Service.HouseService;
import pa165.hauntedhouse.ServiceConfig.ServiceConfiguration;

/**
 *
 * @author Milan
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
public class HouseServiceTest extends AbstractTestNGSpringContextTests{
    
    @Autowired
    private HouseService houseService;
        
    private final House house = new House();
    private final House house2 = new House();
           
    @BeforeClass
    public void initData() {
        house.setAddress("KE");
        house.setName("building");
        house.setHistory("HistoryServiceTest");
        house.setVisible(true);
        
        house2.setAddress("BA");
        house2.setName("house2");
        house2.setHistory("HistoryServiceTest2");
        house2.setVisible(true);
        
        houseService.create(house);
        houseService.create(house2);
    }
    
    @Test
    public void createTest(){
        Assert.assertEquals(houseService.findById(house.getId()), house);
        Assert.assertEquals(houseService.findById(house2.getId()), house2);
    }
        
    @Test
    public void updateTest() {
        house.setAddress("NY");
        houseService.update(house);
        Assert.assertEquals(houseService.findById(house.getId()).getAddress(), "NY");
    }
    
    @Test
    public void testSearch() {
        List<House> buildings = houseService.searchHousesByName("house", true);
        Assert.assertEquals(buildings.size(), 1);
        Assert.assertTrue(buildings.get(0).getName().contains("house"));
    }
    
    @Test
    public void deleteTest() {  
        House house3 = new House();
        house3.setAddress("LA");
        house3.setName("Name");
        house3.setHistory("HistoryServiceTest3");
        house3.setVisible(true);
        
        houseService.create(house3);
        int houses = houseService.findAll().size();
        houseService.delete(house3.getId());
        Assert.assertEquals(houseService.findAll().size(), houses-1);
        Assert.assertNull(houseService.findById(house3.getId()));
    }   
}
