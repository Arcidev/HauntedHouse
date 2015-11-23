/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.ServiceConfig.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Andrej Dobes
 */
@Service
public class BeanMappingServiceImpl implements BeanMappingService {
    @Autowired
    private Mapper mapper;
    
    @Override
    public <T> List<T> mapTo(Collection<?> source, Class<T> mapToClass) {
        List<T> result = new ArrayList<>();
        for(Object o : source){
            result.add(mapTo(o, mapToClass));
        }
        
        return result;
    }

    @Override
    public <T> T mapTo(Object source, Class<T> mapToClass) {
        return mapper.map(source, mapToClass);
    }

    @Override
    public Mapper getMapper() {
        return mapper;
    }
}
