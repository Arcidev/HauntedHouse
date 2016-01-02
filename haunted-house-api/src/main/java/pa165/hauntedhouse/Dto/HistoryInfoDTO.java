/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Dto;

import java.sql.Date;
import java.util.Objects;
import javax.validation.constraints.Size;

/**
 *
 * @author Lucie Smidova
 */
public class HistoryInfoDTO {
    private int id;
    private Date historyDate;
    
    @Size(min = 10)
    private String historyInfo;
    
    public int getId(){
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
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
        
        if (!(obj instanceof HistoryInfoDTO)) {
            return false;
        }
        
        HistoryInfoDTO h = (HistoryInfoDTO)obj;
        return id == h.getId()&&
                (historyDate == null ? h.getHistoryDate() == null : historyDate.equals(h.getHistoryDate())) &&
                (historyInfo == null ? h.getInfo() == null : historyInfo.equals(h.getInfo()));
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + this.id;
        hash = 43 * hash + Objects.hashCode(this.historyDate);
        hash = 43 * hash + Objects.hashCode(this.historyInfo);
        return hash;
    }
}
