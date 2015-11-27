/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pa165.hauntedhouse.TestSuite.Facade;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pa165.hauntedhouse.Dto.HouseDTO;
import pa165.hauntedhouse.Facade.HouseFacade;
import pa165.hauntedhouse.Facade.SpookFacade;
import pa165.hauntedhouse.ServiceConfig.ServiceConfiguration;

/**
 *
 * @author Milan
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class HouseFacadeTest extends AbstractTestNGSpringContextTests{
    
    @Autowired
    HouseFacade houseFacade;
    
    @Autowired
    SpookFacade spookFacade;
    
    private final HouseDTO house = new HouseDTO();
    private final HouseDTO house2 = new HouseDTO();
    
    Calendar cal = Calendar.getInstance();
    Date date = new Date(cal.getTime().getTime());
    
    @BeforeClass
    public void initData(){
        house.setName("House");
        house.setAddress("Serus");
        house.setHistory("HistoryFacadeTest1");
        house.setHauntedSince(date);
        
        house2.setName("House2");
        house2.setAddress("NAZDAR");
        house2.setHistory("HistoryFacadeTest2");
        house2.setHauntedSince(date);
        
        houseFacade.createHouse(house);
        houseFacade.createHouse(house2);
    }
    
//    @Test
//    public void createTest() {
//        Assert.assertEquals(houseFacade.getHouseById(house.getId()), house);
//        Assert.assertEquals(houseFacade.getHouseById(house2.getId()), house2);
//    }
    
    @Test
    public void updateTest() {
        house.setAddress("BB");
        houseFacade.updateHouse(house);
        
        Assert.assertEquals(houseFacade.getHouseById(house.getId()).getAddress(), "BB");
    }
    
    @Test
    public void deleteTest() {
        HouseDTO h = new HouseDTO();
        h.setName("House3");
        h.setAddress("PP");
        h.setHistory("HistoryFacadeTest3");
        h.setHauntedSince(date);
        
        houseFacade.createHouse(h);
        int houses = houseFacade.getAllHouses().size();
        houseFacade.deleteHouse(h.getId());
        Assert.assertEquals(houseFacade.getAllHouses().size(), houses- 1);
        Assert.assertNull(houseFacade.getHouseById(h.getId()));
    }    
}
