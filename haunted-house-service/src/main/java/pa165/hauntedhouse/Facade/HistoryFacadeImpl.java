/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Facade;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import pa165.hauntedhouse.Dto.HistoryDTO;
import pa165.hauntedhouse.Entity.History;
import pa165.hauntedhouse.Service.HistoryService;
import pa165.hauntedhouse.ServiceConfig.Service.BeanMappingService;

/**
 *
 * @author Lucie Smidova
 */
public class HistoryFacadeImpl implements HistoryFacade {
    
    @Autowired
    private BeanMappingService beanMappingService;
    
    @Autowired
    private HistoryService historyService;
    

    @Override
    public int createHistory(HistoryDTO h) {
        return historyService.createHistory(beanMappingService.mapTo(h, History.class));
    }

    @Override
    public void deleteHistory(HistoryDTO h) {
        historyService.deleteHistory(beanMappingService.mapTo(h, History.class));
    }

    @Override
    public void updateHistory(HistoryDTO h) {
        historyService.updateHistory(beanMappingService.mapTo(h, History.class));
    }


    @Override
    public List<HistoryDTO> getAllHistories() {
        return beanMappingService.mapTo(historyService.getAllHistories(), HistoryDTO.class);
    }

    @Override
    public HistoryDTO getHistoryById(int id) {
        return beanMappingService.mapTo(historyService.findHistoryById(id) , HistoryDTO.class);
    }

    
    @Override
    public List<HistoryDTO> searchHistoryByRange(java.util.Date Date1, java.util.Date Date2) {
        return beanMappingService.mapTo(historyService.searchHistoryByRange(Date1, Date2), HistoryDTO.class);
    }
}
