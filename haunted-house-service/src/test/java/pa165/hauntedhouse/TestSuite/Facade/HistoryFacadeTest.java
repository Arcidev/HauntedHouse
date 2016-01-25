/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.TestSuite.Facade;

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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pa165.hauntedhouse.Dto.HistoryDTO;
import pa165.hauntedhouse.Dto.HistoryInfoDTO;
import pa165.hauntedhouse.Entity.History;
import pa165.hauntedhouse.Entity.Spook;
import pa165.hauntedhouse.Facade.HistoryFacade;
import pa165.hauntedhouse.Facade.HistoryFacadeImpl;
import pa165.hauntedhouse.Service.HistoryService;
import pa165.hauntedhouse.ServiceConfig.Service.BeanMappingService;
import pa165.hauntedhouse.ServiceConfig.ServiceConfiguration;

/**
 *
 * @author Lucie Smidova
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class HistoryFacadeTest extends AbstractTestNGSpringContextTests {
    
    @Mock
    private HistoryService hdto;
    
    @Mock
    private BeanMappingService beanMappingService;
    
    @InjectMocks
    HistoryFacade historyFacade;
    
    private History h;
    private HistoryDTO hDTO;
    private HistoryDTO hDTO2;
    
    private Spook spook;
    
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
    public void setup() throws ServiceException {
        historyFacade = new HistoryFacadeImpl();
        MockitoAnnotations.initMocks(this);
    }
    
    @BeforeMethod
    public void createData(){
        hDTO = new HistoryDTO();
        hDTO2 = new HistoryDTO();
        spook = new Spook();
        h = new History();
        spook.setHauntsSince(getTime(20, 15, 00));
        spook.setHauntsUntil(getTime(6, 30, 45));
        spook.setHistory("s");
        spook.setName("b");
        
        hDTO.setHistoryDate(getDate(2015, 12, 19));
        hDTO.setInfo("h info");
        hDTO.setSpookId(1);
        
        hDTO2.setHistoryDate(getDate(2015, 12, 13));
        hDTO2.setInfo("h info2");
        hDTO2.setSpookId(1);
        
        h.setHistoryDate(getDate(2015, 12, 19));
        h.setInfo("h info");
        h.setSpook(spook);
    }
    
    @Test
    public void testDataCreation() {
        when(beanMappingService.mapTo(hDTO, History.class)).thenReturn(h);
        historyFacade.createHistory(hDTO);
        int expected = hDTO.getId();
        verify(hdto).createHistory(h, 1);
        assertEquals(expected, h.getID());
    }
    
    @Test
    public void updateTest() {
        
        when(beanMappingService.mapTo(hDTO, History.class)).thenReturn(h);
        hDTO.setInfo("huhu");
        historyFacade.updateHistory(hDTO);
        String expected = hDTO.getInfo();
        verify(hdto).updateHistory(h);
        Assert.assertEquals(historyFacade.getHistoryById(hDTO.getId()), hdto.findHistoryById(h.getID()));
    }
    
    @Test
    public void searchTest() {        
        List<History> hs = new ArrayList<>();
        hs.add(h);
        when(hdto.getAllHistories()).thenReturn(hs);
        List <HistoryInfoDTO> hDTO = historyFacade.getAllHistories();
        Assert.assertEquals(beanMappingService.mapTo(hs, HistoryInfoDTO.class), hDTO); 
    }
    
    @Test
    public void deleteTest() {
        
        when(beanMappingService.mapTo(hDTO, History.class)).thenReturn(h);
        historyFacade.deleteHistory(hDTO.getId());
        verify(hdto).deleteHistory(h.getID());
        Assert.assertNull(historyFacade.getHistoryById(hDTO.getId()));
        Assert.assertEquals(historyFacade.getHistoryById(hDTO.getId()), hdto.findHistoryById(h.getID()));
    }    
}
