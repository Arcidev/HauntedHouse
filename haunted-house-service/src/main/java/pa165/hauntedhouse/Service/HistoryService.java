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
     * @param h
     */
    void deleteHistory(History h);

    /**
     * Updates existing history
     * 
     * @param h
     */
    void updateHistory(History h);

    /**
     * Adds history to spook
     * 
     * @param h
     * @param s
     */
 //   void addSpook(History h, Spook s);

    /**
     * Removes history from spook
     * 
     * @param h
     * @param s
     */
  //  void removeSpook(History h, Spook s);

    /**
     * Gets all histories
     * 
     * @return all histories
     */
    List<History> getAllHistories();

    /**
     * Gets history by id
     * 
     * @param id
     * @return history by id
     */
    History findHistoryById(int id);

    /**
     * Gets spook history
     * 
     * @param s
     * @return spook history
     */
  //  List<History> getSpookHistory(Spook s);
    
    /**
     * Finds history in date range
     * 
     * @param Date1
     * @param Date2
     * @return history in date1 and date2
     */
    List<History> searchHistoryByRange(java.util.Date Date1, java.util.Date Date2);
}
