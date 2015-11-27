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
import pa165.hauntedhouse.Exception.DbException;

/**
 *
 * @author Milan
 */
@Repository
@Transactional
public class PersonDaoImpl implements PersonDao {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Person p) {
        try {
            em.persist(p);
        } catch(Exception e) {
            throw new DbException("Entity manager has failed - possible cause: universe exploded", e);
        }
    }
    
    @Override
    public void update(Person p) {
        try {
            em.merge(p);
        } catch(Exception e) {
            throw new DbException("Entity manager has failed - possible cause: not existing entity", e);
        }
    }
    
    @Override
    public void delete(Person p) {
        try {
            em.remove(em.merge(p));
        } catch(Exception e) {
            throw new DbException("Entity manager has failed - possible cause: not existing entity", e);
        }
    }

    @Override
    public Person findById(int id) {
        try {
            return em.find(Person.class, id);
        } catch(Exception e) {
            throw new DbException("Entity manager has failed - possible cause: universe exploded", e);
        }
    }

    @Override
    public Person findPersonByEmail(String email) {
        try {
            return em.createQuery("select u from Person u where lower(email) = :email", Person.class)
                    .setParameter("email", email.trim().toLowerCase()).getSingleResult();
        } catch (NoResultException nrf) {
            return null;
        } catch(Exception e) {
            throw new DbException("Entity manager has failed - possible cause: universe exploded", e);
        }
    }

    @Override
    public List<Person> findAll() {
        try {
            return em.createQuery("select u from Person u", Person.class).getResultList();
        } catch(Exception e) {
            throw new DbException("Entity manager has failed - possible cause: universe exploded", e);
        }
        
    }
    
    @Override
    public List<Person> searchByLastName(String filter) {
        try {
            return em.createQuery("select u from Person u where lower(lastName) like :filter", Person.class)
                    .setParameter("filter", '%' + filter.toLowerCase() + '%').getResultList();
        } catch (NoResultException nrf) {
            return null;
        } catch(Exception e) {
            throw new DbException("Entity manager has failed - possible cause: universe exploded", e);
        }
    }
}
