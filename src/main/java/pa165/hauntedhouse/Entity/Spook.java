/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Entity;

import java.sql.Time;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
/**
 *
 * @author Martin Durcansky
 */
@Entity
public class Spook {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    @NotNull
    @Column(nullable=false)
    private String name;
    
    @NotNull
    private String history;
    
    @NotNull
    private Time hountsSince;
    
    @NotNull
    private Time hountsUntil;

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
    
    public void setHountsSince(Time hountsSince) {
        this.hountsSince = hountsSince;
    }
    public Time getHountsSince() {
        return hountsSince;
    }
    
    public void setHountsUntil(Time hountsUntil) {
        this.hountsUntil = hountsUntil;
    }
    public Time getHountsUntil() {
        return hountsUntil;
    }
    
   
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (obj == null) {
            return false;
        }
        
        if (!(obj instanceof Spook)) {
            return false;
        }
        
        return id == ((Spook)obj).getId();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id;
        return hash;
    }
}