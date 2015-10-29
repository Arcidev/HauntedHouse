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
import pa165.hauntedhouse.Entity.History;

/**
 *
 * @author Luka
 */
@Repository
@Transactional
public class HistoryDaoImpl implements HistoryDao{
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void create(History h) {
        em.persist(h);
    }

    @Override
    public void delete(History h) {
        em.remove(h);
    }
    
    @Override
    public History findById(int id) {
        return em.find(History.class, id);
    }

    @Override
    public List<History> findAll() {
        return em.createQuery("select h from History h", History.class).getResultList();
    }

    @Override
    public History findByDate(java.util.Date historyDate) {
        try {
            return em.createQuery("select h from History h where h.historyDate = :date", History.class)
                    .setParameter("date", historyDate).getSingleResult();
        } catch (NoResultException nrf) {
            return null;
        }

    }
}
