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
import pa165.hauntedhouse.Dto.HistoryDTO;
import pa165.hauntedhouse.Facade.HistoryFacade;
import pa165.hauntedhouse.ServiceConfig.ServiceConfiguration;

/**
 *
 * @author Lucie Smidova
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class HistoryFacadeTest extends AbstractTestNGSpringContextTests {
    
    @Autowired
    HistoryFacade historyFacade;
    
    
    private final HistoryDTO hDTO = new HistoryDTO();
    private final HistoryDTO hDTO2 = new HistoryDTO();
    
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
    
    @BeforeClass
    public void createData(){
        Date d = getDate(2015, 12, 19);
        hDTO.setHistoryDate(d);
        hDTO.setInfo("h info");
        
        Date d2 = getDate(2015, 12, 13);
        hDTO2.setHistoryDate(d2);
        hDTO2.setInfo("h info2");
        historyFacade.createHistory(hDTO);
        historyFacade.createHistory(hDTO2);
    }
    
    @Test
    public void testDataCreation() {
        Assert.assertEquals(historyFacade.getHistoryById(hDTO.getID()), hDTO);
        Assert.assertEquals(historyFacade.getHistoryById(hDTO2.getID()), hDTO2);
    }
    
    @Test
    public void updateTest() {
        hDTO.setInfo("hu hu");
        historyFacade.updateHistory(hDTO);
        
        Date d2 = getDate(2015, 10, 13);
        hDTO2.setHistoryDate(d2);
        historyFacade.updateHistory(hDTO2);
        
        Assert.assertEquals(historyFacade.getHistoryById(hDTO.getID()).getInfo(), "hu hu");
        Assert.assertEquals((Date) historyFacade.getHistoryById(hDTO2.getID()).getHistoryDate(), d2);
    }
    
    @Test
    public void searchTest() {
        Date d3 = getDate(2015, 9, 13);
        Date d4 = getDate(2016, 12, 19);
        List <HistoryDTO> h = historyFacade.searchHistoryByRange(d3, d4);
        Assert.assertEquals(h.size(), 2);
        Assert.assertTrue(h.get(0).getHistoryDate().before(d4));
        Assert.assertTrue(h.get(0).getHistoryDate().after(d3));
        
        Assert.assertTrue(h.get(1).getHistoryDate().before(d4));
        Assert.assertTrue(h.get(1).getHistoryDate().after(d3));
        
        List <HistoryDTO> hs = historyFacade.searchHistoryByInfo("h");
        Assert.assertEquals(h.size(), 2);
        Assert.assertTrue(h.get(0).getInfo().contains("h"));
        
    }
    
    @Test
    public void deleteTest() {
        HistoryDTO hDTO3 = new HistoryDTO();
        Date d5 = getDate(1995, 9, 13);
        
        hDTO3.setHistoryDate(d5);
        hDTO3.setInfo("h info3");
        
        historyFacade.createHistory(hDTO3);
        int num_history = historyFacade.getAllHistories().size();
        historyFacade.deleteHistory(hDTO3.getID());
        Assert.assertEquals(historyFacade.getAllHistories().size(), num_history-1);
        Assert.assertNull(historyFacade.getHistoryById(hDTO3.getID()));
    }    
}
