/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.ServiceConfig.Service;

import java.util.Collection;
import java.util.List;
import org.dozer.Mapper;

/**
 *
 * @author Andrej Dobes
 */
public interface BeanMappingService {

    /**
     * Maps list of objects to specified type
     * 
     * @param <T>
     * @param source
     * @param mapToClass
     * @return mapped objects
     */
    public <T> List<T> mapTo(Collection<?> source, Class<T> mapToClass);

    /**
     * Maps object into specified type
     * @param <T>
     * @param source
     * @param output
     * @return mapped object
     */
    public <T> T mapTo(Object source, Class<T> output);
    
    public Mapper getMapper();
}
