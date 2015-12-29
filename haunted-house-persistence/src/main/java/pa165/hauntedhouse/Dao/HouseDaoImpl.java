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
import pa165.hauntedhouse.Entity.House;
import pa165.hauntedhouse.Exception.DbException;

/**
 *
 * @author Milan
 */
@Repository
@Transactional
public class HouseDaoImpl implements HouseDao{
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public House findById(int id) {
        try {
            return em.find(House.class, id);
        } catch(Exception e) {
            throw new DbException("Entity manager has failed - possible cause: universe exploded", e);
        }
    }

    @Override
    public void create(House hs) {
        try {
            em.persist(hs);
        } catch(Exception e) {
            throw new DbException("Entity manager has failed - possible cause: duplicate entity", e);
        }
    }

    @Override
    public void delete(House hs) {
        try {
            em.remove(em.merge(hs));
        } catch(Exception e) {
            throw new DbException("Entity manager has failed - possible cause: not existing entity", e);
        }
    }
    
    @Override
    public void update(House hs) {
        try {
            em.merge(hs);
        } catch(Exception e) {
            throw new DbException("Entity manager has failed - possible cause: not existing entity", e);
        }
    }

    @Override
    public List<House> findAll() {
        try {
        return em.createQuery("select hs from House hs", House.class).getResultList();
        } catch(Exception e) {
            throw new DbException("Entity manager has failed - possible cause: universe exploded", e);
        }
    }

    @Override
    public House findByName(String name) {
        try {
            return em.createQuery("select hs from House hs where name = :name", House.class)
                    .setParameter("name", name).getSingleResult();
        } catch (NoResultException nrf) {
            return null;
        } catch(Exception e) {
            throw new DbException("Entity manager has failed - possible cause: universe exploded", e);
        }
    }

    @Override
    public List<House> searchByName(String filter, boolean visible) {
        try {
            return em.createQuery("select hs from House hs where visible = :visible and lower(name) like :filter", House.class)
                    .setParameter("filter", '%' + filter.toLowerCase() + '%')
                    .setParameter("visible", visible).getResultList();
        } catch (NoResultException nrf) {
            return null;
        } catch(Exception e) {
            throw new DbException("Entity manager has failed - possible cause: universe exploded", e);
        }
    }

    @Override
    public List<House> findAllByVisibility(boolean visible) {
        try {
            return em.createQuery("select h from House h where visible = :visible", House.class)
                    .setParameter("visible", visible).getResultList();
        } catch(Exception e) {
            throw new DbException("Entity manager has failed - possible cause: universe exploded", e);
        }
    }
}
