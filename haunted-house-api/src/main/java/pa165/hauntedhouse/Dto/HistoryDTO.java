/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Dto;

/**
 *
 * @author Andrej Dobes
 */
public class HistoryDTO extends HistoryInfoDTO {
    private int spookId;
    
    public void setSpookId(int id) {
        spookId = id;
    }
    
    public int getSpookId() {
        return spookId;
    }
}
