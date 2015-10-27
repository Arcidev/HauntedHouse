/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Entity;

import java.io.Serializable;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
/**
 *
 * @author Martin Durcansky
 */
@Entity
public class Spook  {
    
    @Id
    @Column(name="spok_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    @NotNull    
    private String name;
    
    @NotNull
    private String history;
    
    @NotNull
    private Time hountsSince;
    
    @NotNull
    private Time hountsUntil;
    
    
    @OneToMany
    @JoinColumn(name="spok_id")
    private Set<History> histories = new HashSet<>();

    public Set<History> getHistories() {
        return histories;
    }

    public void setHistories(Set<History> histories) {
        this.histories = histories;
    }
    
    
    
    @ManyToMany(targetEntity=Ability.class, mappedBy="spooks", fetch=FetchType.EAGER) 
    private Set<Ability> abilities = new HashSet<>();    

    public Set<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(Set<Ability> abilities) {
        this.abilities = abilities;
    }
         
       
      
   /*
    @ManyToOne
    @JoinColumn(name = "house_id")
    private House house;
    

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }
    
   */

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