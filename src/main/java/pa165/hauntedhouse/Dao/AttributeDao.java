/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Dao;

import java.util.List;
import pa165.hauntedhouse.Entity.Attribute;

/**
 *
 * @author Andrej Dobes
 */
public interface AttributeDao {
    public Attribute findById(int id);
    public void create(Attribute attr);
    public void delete(Attribute attr);
    public List<Attribute> findAll();
    public Attribute findByName(String name);
}
