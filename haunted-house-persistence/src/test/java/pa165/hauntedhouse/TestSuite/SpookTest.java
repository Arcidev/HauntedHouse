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
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pa165.hauntedhouse.Dao.AbilityDao;
import pa165.hauntedhouse.Dao.HistoryDao;
import pa165.hauntedhouse.Dao.HouseDao;
import pa165.hauntedhouse.Dao.SpookDao;
import pa165.hauntedhouse.Entity.Ability;
import pa165.hauntedhouse.Entity.History;
import pa165.hauntedhouse.Entity.House;
import pa165.hauntedhouse.Entity.Spook;
import pa165.hauntedhouse.PersistenceApplicationContext;

/**
 *
 * @author Andrej Dobes
 */
@ContextConfiguration(classes = PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
public class SpookTest extends AbstractTestNGSpringContextTests {
    
    @Autowired
    private SpookDao spookDao;
    
    @Autowired
    private HouseDao houseDao;
    
    @Autowired
    private HistoryDao historyDao;
    
    @Autowired
    private AbilityDao abilityDao;
    
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
        
        Spook spook = new Spook();
        spook.setName("Spookie");
        spook.setHauntsSince(timeSince);
        spook.setHauntsUntil(timeUntil);
        spook.setHistory("Test history");
        
        House house = new House();
        house.setAdress("Spookie's House Adress");
        house.setHauntedSince(date);
        house.setName("Spookie's House");
        house.setHistory("Spookie's House History");
        houseDao.create(house);
        
        Ability ability = new Ability();
        ability.setName("Scare");
        ability.setInfo("Scares somenone");
        Ability ability2 = new Ability();
        ability2.setName("Sleep");
        ability2.setInfo("Brings somenone to sleep");
        abilityDao.create(ability);
        abilityDao.create(ability2);
        
        History history = new History();
        history.setInfo("history 1");
        History history2 = new History();
        history2.setInfo("history 2");
        historyDao.create(history);
        historyDao.create(history2);
        
        spook.addAbility(ability);
        spook.addAbility(ability2);
        
        spook.setHouse(house);
        
        spook.addHistory(history);
        spook.addHistory(history2);
        
        spookDao.create(spook);
        
        spook = new Spook();
        spook.setName("Freddy Krueger");
        spook.setHauntsSince(timeSince);
        spook.setHauntsUntil(timeUntil);
        spook.setHistory("He gets burned once");
        
        spookDao.create(spook);
    }
    
    @Test
    public void testSpookData() {
        List<Spook> spooks = spookDao.findAll();
        Assert.assertEquals(spooks.size(), 2);
        
        Spook spook = spookDao.findByName("Spookie");
        Assert.assertEquals(spook.getName(), "Spookie");
        Assert.assertEquals(spook.getHistory(), "Test history");
        Assert.assertEquals(spook.getHauntsSince().toString(), getTime(20, 45, 30).toString());
        Assert.assertEquals(spook.getHauntsUntil().toString(), getTime(6, 30, 00).toString());
        
        spook = spookDao.findByName("Freddy Krueger");
        spook.setName("Jason Voorhees");
        spookDao.update(spook);
        Assert.assertEquals(spook.getName(), "Jason Voorhees");
        
        spookDao.delete(spook);
        spooks = spookDao.findAll();
        Assert.assertEquals(spooks.size(), 1);
    }
    
    @Test
    public void testSpookHouseAssosiation() {
        House house = spookDao.findByName("Spookie").getHouse();
        Assert.assertEquals(house.getName(), "Spookie's House");
        Assert.assertEquals(house.getAdress(), "Spookie's House Adress");
        Assert.assertEquals(house.getHauntedSince().toString(), getTestDate().toString());
        Assert.assertEquals(house.getHistory(), "Spookie's House History");
        //Assert.assertEquals(house.getSpooks().size(), 1);
    }
    
    @Test
    public void testSpookAbilityAssosiation() {
        Set<Ability> abilities = spookDao.findByName("Spookie").getAbilities();
        Assert.assertEquals(abilities.size(), 2);
        
        Ability ability = null;
        Iterator iter = abilities.iterator();
        while (iter.hasNext()) {
            ability = (Ability)iter.next();
            if ("Scare".equals(ability.getName())) {
                break;
            }
        }
        
        if (ability == null) {
            throw new NullPointerException("Ability in list of Spookie's abilities cannot be null");
        }
        Assert.assertEquals(ability.getName(), "Scare");
        Assert.assertEquals(ability.getInfo(), "Scares somenone");
        Assert.assertEquals(ability.getSpooks().size(), 1);
    }
    
    @Test
    public void testSpookHistoryAssociation() {
        Set<History> histories = spookDao.findByName("Spookie").getHistories();
        Assert.assertEquals(histories.size(), 2);
        
        History history = null;
        Iterator iter = histories.iterator();
        while (iter.hasNext()) {
            history = (History)iter.next();
            if ("history 1".equals(history.getInfo())) {
                break;
            }
        }
        
        if (history == null) {
            throw new NullPointerException("History in list of Spookie's histories cannot be null");
        }
        Assert.assertEquals(history.getInfo(), "history 1");
        //Assert.assertEquals(history.getSpook().getName, "Spookie");
    }
}
