/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pa165.hauntedhouse.Dao;

import java.util.List;
import pa165.hauntedhouse.Entity.Person;

/**
 *
 * @author Milan
 */
public interface PersonDao {
    
    public void create(Person u);
    
    public Person findById(int id);
    
    public Person findPersonByEmail(String email);
    
    public List<Person> findAll();
    
    public List<Person> searchByLastName(String filter);
    
}
