/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Facade;

import java.util.List;
import pa165.hauntedhouse.Dto.HouseDTO;
import pa165.hauntedhouse.Dto.HouseInfoDTO;

/**
 *
 * @author Andrej Dobes
 */
public interface HouseFacade {

    /**
     * Creates new house
     * 
     * @param h
     */
    void createHouse(HouseDTO h);

    /**
     * Removes existing house by id
     * 
     * @param houseId
     */
    void deleteHouse(int houseId);

    /**
     * Updates existing house
     * 
     * @param h
     */
    void updateHouse(HouseDTO h);

    /**
     * Adds spook to house
     * 
     * @param houseId
     * @param spookId
     */
    void addSpook(int houseId, int spookId);

    /**
     * Removes spook from house
     * 
     * @param houseId
     * @param spookId
     */
    void removeSpook(int houseId, int spookId);
    
    /**
     * Sets house visibility
     * 
     * @param houseId
     * @param visible
     */
    void setVisible(int houseId, boolean visible);

    /**
     * Gets all houses
     * 
     * @return all houses
     */
    List<HouseDTO> getAllHouses();

    /**
     *
     * @param id
     * @return
     */
    HouseDTO getHouseById(int id);

    /**
     * Gets house by id
     * 
     * @param spookId
     * @return house by id
     */
    HouseDTO getSpookHouse(int spookId);

    /**
     * Finds all houses which name matches the filter
     * 
     * @param filterName
     * @param visible
     * @return houses matched by filter
     */
    List<HouseDTO> searchHousesByName(String filterName, boolean visible);
    
    /**
     * Gets house info by id
     * 
     * @param id
     * @return house info by id
     */
    HouseInfoDTO getHouseInfoById(int id);
    
    /**
     * Gets all houses that are stored in database based on their visibility
     * 
     * @param visible
     * @return all visible or invisible houses
     */
    List<HouseInfoDTO> getAllHouseInfoesByVisibility(boolean visible);
}
