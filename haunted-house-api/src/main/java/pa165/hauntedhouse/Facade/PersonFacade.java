/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Facade;

import java.util.List;
import pa165.hauntedhouse.Dto.PersonDTO;

/**
 *
 * @author Andrej Dobes
 */
public interface PersonFacade {
    public void create(PersonDTO p);
    
    public void update(PersonDTO p);
    
    public void delete(int id);
    
    public PersonDTO findById(int id);
    
    public PersonDTO findPersonByEmail(String email);
    
    public List<PersonDTO> findAll();
    
    public List<PersonDTO> searchByLastName(String filter);
    
    public boolean isAdmin(int id);
    
    public PersonDTO authenticate(String email, String password);
}
