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
import pa165.hauntedhouse.Dao.AbilityDao;
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
    @Autowired
    private AbilityDao abilityDao;
    
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
    public void delete(int id) {
        Spook s = spookDao.findById(id);
        spookDao.delete(s);
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
    public void addToAbility(int spookId, int abilityId) {
        Spook s = spookDao.findById(spookId);
        Ability a = abilityDao.findById(abilityId);
        s.addAbility(a);
        abilityDao.update(a);        
    }
    @Override
    public void removeFromAbility(int spookId, int abilityId) {
        Spook s = spookDao.findById(spookId);
        Ability a = abilityDao.findById(abilityId);
        s.removeAbility(a);
        abilityDao.update(a);
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
