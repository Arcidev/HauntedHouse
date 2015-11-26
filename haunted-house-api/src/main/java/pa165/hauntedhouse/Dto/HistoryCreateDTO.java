/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Dto;

import java.sql.Date;
import java.util.Objects;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Lucie Smidova
 */
public class HistoryCreateDTO extends HistoryDTO {
    @NotNull
    private SpookDTO spook;
    @NotNull
    private Date historyDate;
    @NotNull
    private String historyInfo;
    @NotNull
    private int id;
    
    public int getId(){
        return id;
    }
    
    public SpookDTO getSpook(){
	return spook;
    }
    
    public void setSpook(SpookDTO spook){
	this.spook=spook;
    }
 
    public Date getDate(){
        return historyDate;
    }
    
    public void setDate(Date historyDate){
        this.historyDate = historyDate;
    }
    
    public String getInf(){
        return historyInfo;
    }
    
    public void setInf(String historyInfo){
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
        
        if (!(obj instanceof HistoryDTO)) {
            return false;
        }
        
        HistoryDTO h = (HistoryDTO)obj;
        return id == ((HistoryDTO)obj).getID()&&
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
