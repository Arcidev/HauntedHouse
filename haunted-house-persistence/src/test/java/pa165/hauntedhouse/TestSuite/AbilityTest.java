/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.TestSuite;
import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pa165.hauntedhouse.Dao.AbilityDao;
import pa165.hauntedhouse.Dao.SpookDao;
import pa165.hauntedhouse.Entity.Ability;
import pa165.hauntedhouse.Entity.Spook;
import pa165.hauntedhouse.PersistenceApplicationContext;

/**
 *
 * @author Martin Durcansky
 */
@ContextConfiguration(classes = PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
public class AbilityTest extends AbstractTestNGSpringContextTests{
    
    @Autowired
    private AbilityDao abilityDao;
    
    @Autowired
    private SpookDao spookDao;
    
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
        
        Ability ability = new Ability();
        ability.setName("Vydavanie zvukov");
        ability.setInfo("piskanie");        
        
        Spook spook = new Spook();
        spook.setName("Kasper");
        spook.setHauntsSince(timeSince);
        spook.setHauntsUntil(timeUntil);
        spook.setHistory("Test history");
        
        Spook spook2 = new Spook();
        spook2.setName("Spookie2");
        spook2.setHauntsSince(timeSince);
        spook2.setHauntsUntil(timeUntil);
        spook2.setHistory("Test history2");
        spookDao.create(spook);
        spookDao.create(spook2);        
        
        ability.addSpook(spook);
        ability.addSpook(spook2);
        abilityDao.create(ability);
        
        Ability ability2 = new Ability();
        ability2.setName("Kricanie");
        ability2.setInfo("zensky krik");
        abilityDao.create(ability2);
        spookDao.update(spook);
        spookDao.update(spook2);
    }
    
     @Test
    public void testAbilityData() {
        List<Ability> abilities = abilityDao.findAll();       
        int abilitiesCounts =abilities.size();
        
        Ability ability = abilityDao.findByName("Vydavanie zvukov");
        Assert.assertEquals(ability.getName(), "Vydavanie zvukov");
        Assert.assertEquals(ability.getInfo(), "piskanie");
        
        
        Ability ability2 = abilityDao.findByName("Kricanie");
        ability2.setName("Hulakanie");
        abilityDao.update(ability2);
        Assert.assertEquals(ability2.getName(), "Hulakanie");
        
        abilityDao.delete(ability2);
        abilities = abilityDao.findAll();
        Assert.assertEquals(abilities.size(), abilitiesCounts - 1);
    }
    
    @Test
    public void testAbilitySpookAssosiation() {
        Set<Spook> spooks = abilityDao.findByName("Vydavanie zvukov").getSpooks();
        Assert.assertEquals(spooks.size(), 2);
        
        Spook spook = null;
        Iterator iter = spooks.iterator();
        while (iter.hasNext()) {
            spook = (Spook)iter.next();
            if ("Kasper".equals(spook.getName())) {
                break;
            }
        }
        
        if (spook == null) {
            throw new NullPointerException("Ability in list of Spookie's abilities cannot be null");
        }
        Assert.assertEquals(spook.getName(), "Kasper");
        Assert.assertEquals(spook.getHistory(), "Test history");
        Assert.assertEquals(spook.getAbilities().size(), 1);
    }
    
}