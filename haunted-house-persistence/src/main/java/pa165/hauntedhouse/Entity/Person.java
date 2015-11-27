/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pa165.hauntedhouse.Entity;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.dozer.Mapping;
import pa165.hauntedhouse.Enums.UserRole;

/**
 *
 * @author Milan
 */
@Entity
public class Person {
       
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Mapping
    private int id;
    
    @NotNull
    @Pattern(regexp="^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;
    
    @NotNull
    private String firstName;
    
    @NotNull
    private String lastName;
    
    @NotNull
    private String passwordHash;
    
    @Enumerated
    @NotNull
    private UserRole userRole;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the passwordHash
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * @param passwordHash the passwordHash to set
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
    
        /**
     * @return the userRole
     */
    public UserRole getUserRole() {
        return userRole;
    }

    /**
     * @param userRole the userRole to set
     */
    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Person)) {
            return false;
        }
        
        Person u = (Person)obj;
        return id == u.getId() &&
                (firstName == null ? u.getFirstName() == null : firstName.equals(u.getFirstName())) &&
                (lastName == null ? u.getLastName() == null : lastName.equals(u.getLastName())) &&
                (email == null ? u.getEmail() == null : email.equals(u.getEmail()) &&
                (passwordHash == null ? u.getPasswordHash() == null : passwordHash.equals(u.getPasswordHash())) &&
                userRole == u.getUserRole());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.id;
        hash = 89 * hash + Objects.hashCode(this.email);
        hash = 89 * hash + Objects.hashCode(this.firstName);
        hash = 89 * hash + Objects.hashCode(this.lastName);
        hash = 89 * hash + Objects.hashCode(this.passwordHash);
        hash = 89 * hash + Objects.hashCode(this.userRole);
        return hash;
    }
}
