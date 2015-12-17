/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Service;

import java.util.List;
import pa165.hauntedhouse.Entity.History;
import pa165.hauntedhouse.Entity.Spook;

/**
 *
 * @author Lucie Smidova
 */
public interface HistoryService {
    /**
     * Creates new history
     * 
     * @param h
     * @return id of newly created history
     */
    int createHistory(History h);

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
    void updateHistory(History h);

    /**
     * Gets all histories
     * 
     * @return all histories
     */
    List<History> getAllHistories();

    /**
     * Gets all histories
     * @param spookId
     * @return all records of spooks history
     */
    List<History> getAllSpooksHistories(int spookId);
    /**
     * Gets history by id
     * 
     * @param id
     * @return history by id
     */
    History findHistoryById(int id);

    /**
     * Gets spook connected to history
     * 
     * @param historyId
     * @return spook
     */
    public Spook getSpookByHistoryId(int historyId);
    
    /**
     * Finds history in date range
     * 
     * @param Date1
     * @param Date2
     * @return history in date1 and date2
     */
    List<History> searchHistoryByRange(java.util.Date Date1, java.util.Date Date2);
    
    /**
     * Finds history cointaining pattern in info
     * 
     * @param  top
     * @param pattern
     * @return list of exact number of histories containing pattern in info order by id
     */
    List<History> searchTopHistoryByInfo(String pattern, int top);
    
}
