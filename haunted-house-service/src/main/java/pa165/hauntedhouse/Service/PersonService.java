/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Service;

import java.util.List;
import pa165.hauntedhouse.Entity.Person;

/**
 *
 * @author Andrej Dobes
 */
public interface PersonService {
    public int create(Person p, String password);
    
    public void update(Person p, String newPassword);
    
    public void delete(int id);
    
    public Person findById(int id);
    
    public Person findPersonByEmail(String email);
    
    public List<Person> findAll();
    
    public List<Person> searchByLastName(String filter);
    
    public boolean isAdmin(int id);
    
    public Person authenticate(String email, String password);
}
