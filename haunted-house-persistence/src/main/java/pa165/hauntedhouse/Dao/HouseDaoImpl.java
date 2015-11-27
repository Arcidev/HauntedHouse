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
        return em.find(House.class, id);
    }

    @Override
    public void create(House hs) {
        em.persist(hs);
    }

    @Override
    public void delete(House hs) {
        em.remove(em.merge(hs));
    }
    
    @Override
    public void update(House hs) {
        em.merge(hs);
    }

    @Override
    public List<House> findAll() {
        return em.createQuery("select hs from House hs", House.class).getResultList();
    }

    @Override
    public House findByName(String name) {
        try {
            return em.createQuery("select hs from House hs where name = :name", House.class)
                    .setParameter("name", name).getSingleResult();
        } catch (NoResultException nrf) {
            return null;
        }
    }

    @Override
    public List<House> searchByName(String filter) {
        try {
            return em.createQuery("select hs from House hs where name like :filter", House.class)
                    .setParameter("filter", '%' + filter.toLowerCase() + '%').getResultList();
        } catch (NoResultException nrf) {
            return null;
        }
    }
}
