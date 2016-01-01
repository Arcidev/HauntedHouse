/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Entity;


import java.sql.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.dozer.Mapping;
/**
 *
 * @author Lucie Smidova
 * date, id, info
 */
@Entity
public class History {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Mapping
    private int id;
    
    @NotNull
    private Date historyDate;
    
    private String historyInfo;
    
    @ManyToOne
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
 
    public Date getHistoryDate(){
        return historyDate;
    }
    
    public void setHistoryDate(Date historyDate){
        this.historyDate = historyDate;
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
        
        History h = (History)obj;
        return id == ((History)obj).getID()&&
                (historyDate == null ? h.getHistoryDate() == null : historyDate.equals(h.getHistoryDate())) &&
                (historyInfo == null ? h.getInfo() == null : historyInfo.equals(h.getInfo()));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
        hash = 37 * hash + Objects.hashCode(this.historyDate);
        hash = 37 * hash + Objects.hashCode(this.historyInfo);
        return hash;
    }
}
