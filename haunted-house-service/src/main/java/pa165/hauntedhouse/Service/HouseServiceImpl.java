/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pa165.hauntedhouse.Dao.HouseDao;
import pa165.hauntedhouse.Dao.SpookDao;
import pa165.hauntedhouse.Entity.House;
import pa165.hauntedhouse.Entity.Spook;

/**
 *
 * @author Milan
 */
@Service
public class HouseServiceImpl implements HouseService {
    
    @Autowired
    private HouseDao houseDao;
    
    @Autowired
    private SpookDao spookDao;

    @Override
    public int create(House house) {
        houseDao.create(house);
        System.out.println("ID "+house.getName()+" "+house.getAddress()+ " "+house.getHistory()+ " " +house.getId());
        return house.getId();
    }

    @Override
    public void update(House house) {
        houseDao.update(house);
    }

    @Override
    public void delete(int id) {
        House h = houseDao.findById(id);
        houseDao.delete(h);
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

    @Override
    public void addToSpook(int houseId, int spookId) {
        House h = houseDao.findById(houseId);
        Spook s = spookDao.findById(spookId);
        h.addSpook(s);
        spookDao.update(s);
    }

    @Override
    public void removeFromSpook(int houseId, int spookId) {
        House h = houseDao.findById(houseId);
        Spook s = spookDao.findById(spookId);
        h.removeSpook(s);
        spookDao.update(s);
    }
}
