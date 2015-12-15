/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Dto;

import pa165.hauntedhouse.Enums.UserRole;

/**
 *
 * @author Andrej Dobes
 */
public class PersonCreateDTO extends PersonDTO {
    
    private String passwordAgain;
    
    public PersonCreateDTO() {
        setUserRole(UserRole.USER);
    }
    
    public String getPasswordAgain() {
        return passwordAgain;
    }
    
    public void setPasswordAgain(String password) {
        passwordAgain = password;
    }
}
