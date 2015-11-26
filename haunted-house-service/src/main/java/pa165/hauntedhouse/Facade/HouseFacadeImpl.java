/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Facade;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import pa165.hauntedhouse.Dto.HouseDTO;
import pa165.hauntedhouse.Dto.SpookDTO;
import pa165.hauntedhouse.Entity.House;
import pa165.hauntedhouse.Entity.Spook;
import pa165.hauntedhouse.Service.HouseService;
import pa165.hauntedhouse.Service.SpookService;
import pa165.hauntedhouse.ServiceConfig.Service.BeanMappingService;

/**
 *
 * @author Milan
 */
public class HouseFacadeImpl implements HouseFacade {
    
    @Autowired
    private HouseService houseService;
    
    @Autowired
    private SpookService spookService;
    
    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public int createHouse(HouseDTO h) {
        return houseService.create(beanMappingService.mapTo(h, House.class));
    }

    @Override
    public void deleteHouse(int houseId) {
        houseService.delete(beanMappingService.mapTo(houseId, House.class));
    }

    @Override
    public void updateHouse(HouseDTO h) {
        houseService.update(beanMappingService.mapTo(h, House.class));
    }

    @Override
    public void addSpook(HouseDTO house, SpookDTO spook) {
        houseService.addToSpook(beanMappingService.mapTo(house, House.class), beanMappingService.mapTo(spook, Spook.class));
    }

    @Override
    public void removeSpook(HouseDTO house, SpookDTO spook) {
        houseService.removeFromSpook(beanMappingService.mapTo(house, House.class), beanMappingService.mapTo(spook, Spook.class));
    }

    @Override
    public List<HouseDTO> getAllHouses() {
        return beanMappingService.mapTo(houseService.findAll(), HouseDTO.class);
    }

    @Override
    public HouseDTO getHouseById(int id) {
        return beanMappingService.mapTo(houseService.findById(id), HouseDTO.class);
    }

    @Override
    public HouseDTO getSpookHouse(int spookId) {
        return beanMappingService.mapTo(spookService.getHouseBySpookId(spookId), HouseDTO.class);
    }

    @Override
    public List<HouseDTO> searchHousesByName(String filter) {
        return beanMappingService.mapTo(houseService.searchHousesByName(filter), HouseDTO.class);
    }
}
