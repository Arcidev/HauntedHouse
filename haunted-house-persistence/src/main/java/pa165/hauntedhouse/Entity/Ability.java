/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Entity;

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
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import org.dozer.Mapping;

/**
 *
 * @author Andrej Dobes
 */

@Entity
public class Ability {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Mapping
    private int id;
    
    @NotNull
    private String name;
    
    @NotNull
    private String info;
    
    @Lob
    private byte[] image;

    private String imageMimeType;
    
    private boolean visible;
    
    @ManyToMany(targetEntity=Spook.class, mappedBy="abilities", fetch=FetchType.EAGER)
    private Set<Spook> spooks = new HashSet<>(); 

    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getInfo() {
        return info;
    }
    
    public void setInfo(String info) {
        this.info = info;
    }
    
    public byte[] getImage() {
        return image;
    }
    
    public void setImage(byte[] image) {
        this.image = image;
    }
    
    public String getImageMimeType() {
        return imageMimeType;
    }

    public void setImageMimeType(String imageMimeType) {
        this.imageMimeType = imageMimeType;
    }
    
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
    public Set<Spook> getSpooks() {
        return Collections.unmodifiableSet(spooks);
    }
    
    public void addSpook(Spook s) {
        spooks.add(s);
        s.addAbility(this);
    }
    
    public void remove(Spook s) {
        spooks.remove(s);
        s.removeAbility(this);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (obj == null) {
            return false;
        }
        
        if (!(obj instanceof Ability)) {
            return false;
        }
        
        Ability a = (Ability)obj;
        return id == a.getId() &&
                (name == null ? a.getName() == null : name.equals(a.getName())) &&
                (info == null ? a.getInfo() == null : info.equals(a.getInfo()));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id;
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + Objects.hashCode(this.info);
        return hash;
    }
}
