/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import pa165.hauntedhouse.Dao.SpookDao;
import pa165.hauntedhouse.Entity.Ability;
import pa165.hauntedhouse.Entity.House;
import pa165.hauntedhouse.Entity.Spook;

/**
 *
 * @author Andrej Dobes
 */
public class SpookServiceImpl implements SpookService {
    
    @Autowired
    private SpookDao spookDao;
    
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
}
