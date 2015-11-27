/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.TestSuite.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pa165.hauntedhouse.Entity.History;
import pa165.hauntedhouse.Entity.Spook;
import pa165.hauntedhouse.Service.HistoryService;
import pa165.hauntedhouse.Service.SpookService;
import pa165.hauntedhouse.ServiceConfig.ServiceConfiguration;

/**
 *
 * @author Lucie Smidova
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class HistoryServiceTest extends AbstractTestNGSpringContextTests {
    
    @Autowired
    private HistoryService historyService;
    
    @Autowired
    private SpookService spookService;
        
    private final History h = new History();
    private final History h2 = new History();
    
    private final Spook s = new Spook();
    private final Spook s2 = new Spook();
    
    private Date getDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set( Calendar.HOUR_OF_DAY, 0 );
        cal.set( Calendar.MINUTE, 0 );
        cal.set( Calendar.SECOND, 0 );
        cal.set( Calendar.MILLISECOND, 0 );
        
        return new Date(cal.getTime().getTime());
    }
    
    private Time getTime(int hour, int min, int sec) {
        Calendar cal = Calendar.getInstance();
        cal.set( Calendar.HOUR_OF_DAY, hour );
        cal.set( Calendar.MINUTE, min );
        cal.set( Calendar.SECOND, sec );      
        
        return new Time(cal.getTime().getTime());
    }
    
    @BeforeClass
    public void initData() {
        Date d = getDate(2015, 10, 10);
        Date d2 = getDate(2015, 3, 4);
        
        Time time = getTime(4,3,2);
        s.setName("Spookie");
        s.setHistory("Foo");
        s.setHauntsSince(time);
        s.setHauntsUntil(time);
        s2.setName("Spookie 2");
        s2.setHistory("Foo 2");
        s2.setHauntsSince(time);
        s2.setHauntsUntil(time);
        
        spookService.create(s);
        spookService.create(s2);
        
        h.setHistoryDate(d);
        h.setInfo("1");
        h.setSpook(s);
        
        h2.setHistoryDate(d2);
        h2.setInfo("2");
        h2.setSpook(s2);
        
        historyService.createHistory(h);
        historyService.createHistory(h2);
    }
    
    @Test
    public void createTest(){
        Assert.assertEquals(historyService.findHistoryById(h.getID()), h);
        Assert.assertEquals(historyService.findHistoryById(h2.getID()), h2);
    }
        
    @Test
    public void updateTest() {
        h2.setInfo("nova ");
        historyService.updateHistory(h2);
        Assert.assertEquals(historyService.findHistoryById(h2.getID()).getInfo(), "nova ");
    }
    
    @Test
    public void testSearch() {
        Date d3 = getDate(2013, 10, 10);
        Date d4 = getDate(2015, 3, 5);
        List<History> num_h = historyService.searchHistoryByRange(d3, d4);
        Assert.assertEquals(num_h.size(), 1);
        Assert.assertTrue(num_h.get(0).getHistoryDate().before(d4));
        Assert.assertTrue(num_h.get(0).getHistoryDate().after(d3));
        
        List<History> hs = historyService.searchTopHistoryByInfo("h", 3);
        Assert.assertEquals(hs.size(), 2);
        Assert.assertTrue(hs.get(0).getInfo().contains("h"));
        
        hs = historyService.searchTopHistoryByInfo("h", 1);
        Assert.assertEquals(hs.size(), 1);
        Assert.assertTrue(hs.get(0).getInfo().contains("h"));
    }
    
    @Test
    public void deleteTest() {  
        History h3 = new History();
        Date d5 = getDate(2015, 10, 10);
        h3.setHistoryDate(d5);
        h3.setInfo("h3");
        
        historyService.createHistory(h3);
        int num_h = historyService.getAllHistories().size();
        historyService.deleteHistory(h3.getID());
        Assert.assertEquals(historyService.getAllHistories().size(), num_h-1);
        Assert.assertNull(historyService.findHistoryById(h3.getID()));
    }   
}
