/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import pa165.hauntedhouse.Dao.HouseDao;
import pa165.hauntedhouse.Entity.House;

/**
 *
 * @author Milan
 */
public class HouseServiceImpl implements HouseService {
    
    @Autowired
    private HouseDao houseDao;

    @Override
    public int create(House house) {
        houseDao.create(house);
        return house.getId();
    }

    @Override
    public void update(House house) {
        houseDao.update(house);
    }

    @Override
    public void delete(House house) {
        houseDao.delete(house);
    }

    @Override
    public House findById(int id) {
        return houseDao.findById(id);
    }

    @Override
    public List<House> findAll() {
        return houseDao.findAll();
    }    

    @Override
    public List<House> searchHousesByName(String filter) {
        List<House> houses = houseDao.searchByName(filter);
        if (houses == null) {
            return new ArrayList<>();
        }
        return houses;
    }
}
