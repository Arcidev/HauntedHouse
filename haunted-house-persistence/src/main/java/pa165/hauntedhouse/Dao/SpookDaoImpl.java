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
import pa165.hauntedhouse.Entity.Spook;
/**
 *
 * @author Martin Durcansky
 */

public class SpookDaoImpl implements SpookDao{
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public Spook findById(int id) {
        return em.find(Spook.class, id);
    }
    @Override
    public void create(Spook spk) {
        em.persist(spk);
    }
    @Override
    public void update(Spook spk) {
        em.merge(spk);
    }

    @Override
    public void delete(Spook spk) {
        Spook spook = em.find(Spook.class, spk.getId());
        em.remove(spook);
    }

    @Override
    public List<Spook> findAll() {
        return em.createQuery("select spk from Spook spk", Spook.class).getResultList();
    }

    @Override
    public Spook findByName(String name) {
        try {
            return em.createQuery("select spk from Spook spk where name = :name", Spook.class)
                    .setParameter("name", name).getSingleResult();
        } catch (NoResultException nrf) {
            return null;
        }

    }
    
}