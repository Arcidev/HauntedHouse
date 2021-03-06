/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pa165.hauntedhouse.Entity;

import java.sql.Date;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import org.dozer.Mapping;

/**
 *
 * @author Milan
 */

@Entity
public class House {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Mapping
    private int id;
    
    @NotNull
    private String name;
    
    @NotNull
    private String address;
    
    @NotNull
    private String history;
    
    private Date hauntedSince;
    
    @Lob
    private byte[] image;

    private String imageMimeType;
    
    private boolean visible;
    
    @OneToMany(fetch=FetchType.EAGER, mappedBy = "house")    
    private Set<Spook> spooks = new HashSet<>();
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
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
     * @param adress the adress to set
     */
    public void setAddress(String adress) {
        this.address = adress;
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
     * @return the spooks
     */
    public Set<Spook> getSpooks() {
        return Collections.unmodifiableSet(spooks); 
    }

    public void addSpook(Spook spook) {
        this.spooks.add(spook);
        spook.setHouse(this);
    }   
    
    public void removeSpook(Spook spook) {
        this.spooks.remove(spook);
    }
    
    /**
     * @return the image
     */
    public byte[] getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(byte[] image) {
        this.image = image;
    }

    /**
     * @return the imageMimeType
     */
    public String getImageMimeType() {
        return imageMimeType;
    }

    /**
     * @param imageMimeType the imageMimeType to set
     */
    public void setImageMimeType(String imageMimeType) {
        this.imageMimeType = imageMimeType;
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
        if (!(obj instanceof House)) {
            return false;
        }
        
        House hs = (House)obj;
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
