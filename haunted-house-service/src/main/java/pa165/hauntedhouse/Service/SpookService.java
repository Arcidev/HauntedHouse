/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Service;

import java.util.List;
import pa165.hauntedhouse.Entity.Ability;
import pa165.hauntedhouse.Entity.History;
import pa165.hauntedhouse.Entity.House;
import pa165.hauntedhouse.Entity.Spook;

/**
 *
 * @author Martin Durcansky
 */
public interface SpookService {
    
    
    int create(Spook spook);
    
    void update(Spook spook);
    
       
    void delete(int id);
    
    Spook findById(int id);
    
    
    List<Spook> findAll();
    
    void addToAbility(int spookId, int abilityId);
    
    
    void removeFromAbility(int spookId, int abilityId);
    
    void addHistory(Spook spook, History history);
    
    
    void removeHistory(Spook spook, History history);
    
    
    public List<Ability> getAbilitiesBySpookId(int spookId);
    
    
    public House getHouseBySpookId(int spookId);
    
    
    List<Spook> searchSpooksByName(String filter);
    
}
