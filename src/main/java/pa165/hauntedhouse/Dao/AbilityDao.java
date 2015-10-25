/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Dao;

import java.util.List;
import pa165.hauntedhouse.Entity.Ability;

/**
 *
 * @author Andrej Dobes
 */
public interface AbilityDao {
    public Ability findById(int id);
    public void create(Ability attr);
    public void delete(Ability attr);
    public List<Ability> findAll();
    public Ability findByName(String name);
}
