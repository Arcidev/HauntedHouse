/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Iterface;

/**
 *
 * @author Andrej Dobes
 */
public interface ImageContainer {
    
    /**
     * Gets image as byte array
     * 
     * @return image as byte array
     */
    byte[] getImage();
    
    /**
     * Sets image from byte array
     * 
     * @param image
     */
    void setImage(byte[] image);
    
    /**
     * Gets image mime type
     * 
     * @return image mime type
     */
    String getImageMimeType();

    /**
     * Sets image mime type
     * 
     * @param imageMimeType
     */
    void setImageMimeType(String imageMimeType);
}
