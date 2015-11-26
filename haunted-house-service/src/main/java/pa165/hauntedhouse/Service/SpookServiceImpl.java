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
import org.springframework.transaction.annotation.Transactional;
import pa165.hauntedhouse.Dao.SpookDao;
import pa165.hauntedhouse.Entity.Ability;
import pa165.hauntedhouse.Entity.History;
import pa165.hauntedhouse.Entity.House;
import pa165.hauntedhouse.Entity.Spook;

/**
 *
 * @author Martin Durcansky
 */
@Service
@Transactional
public class SpookServiceImpl implements SpookService {
    
    @Autowired
    private SpookDao spookDao;
    
    @Override
    public int create(Spook spook) {
        spookDao.create(spook);
        return spook.getId();
    }
    
    @Override
    public void update(Spook spook) {
        spookDao.update(spook);
    }

    @Override
    public void delete(Spook spook) {
        spookDao.delete(spook);
    }

    @Override
    public Spook findById(int id) {
        return spookDao.findById(id);
    }

    @Override
    public List<Spook> findAll() {
        return spookDao.findAll();
    }
    
    @Override
    public List<Ability> getAbilitiesBySpookId(int spookId) {
        Spook spook = spookDao.findById(spookId);
        if (spook == null) {
            return new ArrayList<>();
        }
        
        return new ArrayList<>(spook.getAbilities());
    }

    @Override
    public House getHouseBySpookId(int spookId) {
        Spook spook = spookDao.findById(spookId);
        return spook.getHouse();
    }
    @Override
    public void addAbility(Spook spook, Ability ability) {
        spook.addAbility(ability);
        spookDao.update(spook);
    }
    
    public void removeAbility(Spook spook, Ability ability) {
        spook.removeAbility(ability);
        spookDao.update(spook);
    }
    public void addHistory(Spook spook, History history) {
        spook.addHistory(history);
        spookDao.update(spook);
    }

    @Override
    public List<Spook> searchSpooksByName(String filter) {
         List<Spook> spooks = spookDao.searchByName(filter);
        if (spooks == null) {
            return new ArrayList<>();
        }        
        return spooks;
    }

    @Override
    public void removeHistory(Spook spook, History history) {
        spook.removeHistory(history);
        spookDao.update(spook);
    }
}
