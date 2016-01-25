/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Andrej Dobes
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class AccessDenied extends RuntimeException {
    public AccessDenied(String message) {
        super(message);
    }
}
