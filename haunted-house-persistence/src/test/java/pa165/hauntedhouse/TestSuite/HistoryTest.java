/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pa165.hauntedhouse.TestSuite;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.Assert;
import org.testng.annotations.Test;
import pa165.hauntedhouse.Dao.HistoryDao;
import pa165.hauntedhouse.Entity.History;
import pa165.hauntedhouse.Entity.Spook;
import pa165.hauntedhouse.PersistenceConfig.PersistenceApplicationContext;

/**
 *
 * @author Milan
 */
@ContextConfiguration(classes = PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class HistoryTest extends AbstractTestNGSpringContextTests{
    
    @Autowired
    private HistoryDao historyDao;  
    
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

    @Test
    public void haveToFindAll(){
        History history1 = new History();
        History history2 = new History();
        
        history1.setInfo("Info1");
        history1.setHistoryDate(getTestDate());
        history2.setInfo("Info2");
        history2.setHistoryDate(getTestDate());
        
        historyDao.create(history1);
        historyDao.create(history2);
        List<History> history = historyDao.findAll();
        
        Assert.assertEquals(history.size(), 2);
        Assert.assertTrue(history.contains(history1));
        Assert.assertTrue(history.contains(history2));
    }
    
    @Test(expectedExceptions = ConstraintViolationException.class)
    public void importWithoutDate() {
        History history = new History();
        history.setInfo("InfoInfo");
        historyDao.create(history);
    }
    
    @Test
    public void haveToFindByID() {
        History history1 = new History();
        history1.setInfo("Info1");
        history1.setHistoryDate(getTestDate());

        historyDao.create(history1);

        Assert.assertEquals(historyDao.findById(history1.getID()), history1);
    }
    
    @Test
    public void haveToDelete(){
        History history1 = new History();
        History history2 = new History();
        history1.setInfo("Info1");
        history1.setHistoryDate(getTestDate());
        history2.setInfo("Info2");
        history2.setHistoryDate(getTestDate());
        
        historyDao.create(history1);
        historyDao.create(history2);
        
        historyDao.delete(history2);
        
        List<History> history = historyDao.findAll();
        
        Assert.assertEquals(history.size(), 1);
        Assert.assertTrue(history.contains(history1));
    }
    
    @Test
    public void haveToUpdate(){        
        History history1 = new History();
        history1.setInfo("InfoInfo");
        history1.setHistoryDate(getTestDate());
        
        historyDao.create(history1);
        Assert.assertEquals(history1.getInfo(), "InfoInfo");
        history1.setInfo("Info2");
        historyDao.update(history1);
        Assert.assertEquals(history1.getInfo(), "Info2");  
    }
    
    @Test
    public void setSomeData(){
        History history1 = new History();
        Spook spook = new Spook();
        spook.setName("Spook1");
        
        history1.setSpook(spook);
        history1.setInfo("InfoInfo");
        history1.setHistoryDate(getTestDate());
        
        Assert.assertEquals(history1.getSpook().getName(), "Spook1");
        Assert.assertEquals(history1.getInfo(), "InfoInfo");
    }
    
}
