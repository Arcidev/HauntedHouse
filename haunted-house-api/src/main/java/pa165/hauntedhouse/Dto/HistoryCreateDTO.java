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
public class HistoryCreateDTO extends HistoryDTO {
    private SpookDTO spook;
    
    public SpookDTO getSpook(){
	return spook;
    }
    
    public void setSpook(SpookDTO spook){
	this.spook=spook;
    }
}
