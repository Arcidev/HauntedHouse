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
import org.springframework.transaction.annotation.Transactional;
import pa165.hauntedhouse.Entity.Ability;

/**
 *
 * @author Andrej Dobes
 */
@Transactional
public class AbilityDaoImpl implements AbilityDao {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public Ability findById(int id) {
        return em.find(Ability.class, id);
    }

    
    @Override
    public void create(Ability attr) {
        em.persist(attr);
    }
    
    @Override
    public void delete(Ability attr) {
        em.remove(attr);
    }

    @Override
    public List<Ability> findAll() {
        return em.createQuery("select a from Ability a", Ability.class).getResultList();
    }

    @Override
    public Ability findByName(String name) {
        try {
            return em.createQuery("select a from Ability a where name = NAME_PLACEHOLDER", Ability.class)
                    .setParameter("NAME_PLACEHOLDER", name).getSingleResult();
        } catch (NoResultException nrf) {
            return null;
        }
    }
}
