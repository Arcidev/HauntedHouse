/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Facade;

import java.util.List;
import pa165.hauntedhouse.Dto.HistoryDTO;
import pa165.hauntedhouse.Dto.HistoryInfoDTO;
import pa165.hauntedhouse.Dto.SpookInfoDTO;

/**
 *
 * @author Lucie Smidova
 */
public interface HistoryFacade {

    /**
     * Creates new history
     * 
     * @param h
     * 
     */
    void createHistory(HistoryDTO h);

    /**
     * Removes existing history by id
     * 
     * @param id
     */
    void deleteHistory(int id);

    /**
     * Updates existing history
     * 
     * @param h
     */
    void updateHistory(HistoryInfoDTO h);

    /**
     * Gets spook connected to history
     * 
     * @param historyId
     * @return spook
     */
    SpookInfoDTO getSpookByHistoryId(int historyId);
    
    /**
     * Gets all histories
     * 
     * @return all histories
     */
    List<HistoryInfoDTO> getAllHistories();

    /**
     * Gets all histories
     * @param spookId
     * @return all records of spooks history
     */
    List<HistoryInfoDTO> getAllSpooksHistories(int spookId);
    
    /**
     * Gets history by id
     * 
     * @param id
     * @return history by id
     */
    HistoryDTO getHistoryById(int id);

    /**
     * Finds history in date range
     * 
     * @param Date1
     * @param Date2
     * @return history in date1 and date2
     */
    List<HistoryInfoDTO> searchHistoryByRange(java.util.Date Date1, java.util.Date Date2);
    
    /**
     * Finds history cointaining pattern in info
     * 
     * @param  top
     * @param pattern
     * @return list of exact number of histories containing pattern in info order by id
     */
    List<HistoryInfoDTO> searchTopHistoryByInfo(String pattern, int top);
}
