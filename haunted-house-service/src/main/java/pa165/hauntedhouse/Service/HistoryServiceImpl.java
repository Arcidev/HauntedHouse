/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pa165.hauntedhouse.Dao.HistoryDao;
import pa165.hauntedhouse.Dao.SpookDao;
import pa165.hauntedhouse.Entity.History;
import pa165.hauntedhouse.Entity.Spook;

/**
 * @author Lucie Smidova
 */
@Service
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    private HistoryDao historyDao;
    
    @Autowired
    private SpookDao spookDao;
    
     @Override
    public int createHistory(History h, int spookId) {
        Spook s = spookDao.findById(spookId);
        if (s == null) {
            throw new IllegalArgumentException("Not existing spook");
        }
        h.setSpook(s);
        historyDao.create(h);
        return h.getID();
    }

    @Override
    public void updateHistory(History h) {
        History his = historyDao.findById(h.getID());
        if (his == null) {
            throw new IllegalArgumentException("History does not exist");
        }
        his.setInfo(h.getInfo());
        his.setHistoryDate(h.getHistoryDate());
        
        historyDao.update(his);
    }

    @Override
    public void deleteHistory(int id) {
        historyDao.delete(id);
    }

    @Override
    public History findHistoryById(int id) {
        return historyDao.findById(id);
    }

    @Override
    public List<History> getAllHistories() {
        return historyDao.findAll();
    }
    
    @Override
    public List<History> getAllSpooksHistories(Spook s){
        List<History> h = historyDao.getAllSpooksHistories(s);
        if (h == null) {
            return new ArrayList<>();
        }
        
        return h;
    }
    
    @Override
    public List<History> searchHistoryByRange(java.util.Date Date1, java.util.Date Date2) {
        List<History> h = historyDao.searchByRange(Date1, Date2);
        if (h == null) {
            return new ArrayList<>();
        }
        
        return h;
    }

    @Override
    public List<History> searchTopHistoryByInfo(String pattern, int top){
        List<History> h = historyDao.searchTopHistoryByInfo(pattern, top);
        if (h == null) {
            return new ArrayList<>();
        }
        
        return h;
    }
    
    @Override
    public Spook getSpookByHistoryId(int historyId) {
        History history = historyDao.findById(historyId);
        return history.getSpook();
    }
    
}
