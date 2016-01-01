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
        return house.getId();
    }

    @Override
    public void update(House house) {
        House h = houseDao.findById(house.getId());
        if(h ==null) {
            throw new IllegalArgumentException("House does not exist");
        }
        h.setAddress(house.getAddress());
        h.setName(house.getName());
        h.setHauntedSince(house.getHauntedSince());
        h.setHistory(house.getHistory());
        if(house.getImage() !=null){
            h.setImage(house.getImage());
            h.setImageMimeType(h.getImageMimeType());
        }
        
        houseDao.update(h);
    }

    @Override
    public void delete(int id) {
        houseDao.delete(id);
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
    public List<House> searchHousesByName(String filter, boolean visible) {
        List<House> houses = houseDao.searchByName(filter, visible);
        if (houses == null) {
            return new ArrayList<>();
        }
        return houses;
    }

    @Override
    public void addToSpook(int houseId, int spookId) {
        House h = houseDao.findById(houseId);
        Spook s = spookDao.findById(spookId);
        if (h == null || s == null) {
            throw new IllegalArgumentException("House or Spook does not exist with passed ids");
        }
        h.addSpook(s);
        spookDao.update(s);
    }

    @Override
    public void removeFromSpook(int houseId, int spookId) {
        House h = houseDao.findById(houseId);
        Spook s = spookDao.findById(spookId);
        if (h == null || s == null) {
            throw new IllegalArgumentException("House or Spook does not exist with passed ids");
        }
        h.removeSpook(s);
        spookDao.update(s);
    }

    @Override
    public void setVisible(int houseId, boolean visible) {
        House h = houseDao.findById(houseId);
        if (h == null) {
            throw new IllegalArgumentException("House does not exist");
        }
        h.setVisible(visible);
        houseDao.update(h);
    }

    @Override
    public List<House> findAllByVisibility(boolean visible) {
        return houseDao.findAllByVisibility(visible);
    }
    
    @Override
    public List<Spook> getSpooksByHouseId(int houseId){
    House house = houseDao.findById(houseId);
        if (house == null) {
            return new ArrayList<>();
        }

        ArrayList<Spook> spooks = new ArrayList<>(house.getSpooks());
        spooks.removeIf(x -> !x.isVisible());
        return spooks;
    }
}
