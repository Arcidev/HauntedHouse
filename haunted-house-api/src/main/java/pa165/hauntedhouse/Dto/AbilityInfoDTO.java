package pa165.hauntedhouse.Dto;

import java.util.Objects;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Basic ability info without big image files
 * @author Andrej Dobes
 */
public class AbilityInfoDTO {
    
    private int id;
    
    @NotEmpty
    private String name;
    
    @Size(min = 10)
    private String info;
    
    private boolean visible;
    
    public void setId(int id) {
        this.id = id;
    }
    
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
    
    public boolean isVisible() {
        return visible;
    }

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
        
        if (!(obj instanceof AbilityInfoDTO)) {
            return false;
        }
        
        AbilityInfoDTO a = (AbilityInfoDTO)obj;
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
