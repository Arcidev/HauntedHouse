/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.TestSuite.Service;

import java.sql.Date;
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
import pa165.hauntedhouse.Dao.HistoryDao;
import pa165.hauntedhouse.Dao.SpookDao;
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
    
    @Mock
    private HistoryDao historyDao;
    
    @Mock
    private SpookDao spookDao;
    
    @Autowired
    @InjectMocks
    private HistoryService historyService;
    
    @Autowired
    private SpookService spookService;
    
    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }
    
    private History h;
    private History h2;
    
    private Spook s;
    private Spook s2;
    
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
    
    @BeforeMethod
    public void initData() {
        Date d = getDate(2015, 10, 10);
        Date d2 = getDate(2015, 3, 4);
        s = new Spook();
        s2 = new Spook();
        h = new History();
        h2 = new History();
        
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
        
        h2.setHistoryDate(d2);
        h2.setInfo("2");
    }
    
    @Test
    public void createTest(){
        when(spookDao.findById(1)).thenReturn(s);
        historyService.createHistory(h, 1);
        verify(historyDao).create(h);
    }
    
    @Test
    public void findById(){
        int id = 1;
        int id2 = 2;        
        when(historyDao.findById(id)).thenReturn(h);
        Assert.assertEquals(historyService.findHistoryById(id), h);
        
        when(historyDao.findById(id2)).thenReturn(h2);
        Assert.assertEquals(historyService.findHistoryById(id2), h2);
    }
                
    @Test
    public void deleteTest() {  
        historyService.deleteHistory(h.getID());
        verify(historyDao).delete(h.getID());
    }   
        
    @Test
    public void shouldGetAllHistories()  {
        List<History> histories = new ArrayList<>();
        histories.add(h);
        histories.add(h2);

        when(historyDao.findAll()).thenReturn(histories);
        Assert.assertEquals(historyService.getAllHistories(),histories);
    }
    
    @Test
    public void testSpookHistory(){
        when(spookDao.findById(1)).thenReturn(s);
        when(historyDao.findById(1)).thenReturn(h);
        when(historyDao.findById(2)).thenReturn(h2);    
        historyService.createHistory(h, 1);
        historyService.createHistory(h2, 1);
        s.addHistory(h);
        s.addHistory(h2);
        Assert.assertEquals(s.getHistories().size(), 2);
    }  
}
