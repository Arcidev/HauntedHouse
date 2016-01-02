/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Facade;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pa165.hauntedhouse.Dto.HistoryDTO;
import pa165.hauntedhouse.Dto.HistoryInfoDTO;
import pa165.hauntedhouse.Dto.SpookInfoDTO;
import pa165.hauntedhouse.Entity.History;
import pa165.hauntedhouse.Entity.Spook;
import pa165.hauntedhouse.Service.HistoryService;
import pa165.hauntedhouse.Service.SpookService;
import pa165.hauntedhouse.ServiceConfig.Service.BeanMappingService;

/**
 * 
 * 
 * @author Lucie Smidova
 */
@Service
@Transactional
public class HistoryFacadeImpl implements HistoryFacade {
    
    @Autowired
    private BeanMappingService beanMappingService;
    
    @Autowired
    private HistoryService historyService;
    
    @Autowired
    private SpookService spookService;
    

    @Override
    public void createHistory(HistoryDTO h) {
        h.setId(historyService.createHistory(beanMappingService.mapTo(h, History.class), h.getSpookId()));
    }
    
    @Override
    public void deleteHistory(int id) {
        historyService.deleteHistory(id);
    }

    @Override
    public void updateHistory(HistoryInfoDTO h) {
        historyService.updateHistory(beanMappingService.mapTo(h, History.class));
    }


    @Override
    public List<HistoryInfoDTO> getAllHistories() {
        return beanMappingService.mapTo(historyService.getAllHistories(), HistoryInfoDTO.class);
    }
    
    @Override
    public List<HistoryInfoDTO> getAllSpooksHistories(int spookId){
        Spook s = spookService.findById(spookId);
        return beanMappingService.mapTo(historyService.getAllSpooksHistories(s), HistoryInfoDTO.class);
    }

    @Override
    public HistoryDTO getHistoryById(int id) {
        History h = historyService.findHistoryById(id);
        if (h == null) {
            return null;
        }
        
        HistoryDTO hDTO = beanMappingService.mapTo(h, HistoryDTO.class);
        Spook s = h.getSpook();
        if (s != null) {
            hDTO.setSpookId(s.getId());
        }
        return hDTO;
    }

    @Override
    public SpookInfoDTO getSpookByHistoryId(int historyId) {
        return beanMappingService.mapTo(historyService.getSpookByHistoryId(historyId), SpookInfoDTO.class);
    }
    
    @Override
    public List<HistoryInfoDTO> searchHistoryByRange(java.util.Date Date1, java.util.Date Date2) {
        return beanMappingService.mapTo(historyService.searchHistoryByRange(Date1, Date2), HistoryInfoDTO.class);
    }
    
    @Override
    public List<HistoryInfoDTO> searchTopHistoryByInfo(String pattern, int top) {
        return beanMappingService.mapTo(historyService.searchTopHistoryByInfo(pattern, top) , HistoryInfoDTO.class);
    }
    
}
