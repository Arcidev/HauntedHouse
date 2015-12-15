/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Dto;

import java.sql.Time;
import java.util.Objects;

/**
 *
 * @author Andrej
 */
public class SpookInfoDTO {
    private int id;
    private String name;
    private String history;
    private Time hauntsSince;
    private Time hauntsUntil;

    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }  
    
    public void setHistory(String history) {
        this.history = history;
    }
    
    public String getHistory() {
        return history;
    }
    
    public void setHauntsSince(Time hauntsSince) {
        this.hauntsSince = hauntsSince;
    }
    public Time getHauntsSince() {
        return hauntsSince;
    }
    
    public void setHauntsUntil(Time hauntsUntil) {
        this.hauntsUntil = hauntsUntil;
    }
    public Time getHauntsUntil() {
        return hauntsUntil;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (obj == null) {
            return false;
        }
        
        if (!(obj instanceof SpookInfoDTO)) {
            return false;
        }
        SpookInfoDTO s = (SpookInfoDTO)obj;
        return id == s.getId() &&
                (name == null ? s.getName() == null : name.equals(s.getName())) &&
                (history == null ? s.getHistory() == null : history.equals(s.getHistory())) &&
                (hauntsSince == null ? s.getHauntsSince() == null : hauntsSince.equals(s.getHauntsSince())) &&
                (hauntsUntil == null ? s.getHauntsUntil() == null : hauntsUntil.equals(s.getHauntsUntil()));
                
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id;
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + Objects.hashCode(this.history);
        hash = 59 * hash + Objects.hashCode(this.hauntsSince);
        hash = 59 * hash + Objects.hashCode(this.hauntsUntil);
        return hash;
    }
}
