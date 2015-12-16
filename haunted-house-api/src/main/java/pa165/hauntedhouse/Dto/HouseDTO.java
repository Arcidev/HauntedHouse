/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Dto;

import pa165.hauntedhouse.Iterface.ImageContainer;

/**
 *
 * @author Milan Matijka
 */
public class HouseDTO extends HouseInfoDTO  implements ImageContainer {
    private byte[] image;
    private String imageMimeType;

    @Override
    public byte[] getImage() {
        return image;
    }
    
    @Override
    public void setImage(byte[] image) {
        this.image = image;
    }
    
    @Override
    public String getImageMimeType() {
        return imageMimeType;
    }

    @Override
    public void setImageMimeType(String imageMimeType) {
        this.imageMimeType = imageMimeType;
    }
}
