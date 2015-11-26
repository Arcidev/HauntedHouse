/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
skuska git-u
 */
package pa165.hauntedhouse.Entity;

import java.sql.Time;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
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
import org.dozer.Mapping;
/**
 *
 * @author Martin Durcansky
 */
@Entity
public class Spook  {
    
    @Id    
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Mapping
    private int id;
    
    @NotNull    
    private String name;
    
    @NotNull
    private String history;
    
    @NotNull
    private Time hauntsSince;
    
    @NotNull
    private Time hauntsUntil;
        
    @OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.PERSIST)    
    private Set<History> histories = new HashSet<>();
    
    @ManyToMany(targetEntity=Ability.class, fetch=FetchType.EAGER, cascade = CascadeType.ALL) 
    private Set<Ability> abilities = new HashSet<>();
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "House_id")
    private House house;

    public Set<History> getHistories() {
        return Collections.unmodifiableSet(histories);        
    }

    public void addHistory(History history) {
        this.histories.add(history);
        history.setSpook(this);
    }    

    public Set<Ability> getAbilities() {
        return Collections.unmodifiableSet(abilities);
    }

    
    public void addAbility(Ability ability) {
        abilities.add(ability);
    }     
    
    public void removeAbility(Ability ability) {
        abilities.remove(ability);
    }   
    
    public House getHouse() {
        return house;
    }
     public void removeHistory(History history) {
        histories.remove(history);
    }

    public void setHouse(House house) {
        this.house = house;
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
        
        if (!(obj instanceof Spook)) {
            return false;
        }
        Spook s = (Spook)obj;
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