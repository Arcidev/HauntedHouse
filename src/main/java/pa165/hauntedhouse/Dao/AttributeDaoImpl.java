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
import pa165.hauntedhouse.Entity.Attribute;

/**
 *
 * @author Andrej Dobes
 */
public class AttributeDaoImpl implements AttributeDao {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public Attribute findById(int id) {
        return em.find(Attribute.class, id);
    }

    @Override
    public void create(Attribute attr) {
        em.persist(attr);
    }

    @Override
    public void delete(Attribute attr) {
        em.remove(attr);
    }

    @Override
    public List<Attribute> findAll() {
        return em.createQuery("select attr from Attribute attr", Attribute.class).getResultList();
    }

    @Override
    public Attribute findByName(String name) {
        try {
            return em.createQuery("select attr from Attribute attr where name = NAME_PLACEHOLDER", Attribute.class)
                    .setParameter("NAME_PLACEHOLDER", name).getSingleResult();
        } catch (NoResultException nrf) {
            return null;
        }

    }
    
}
