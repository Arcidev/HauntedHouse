/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pa165.hauntedhouse.Dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import pa165.hauntedhouse.Entity.Person;

/**
 *
 * @author Milan
 */
@Repository
@Transactional
public class PersonDaoImpl implements PersonDao{
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Person u) {
        em.persist(u);
    }

    @Override
    public Person findById(int id) {
        return em.find(Person.class, id);
    }

    @Override
    public Person findPersonByEmail(String email) {
        try {
            return em.createQuery("select u from Person u where email = :email", Person.class)
                    .setParameter("email", email).getSingleResult();
        } catch (NoResultException nrf) {
            return null;
        }
    }

    @Override
    public List<Person> findAll() {
        return em.createQuery("select u from Person u", Person.class).getResultList();
    }
    
    @Override
    public List<Person> searchByLastName(String filter) {
        try {
            return em.createQuery("select u from Person u where lastName like :filter", Person.class)
                    .setParameter("filter", '%' + filter.toLowerCase() + '%').getResultList();
        } catch (NoResultException nrf) {
            return null;
        }
    }
}
