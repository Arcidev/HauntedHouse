/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.ServiceConfig;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import pa165.hauntedhouse.Dto.*;
import pa165.hauntedhouse.Entity.*;
import pa165.hauntedhouse.PersistenceConfig.PersistenceApplicationContext;

/**
 *
 * @author Andrej Dobes
 */
@Configuration
@Import(PersistenceApplicationContext.class)
@ComponentScan(basePackages = { "pa165.hauntedhouse.ServiceConfig.Service", "pa165.hauntedhouse.Facade", "pa165.hauntedhouse.Service" })
public class ServiceConfiguration {

    @Bean
    public Mapper dozer() {
        DozerBeanMapper dozer = new DozerBeanMapper();
        dozer.addMapping(new DozerCustomConfig());
        return dozer;
    }

    public class DozerCustomConfig extends BeanMappingBuilder {

        @Override
        protected void configure() {
            mapping(Ability.class, AbilityDTO.class);
            mapping(History.class, HistoryDTO.class);
            mapping(House.class, HouseDTO.class);
            mapping(Spook.class, SpookDTO.class);
            mapping(Person.class, PersonDTO.class);
        }
    }
}
