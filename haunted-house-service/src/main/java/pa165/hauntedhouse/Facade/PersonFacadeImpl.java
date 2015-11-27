/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Facade;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pa165.hauntedhouse.Dto.PersonDTO;
import pa165.hauntedhouse.Entity.Person;
import pa165.hauntedhouse.Service.PersonService;
import pa165.hauntedhouse.ServiceConfig.Service.BeanMappingService;

/**
 *
 * @author Andrej Dobes
 */
@Service
public class PersonFacadeImpl implements PersonFacade {

    @Autowired
    private BeanMappingService beanMappingService;
    
    @Autowired
    private PersonService personService;
    
    @Override
    public void create(PersonDTO p) {
        p.setId(personService.create(beanMappingService.mapTo(p, Person.class), p.getPassword()));
    }
    
    @Override
    public void update(PersonDTO p) {
        personService.update(beanMappingService.mapTo(p, Person.class), p.getPassword());
    }
    
    @Override
    public void delete(int id) {
        personService.delete(id);
    }

    @Override
    public PersonDTO findById(int id) {
        Person person = personService.findById(id);
        if (person == null) {
            return null;
        }
        return beanMappingService.mapTo(person, PersonDTO.class);
    }

    @Override
    public PersonDTO findPersonByEmail(String email) {
        Person person = personService.findPersonByEmail(email);
        if (person == null) {
            return null;
        }
        return beanMappingService.mapTo(person, PersonDTO.class);
    }

    @Override
    public List<PersonDTO> findAll() {
        return beanMappingService.mapTo(personService.findAll(), PersonDTO.class);
    }

    @Override
    public List<PersonDTO> searchByLastName(String filter) {
        return beanMappingService.mapTo(personService.searchByLastName(filter), PersonDTO.class);
    }

    @Override
    public boolean isAdmin(int id) {
        return personService.isAdmin(id);
    }

    @Override
    public PersonDTO authenticate(String email, String password) {
        Person person = personService.authenticate(email, password);
        if (person == null) {
            return null;
        }
        return beanMappingService.mapTo(person, PersonDTO.class);
    }
    
}
