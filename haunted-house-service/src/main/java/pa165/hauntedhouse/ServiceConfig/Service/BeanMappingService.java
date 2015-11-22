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
    public <T> List<T> mappTo(Collection<Object> source, Class<T> mapToClass);
    public <T> T mapTo(Object source, Class<T> output);
    
    public Mapper getMapper();
}
