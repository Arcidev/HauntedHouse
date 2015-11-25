/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Service;

import java.util.List;
import pa165.hauntedhouse.Entity.Ability;
import pa165.hauntedhouse.Entity.House;

/**
 *
 * @author Andrej Dobes
 */
public interface SpookService {
    
    /**
     * Gets spook abilities
     * 
     * @param spookId
     * @return all spook abilities
     */
    public List<Ability> getAbilitiesBySpookId(int spookId);
    
    /**
     * Gets spook's house
     * 
     * @param spookId
     * @return spook's house
     */
    public House getHouseBySpookId(int spookId);
}