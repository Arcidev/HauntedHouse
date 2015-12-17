/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Dao;

import java.util.List;
import pa165.hauntedhouse.Entity.History;

/**
 *
 * @author Lucie Smidova
 */
public interface HistoryDao {
    
     /**
     * Creates new history record
     * 
     * @param h
     */
    public void create(History h);
    
    /**
     * Deletes history record
     * 
     * @param h
     */
    public void delete(History h);
    
    /**
     * Updates history record
     * 
     * @param h
     */
    public void update(History h);
    
     /**
     * Returns a history record with specified id from database
     * 
     * @param id
     * @return the ability with specified id
     */
    public History findById(int id);
    
     /**
     * Returns a history record with specified date from database
     * 
     * @param Date
     * @return the history record with specified date
     */
    public History findByDate(java.util.Date Date);
    
    /**
     * Returns list of all history records that are stored in database
     * 
     * @return the list of all history records
     */
    public List<History> findAll();
    
    /**
     * Gets all histories
     * @param spookId
     * @return all records of spooks history
     */
    List<History> getAllSpooksHistories(int spookId);
    
    /**
     * Finds history in date range
     * 
     * @param Date1
     * @param Date2
     * @return history in date1 and date2
     */
    public List<History> searchByRange(java.util.Date Date1, java.util.Date Date2);
    
    /**
     * Finds history cointaining pattern in info
     * 
     * @param  top
     * @param pattern
     * @return list of exact number of histories containing pattern in info order by id
     */
    List<History> searchTopHistoryByInfo(String pattern, int top);
    
}
