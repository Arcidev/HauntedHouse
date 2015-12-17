/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Config;

import java.util.ArrayList;
import java.util.List;
import javax.naming.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import pa165.hauntedhouse.Dto.PersonDTO;
import pa165.hauntedhouse.Facade.PersonFacade;

/**
 *
 * @author Andrej Dobes
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
 
    @Autowired
    private PersonFacade personFacade;
    
    @Override
    public Authentication authenticate(Authentication authentication) {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        PersonDTO person = personFacade.authenticate(email, password);
        if (person != null) {
            List<GrantedAuthority> grantedAuths = new ArrayList<>();
            grantedAuths.add(new SimpleGrantedAuthority(person.getUserRole().toString()));
            Authentication auth = new UsernamePasswordAuthenticationToken(email, password, grantedAuths);
            return auth;
        } else {
            return null;
        }
    }
 
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}