/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Dto;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Andrej Dobes
 */
public class SpookCreateDTO {
    private Set<HistoryDTO> histories = new HashSet<>();
    private Set<AbilityDTO> abilities = new HashSet<>();
    private HouseDTO house;
    
    public Set<HistoryDTO> getHistories() {
        return Collections.unmodifiableSet(histories);        
    }

    public void addHistory(HistoryDTO history) {
        this.histories.add(history);
    }    

    public Set<AbilityDTO> getAbilities() {
        return Collections.unmodifiableSet(abilities);
    }
    
    public void addAbility(AbilityDTO ability) {
        abilities.add(ability);
    }     
    
    public HouseDTO getHouse() {
        return house;
    }

    public void setHouse(HouseDTO house) {
        this.house = house;
    }
}
