/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pa165.hauntedhouse.TestSuite;

import java.util.List;
import javax.transaction.Transactional;
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
import pa165.hauntedhouse.PersistenceApplicationContext;

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

    @Test
    public void haveToFindAll(){
        History history1 = new History();
        History history2 = new History();
        
        history1.setInfo("Info1");
        history2.setInfo("Info2");
        
        historyDao.create(history1);
        historyDao.create(history2);
        List<History> history = historyDao.findAll();
        
        Assert.assertEquals(history.size(), 2);
        Assert.assertTrue(history.contains(history1));
        Assert.assertTrue(history.contains(history2));
    }
    
    @Test
    public void haveToFindByID() {
        History history1 = new History();
        history1.setInfo("Info1");

        historyDao.create(history1);

        Assert.assertEquals(historyDao.findById(history1.getID()), history1);
    }
    
    @Test
    public void haveToDelete(){
        History history1 = new History();
        History history2 = new History();
        history1.setInfo("Info1");
        history2.setInfo("Info2");
        
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
        
        Assert.assertEquals(history1.getSpook().getName(), "Spook1");
        Assert.assertEquals(history1.getInfo(), "InfoInfo");
    }
    
}
