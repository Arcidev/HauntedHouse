/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Exception;

import org.springframework.dao.DataAccessException;

/**
 *
 * @author Andrej Dobes
 */
public class DbException extends DataAccessException {

	public DbException(String message, Throwable cause) {
		super(message, cause);
	}

	public DbException(String message) {
		super(message);
	}
}
