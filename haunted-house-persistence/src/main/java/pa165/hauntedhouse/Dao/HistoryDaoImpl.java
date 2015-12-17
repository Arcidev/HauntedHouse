/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
house2.setHauntedSince(Date.valueOf("2009-8-11"));
 */
package pa165.hauntedhouse.Dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import pa165.hauntedhouse.Entity.History;
import pa165.hauntedhouse.Exception.DbException;

/**
 *
 * @author Lucie Smidova
 */
@Repository
@Transactional
public class HistoryDaoImpl implements HistoryDao{
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void create(History h) {
        try {
            em.persist(h);
        } catch(Exception e) {
            throw new DbException("Entity manager has failed - possible cause: duplicate entity", e);
        }
        
    }

    @Override
    public void delete(History h) {
        try {
            em.remove(em.merge(h));
        } catch(Exception e) {
            throw new DbException("Entity manager has failed - possible cause: not existing entity", e);
        }
         
    }
    
    @Override
    public void update(History h){
        try {
            em.merge(h);
        } catch(Exception e) {
            throw new DbException("Entity manager has failed - possible cause: not existing entity", e);
        }
        
    }
    
    @Override
    public History findById(int id) {
        try {
            return em.find(History.class, id);
        } catch(Exception e) {
            throw new DbException("Entity manager has failed - possible cause: universe exploded", e);
        }
        
    }

    @Override
    public List<History> findAll() {
        try {
            return em.createQuery("select h from History h", History.class).getResultList();
        } catch(Exception e) {
            throw new DbException("Entity manager has failed - possible cause: universe exploded", e);
        }
       
    }

    @Override
    public History findByDate(java.util.Date historyDate) {
        try {
            return em.createQuery("select h from History h where h.historyDate = :date", History.class)
                    .setParameter("date", historyDate).getSingleResult();
        } catch (NoResultException nrf) {
            return null;
        } catch(Exception e) {
            throw new DbException("Entity manager has failed - possible cause: universe exploded", e);
        }

    }
    
    @Override
    public List<History> getAllSpooksHistories(int spookId){
        try {
            return em.createQuery("select h from History h where h.spook = :s", History.class)
                    .setParameter("s", spookId).getResultList();
        } catch (NoResultException nrf) {
            return null;
        } catch(Exception e) {
            throw new DbException("Entity manager has failed - possible cause: universe exploded", e);
        }
    }
    
    @Override
    public List<History> searchByRange(java.util.Date historyDate1, java.util.Date historyDate2) {
        try {
            return em.createQuery("select h from History h where h.historyDate between :date1 and :date2", History.class)
                    .setParameter("date1", historyDate1).setParameter("date2", historyDate2).getResultList();
        } catch (NoResultException nrf) {
            return null;
        } catch(Exception e) {
            throw new DbException("Entity manager has failed - possible cause: universe exploded", e);
        }
    }
    
    @Override
    public List<History> searchTopHistoryByInfo(String pattern, int num) {
        try {
            return em.createQuery("select h from History h where lower(h.historyInfo) like :pat order by h.id desc", History.class)
                    .setParameter("pat", '%' + pattern.toLowerCase() + '%').setMaxResults(num).getResultList();
        } catch (NoResultException nrf) {
            return null;
        } catch(Exception e) {
            throw new DbException("Entity manager has failed - possible cause: universe exploded", e);
        }
    }
}
