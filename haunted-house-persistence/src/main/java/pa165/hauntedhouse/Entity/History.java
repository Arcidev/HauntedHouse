/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Entity;


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
/**
 *
 * @author Luka
 * date, id, info
 */
@Entity
public class History {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    @Temporal(TemporalType.DATE)
    private java.util.Date historyDate;
    
    private String historyInfo;
    
    @ManyToOne
    @JoinColumn(name = "spook_id")
    private Spook spook;
    
    public Spook getSpook(){
	return spook;
    }
    
    public void setSpook(Spook spook){
	this.spook=spook;
    }
    
    
    public int getID(){
        return id;
    }
    
    public java.util.Date getHistoryDate(){
        return historyDate;
    }
    
    public String getInfo(){
        return historyInfo;
    }
    
    public void setInfo(String historyInfo){
        this.historyInfo = historyInfo;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (obj == null) {
            return false;
        }
        
        if (!(obj instanceof History)) {
            return false;
        }
        
        return id == ((History)obj).getID();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id;
        return hash;
    }
}
