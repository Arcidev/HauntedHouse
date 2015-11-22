/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Facade;

import java.util.List;
import pa165.hauntedhouse.Dto.HistoryDTO;

/**
 *
 * @author Andrej Dobes
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
     * @param historyId
     */
    void deleteHistory(int historyId);

    /**
     * Updates existing history
     * 
     * @param h
     */
    void updateHistory(HistoryDTO h);

    /**
     * Adds history to spook
     * 
     * @param historyId
     * @param spookId
     */
    void addToSpook(int historyId, int spookId);

    /**
     * Removes history from spook
     * 
     * @param historyId
     * @param spookId
     */
    void removeFromSpook(int historyId, int spookId);

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
}
