/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Facade;

import java.util.List;
import pa165.hauntedhouse.Dto.HistoryDTO;
import pa165.hauntedhouse.Dto.SpookDTO;

/**
 *
 * @author Lucie Smidova
 */
public interface HistoryFacade {

    /**
     * Creates new history
     * 
     * @param h
     * @return id of newly created history
     */
    int createHistory(HistoryDTO h);

    /**
     * Removes existing history by id
     * 
     * @param h
     */
    void deleteHistory(HistoryDTO h);

    /**
     * Updates existing history
     * 
     * @param h
     */
    void updateHistory(HistoryDTO h);

    /**
     * Adds history to spook
     * 
     * @param h
     * @param s
     */
  //  void addToSpook(HistoryDTO h, SpookDTO s);

    /**
     * Removes history from spook
     * 
     * @param h
     * @param s
     */
 //   void removeFromSpook(HistoryDTO h, SpookDTO s);

    /**
     * Gets all histories
     * 
     * @return all histories
     */
    List<HistoryDTO> getAllHistories();

    /**
     * Gets history by id
     * 
     * @param id
     * @return history by id
     */
    HistoryDTO getHistoryById(int id);

    /**
     * Gets spook history
     * 
     * @param spookId
     * @return spook history
     */
    List<HistoryDTO> getSpookHistory(int spookId);
    
    /**
     * Finds history in date range
     * 
     * @param Date1
     * @param Date2
     * @return history in date1 and date2
     */
    List<HistoryDTO> searchHistoryByRange(java.util.Date Date1, java.util.Date Date2);
}
