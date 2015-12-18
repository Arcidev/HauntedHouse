/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor..
 */
package pa165.hauntedhouse.Dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import pa165.hauntedhouse.Entity.Spook;
import pa165.hauntedhouse.Exception.DbException;
/**
 *
 * @author Martin Durcansky
 */
@Repository
@Transactional
public class SpookDaoImpl implements SpookDao{
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public Spook findById(int id) {
        try {
        return em.find(Spook.class, id);
        } catch(Exception e) {
            throw new DbException("Entity manager has failed - possible cause: universe exploded", e);
        }
        
    }
    @Override
    public void create(Spook spk) {
        try {
        em.persist(spk);
        } catch(Exception e) {
            throw new DbException("Entity manager has failed - possible cause: duplicate entity", e);
        }
    }
    @Override
    public void update(Spook spk) {
        try {
        em.merge(spk);
        } catch(Exception e) {
            throw new DbException("Entity manager has failed - possible cause: not existing entity", e);
        }
    }

    @Override
    public void delete(Spook spk) {
         try {
        em.remove(em.merge(spk));
        } catch(Exception e) {
            throw new DbException("Entity manager has failed - possible cause: not existing entity", e);
        }
    }

    @Override
    public List<Spook> findAll() {
        try {
        return em.createQuery("select spk from Spook spk", Spook.class).getResultList();
        } catch(Exception e) {
            throw new DbException("Entity manager has failed - possible cause: universe exploded", e);
        }
    }

    @Override
    public Spook findByName(String name) {
        try {
            return em.createQuery("select spk from Spook spk where name = :name", Spook.class)
                    .setParameter("name", name).getSingleResult();
        } catch (NoResultException nrf) {
            return null;
        } catch(Exception e) {
            throw new DbException("Entity manager has failed - possible cause: universe exploded", e);
        }

    }
    @Override
    public List<Spook> searchByName(String filter) {
        try {
            return em.createQuery("select sp from Spook sp where visible = true and lower(name) like :filter", Spook.class)
                    .setParameter("filter", '%' + filter.toLowerCase() + '%').getResultList();
        } catch (NoResultException nrf) {
            return null;
        } catch(Exception e) {
            throw new DbException("Entity manager has failed - possible cause: universe exploded", e);
        }
    }
    @Override
    public List<Spook> findAllByVisibility(boolean visible) {
        try {
            return em.createQuery("select s from Spook s where visible = :visible", Spook.class)
                    .setParameter("visible", visible).getResultList();
        } catch(Exception e) {
            throw new DbException("Entity manager has failed - possible cause: universe exploded", e);
        }
    }
    
}