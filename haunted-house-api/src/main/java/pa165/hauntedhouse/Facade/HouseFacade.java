/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Facade;

import java.util.List;
import pa165.hauntedhouse.Dto.HouseDTO;

/**
 *
 * @author Andrej Dobes
 */
public interface HouseFacade {

    /**
     * Creates new house
     * 
     * @param h
     * @return id of newly created house
     */
    int createHouse(HouseDTO h);

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
     * @return houses matched by filter
     */
    List<HouseDTO> searchHousesByName(String filterName);
}
