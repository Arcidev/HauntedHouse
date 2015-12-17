/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Dto;

import java.sql.Date;
import java.util.Objects;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Milan Matijka
 */
public class HouseInfoDTO {
    private int id;
    
    @NotEmpty
    private String name;
    
    @NotEmpty
    private String address;
    
    @Size(min = 20)
    private String history;
    
    private Date hauntedSince;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public Date getHauntedSince() {
        return hauntedSince;
    }

    public void setHauntedSince(Date hauntedSince) {
        this.hauntedSince = hauntedSince;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof HouseInfoDTO)) {
            return false;
        }
        
        HouseInfoDTO hs = (HouseInfoDTO)obj;
        return id == hs.getId() &&
                (name == null ? hs.getName() == null : name.equals(hs.getName())) &&
                (address == null ? hs.getAddress() == null : address.equals(hs.getAddress())) &&
                (history == null ? hs.getHistory() == null : history.equals(hs.getHistory())) &&
                (hauntedSince == null ? hs.getHauntedSince() == null : hauntedSince.equals(hs.getHauntedSince()));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id;
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + Objects.hashCode(this.address);
        hash = 59 * hash + Objects.hashCode(this.history);
        hash = 59 * hash + Objects.hashCode(this.hauntedSince);
        return hash;
    }     
}
