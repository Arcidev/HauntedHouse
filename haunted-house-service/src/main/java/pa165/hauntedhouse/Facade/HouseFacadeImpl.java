/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Facade;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pa165.hauntedhouse.Dto.HouseDTO;
import pa165.hauntedhouse.Dto.HouseInfoDTO;
import pa165.hauntedhouse.Entity.House;
import pa165.hauntedhouse.Service.HouseService;
import pa165.hauntedhouse.Service.SpookService;
import pa165.hauntedhouse.ServiceConfig.Service.BeanMappingService;

/**
 *
 * @author Milan
 */
@Service
@Transactional
public class HouseFacadeImpl implements HouseFacade {
    
    @Autowired
    private HouseService houseService;
    
    @Autowired
    private SpookService spookService;
    
    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public void createHouse(HouseDTO h) {
        h.setId(houseService.create(beanMappingService.mapTo(h, House.class)));
    }

    @Override
    public void deleteHouse(int houseId) {
        houseService.delete(houseId);
    }

    @Override
    public void updateHouse(HouseDTO h) {
        houseService.update(beanMappingService.mapTo(h, House.class));
    }

    @Override
    public void addSpook(int houseId, int spookId) {
        houseService.addToSpook(houseId, spookId);
    }

    @Override
    public void removeSpook(int houseId, int spookId) {
        houseService.removeFromSpook(houseId, spookId);
    }

    @Override
    public List<HouseDTO> getAllHouses() {
        return beanMappingService.mapTo(houseService.findAll(), HouseDTO.class);
    }

    @Override
    public HouseDTO getHouseById(int id) {
        House h = houseService.findById(id);
        if (h == null) {
            return null;
        } 
        return beanMappingService.mapTo(h, HouseDTO.class);
    }

    @Override
    public HouseDTO getSpookHouse(int spookId) {
        House house = spookService.getHouseBySpookId(spookId);
        if (house == null) {
            return null;
        }
        return beanMappingService.mapTo(house, HouseDTO.class);
    }

    @Override
    public List<HouseDTO> searchHousesByName(String filter) {
        return beanMappingService.mapTo(houseService.searchHousesByName(filter), HouseDTO.class);
    }

    @Override
    public List<HouseInfoDTO> getAllHouseInfoesByVisibility(boolean visible) {
        return beanMappingService.mapTo(houseService.findAllByVisibility(visible), HouseInfoDTO.class);
    }

    @Override
    public HouseInfoDTO getHouseInfoById(int id) {
        House h = houseService.findById(id);
        if (h == null) {
            return null;
        }
        
        return beanMappingService.mapTo(h, HouseInfoDTO.class);
    }

    @Override
    public void setVisible(int houseId, boolean visible) {
        houseService.setVisible(houseId,visible);
    }
}
