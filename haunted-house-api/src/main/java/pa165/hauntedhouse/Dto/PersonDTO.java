/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Dto;

import java.util.Objects;
import pa165.hauntedhouse.Enums.UserRole;

/**
 *
 * @author Andrej Dobes
 */
public class PersonDTO {
    private int id;
    
    private String email;
    
    private String firstName;
    
    private String lastName;
    
    private String password;
    
    private UserRole userRole;
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPassword() {
        return password;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof PersonDTO)) {
            return false;
        }
        
        PersonDTO p = (PersonDTO)obj;
        return id == p.getId() &&
                (firstName == null ? p.getFirstName() == null : firstName.equals(p.getFirstName())) &&
                (lastName == null ? p.getLastName() == null : lastName.equals(p.getLastName())) &&
                (email == null ? p.getEmail() == null : email.equals(p.getEmail()) &&
                userRole == p.getUserRole());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.id;
        hash = 47 * hash + Objects.hashCode(this.email);
        hash = 47 * hash + Objects.hashCode(this.firstName);
        hash = 47 * hash + Objects.hashCode(this.lastName);
        hash = 47 * hash + Objects.hashCode(this.userRole);
        return hash;
    }
}
