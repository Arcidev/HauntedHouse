/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import pa165.hauntedhouse.Dao.AbilityDao;
import pa165.hauntedhouse.Entity.Ability;
import pa165.hauntedhouse.Entity.Spook;

/**
 *
 * @author Andrej Dobes
 */
public class AbilityServiceImpl implements AbilityService {

    @Autowired
    private AbilityDao abilityDao;
    
    @Override
    public int create(Ability ability) {
        abilityDao.create(ability);
        return ability.getId();
    }

    @Override
    public void update(Ability ability) {
        abilityDao.update(ability);
    }

    @Override
    public void delete(Ability ability) {
        abilityDao.delete(ability);
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
    public void addToSpook(Ability ability, Spook spook) {
        ability.addSpook(spook);
        abilityDao.update(ability);
    }

    @Override
    public void removeFromSpook(Ability ability, Spook spook) {
        ability.remove(spook);
        abilityDao.update(ability);
    }
    
    @Override
    public List<Ability> searchAbilitiesByName(String filter) {
        List<Ability> abilities = abilityDao.searchByName(filter);
        if (abilities == null) {
            return new ArrayList<>();
        }
        
        return abilities;
    }
}
