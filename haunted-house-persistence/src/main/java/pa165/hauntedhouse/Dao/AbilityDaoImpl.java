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
import pa165.hauntedhouse.Entity.Ability;
import pa165.hauntedhouse.Exception.DbException;

/**
 *
 * @author Andrej Dobes
 */
@Repository
@Transactional
public class AbilityDaoImpl implements AbilityDao {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public Ability findById(int id) {
        try {
            return em.find(Ability.class, id);
        } catch(Exception e) {
            throw new DbException("Entity manager has failed - possible cause: universe exploded", e);
        }
    }
  
    @Override
    public void create(Ability a) {
        try {
            em.persist(a);
        } catch(Exception e) {
            throw new DbException("Entity manager has failed - possible cause: duplicate entity", e);
        }
    }
    
    @Override
    public void delete(Ability a) {
        try {
            em.remove(em.merge(a));
        } catch(Exception e) {
            throw new DbException("Entity manager has failed - possible cause: not existing entity", e);
        }
    }
    
    @Override
    public void update(Ability a) {
        try {
            em.merge(a);
        } catch(Exception e) {
            throw new DbException("Entity manager has failed - possible cause: not existing entity", e);
        }
    }

    @Override
    public List<Ability> findAll() {
        try {
            return em.createQuery("select a from Ability a", Ability.class).getResultList();
        } catch(Exception e) {
            throw new DbException("Entity manager has failed - possible cause: universe exploded", e);
        }
    }
    
    @Override
    public List<Ability> findAllByVisibility(boolean visible) {
        try {
            return em.createQuery("select a from Ability a where visible = :visible", Ability.class)
                    .setParameter("visible", visible).getResultList();
        } catch(Exception e) {
            throw new DbException("Entity manager has failed - possible cause: universe exploded", e);
        }
    }

    @Override
    public Ability findByName(String name) {
        try {
            return em.createQuery("select a from Ability a where name = :name", Ability.class)
                    .setParameter("name", name).getSingleResult();
        } catch (NoResultException nrf) {
            return null;
        } catch(Exception e) {
            throw new DbException("Entity manager has failed - possible cause: universe exploded", e);
        }
    }
    
    @Override
    public List<Ability> searchByName(String filter) {
        try {
            return em.createQuery("select a from Ability a where visible = true and lower(name) like :filter", Ability.class)
                    .setParameter("filter", '%' + filter.toLowerCase() + '%').getResultList();
        } catch (NoResultException nrf) {
            return null;
        } catch(Exception e) {
            throw new DbException("Entity manager has failed - possible cause: universe exploded", e);
        }
    }
}
