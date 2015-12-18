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
import pa165.hauntedhouse.Dao.AbilityDao;
import pa165.hauntedhouse.Dao.SpookDao;
import pa165.hauntedhouse.Entity.Ability;
import pa165.hauntedhouse.Entity.Spook;

/**
 *
 * @author Andrej Dobes
 */
@Service
public class AbilityServiceImpl implements AbilityService {

    @Autowired
    private AbilityDao abilityDao;
    
    @Autowired
    private SpookDao spookDao;
    
    @Override
    public int create(Ability ability) {
        abilityDao.create(ability);
        return ability.getId();
    }

    @Override
    public void update(Ability ability) {
        Ability a = abilityDao.findById(ability.getId());
        if (a == null) {
            throw new IllegalArgumentException("Ability does not exist");
        }
        a.setName(ability.getName());
        a.setInfo(ability.getInfo());
        if (ability.getImage() != null) {
            a.setImage(ability.getImage());
            a.setImageMimeType(a.getImageMimeType());
        }
        
        abilityDao.update(a);
    }

    @Override
    public void delete(int id) {
        Ability a = abilityDao.findById(id);
        if (a == null) {
            throw new IllegalArgumentException("Ability does not exist");
        }
        abilityDao.delete(a);
    }

    @Override
    public Ability findById(int id) {
        return abilityDao.findById(id);
    }

    @Override
    public List<Ability> findAll() {
        return abilityDao.findAll();
    }
    
    @Override
    public List<Ability> findAllByVisibility(boolean visible) {
        return abilityDao.findAllByVisibility(visible);
    }

    @Override
    public void addToSpook(int abilityId, int spookId) {
        Ability a = abilityDao.findById(abilityId);
        Spook s = spookDao.findById(spookId);
        if (a == null || s == null) {
            throw new IllegalArgumentException("Ability or Spook does not exist with passed ids");
        }
        
        a.addSpook(s);
        spookDao.update(s); // updates ability with cascade
    }

    @Override
    public void removeFromSpook(int abilityId, int spookId) {
        Ability a = abilityDao.findById(abilityId);
        Spook s = spookDao.findById(spookId);
        if (a == null || s == null) {
            throw new IllegalArgumentException("Ability or Spook does not exist with passed ids");
        }
        
        a.remove(s);
        spookDao.update(s); // updates ability with cascade
    }
    
    @Override
    public void setVisible(int abilityId, boolean visible) {
        Ability a = abilityDao.findById(abilityId);
        if (a == null) {
            throw new IllegalArgumentException("Ability does not exist");
        }
        a.setVisible(visible);
        abilityDao.update(a);
    }
    
    @Override
    public List<Ability> searchAbilitiesByName(String filter, boolean visible) {
        List<Ability> abilities = abilityDao.searchByName(filter, visible);
        if (abilities == null) {
            return new ArrayList<>();
        }
        
        return abilities;
    }
    
    @Override
    public List<Spook> getSpooksByAbilityId(int abilityId){
    Ability ability = abilityDao.findById(abilityId);
        if (ability == null) {
            return new ArrayList<>();
        }

        ArrayList<Spook> spooks = new ArrayList<>(ability.getSpooks());
        spooks.removeIf(x -> !x.isVisible());
        return spooks;
    }
}
