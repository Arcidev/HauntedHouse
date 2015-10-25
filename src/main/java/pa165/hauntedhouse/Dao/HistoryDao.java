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
 * @author Luka
 */
public interface HistoryDao {
    public void create(History h);
    public void delete(History h);
    public History findById(int id);
    public History findByDate(java.util.Date Date);
    public List<History> findAll();
    
}
