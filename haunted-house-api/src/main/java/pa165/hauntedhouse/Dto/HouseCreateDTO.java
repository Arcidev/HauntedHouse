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
public class HouseCreateDTO {
    private Set<SpookDTO> spooks = new HashSet<>();
    
    public Set<SpookDTO> getSpooks() {
        return Collections.unmodifiableSet(spooks); 
    }

    public void addSpook(SpookDTO spook) {
        this.spooks.add(spook);
    }   
}
