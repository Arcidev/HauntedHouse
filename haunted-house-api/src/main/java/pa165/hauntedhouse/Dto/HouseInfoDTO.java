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
    
    private boolean visible;  
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the history
     */
    public String getHistory() {
        return history;
    }

    /**
     * @param history the history to set
     */
    public void setHistory(String history) {
        this.history = history;
    }

    /**
     * @return the hauntedSince
     */
    public Date getHauntedSince() {
        return hauntedSince;
    }

    /**
     * @param hauntedSince the hauntedSince to set
     */
    public void setHauntedSince(Date hauntedSince) {
        this.hauntedSince = hauntedSince;
    }
    
    /**
     * @return the visible
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * @param visible the visible to set
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
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
        return getId() == hs.getId() &&
                (getName() == null ? hs.getName() == null : getName().equals(hs.getName())) &&
                (getAddress() == null ? hs.getAddress() == null : getAddress().equals(hs.getAddress())) &&
                (getHistory() == null ? hs.getHistory() == null : getHistory().equals(hs.getHistory())) &&
                (getHauntedSince() == null ? hs.getHauntedSince() == null : getHauntedSince().equals(hs.getHauntedSince()));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.getId();
        hash = 59 * hash + Objects.hashCode(this.getName());
        hash = 59 * hash + Objects.hashCode(this.getAddress());
        hash = 59 * hash + Objects.hashCode(this.getHistory());
        hash = 59 * hash + Objects.hashCode(this.getHauntedSince());
        return hash;
    }     
}
