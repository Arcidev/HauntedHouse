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
    
}
