/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.TestSuite;

import java.util.Calendar;
import java.sql.Date;
import java.sql.Time;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pa165.hauntedhouse.Dao.HouseDao;
import pa165.hauntedhouse.Dao.SpookDao;
import pa165.hauntedhouse.Entity.House;
import pa165.hauntedhouse.Entity.Spook;
import pa165.hauntedhouse.PersistenceConfig.PersistenceApplicationContext;

/**
 *
 * @author Lucie Smidova
 * 
 */
@ContextConfiguration(classes = PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class HouseTest extends AbstractTestNGSpringContextTests{
    
    @Autowired
    private SpookDao spookDao;
    
    @Autowired
    private HouseDao houseDao;
    
    private Date getTestDate() {
        Calendar cal = Calendar.getInstance();
        cal.set( Calendar.YEAR, 1989 );
        cal.set( Calendar.MONTH, Calendar.FEBRUARY );
        cal.set( Calendar.DATE, 14 );
    
        cal.set( Calendar.HOUR_OF_DAY, 17 );
        cal.set( Calendar.MINUTE, 20 );
        cal.set( Calendar.SECOND, 30 );
        cal.set( Calendar.MILLISECOND, 0 );
        
        return new Date(cal.getTime().getTime());
    }
    
    private Time getTime(int hour, int min, int sec) {
        Calendar cal = Calendar.getInstance();
        cal.set( Calendar.HOUR_OF_DAY, hour );
        cal.set( Calendar.MINUTE, min );
        cal.set( Calendar.SECOND, sec );
        cal.set( Calendar.MILLISECOND, 0 );
        
        return new Time(cal.getTime().getTime());
    }
   
    @BeforeClass
    public void createTestData() {
        Date date = getTestDate();
        Time timeSince = getTime(20, 45, 30);
        Time timeUntil = getTime(6, 30, 00);
        
        
        House h3 = new House();
        h3.setName("Pernikova chaloupka");
        h3.setAddress("Les");
        h3.setHauntedSince(date);
        h3.setHistory("Pernikova historie");
        h3.setVisible(true);
        
        Spook spook = new Spook();
        spook.setName("Skorobezhlavy Nick");
        spook.setHauntsSince(timeSince);
        spook.setHauntsUntil(timeUntil);
        spook.setHistory("Test history");
       
        spookDao.create(spook);
        
        h3.addSpook(spook);
        houseDao.create(h3);
        spookDao.update(spook); 
        
        House h = new House();
        h.setName("Chroptici chyse");
        h.setAddress("Prasinky");
        h.setHauntedSince(date);
        h.setHistory("Chroptici historie");
        h.setVisible(true);
        
        houseDao.create(h);
        
        House h2 = new House();
        h2.setName("Chaloupka na kuri nozce");
        h2.setAddress("Les");
        h2.setHauntedSince(date);
        h2.setHistory("Historie kuri nozky");
        h2.setVisible(true);
        
        houseDao.create(h2);
        
    }
    
    @Test
    public void testHouseData() {
        List<House> houses = houseDao.findAll();       
        int hSum = houses.size();
        
        House h = houseDao.findByName("Chroptici chyse");
        Assert.assertEquals(h.getName(), "Chroptici chyse");
        Assert.assertEquals(h.getAddress(), "Prasinky");
        Assert.assertEquals(h.getHistory(), "Chroptici historie");
        
        h = houseDao.findByName("Chaloupka na kuri nozce");
        h.setName("Panelak na kurnozce");
        houseDao.update(h);
        Assert.assertEquals(h.getName(), "Panelak na kurnozce");
        
        houseDao.delete(h.getId());
        houses = houseDao.findAll();
        Assert.assertEquals(houses.size(), hSum - 1);
    }
   
    @Test
    public void testHouseSpookAssosiation() {
        Date date = getTestDate();
        Set<Spook> spooks = houseDao.findByName("Pernikova chaloupka").getSpooks();
        Assert.assertEquals(spooks.size(), 1);
        
        House h = houseDao.findByName("Pernikova chaloupka");
        
        Spook s = null;
        Iterator iter = spooks.iterator();
        while (iter.hasNext()) {
            s = (Spook)iter.next();
            if ("Skorobezhlavy Nick".equals(s.getName())) {
                break;
            }
        }
        if (s == null) {
            throw new NullPointerException("cant be null");
        }
        
       Assert.assertEquals(s.getName(), "Skorobezhlavy Nick");
       Assert.assertEquals(s.getHistory(), "Test history");
       Assert.assertEquals(s.getHouse().getAddress(), "Les");
       Assert.assertEquals(s.getHouse().getName(), "Pernikova chaloupka");
       Assert.assertEquals(s.getHouse(), h);
    }
    
}