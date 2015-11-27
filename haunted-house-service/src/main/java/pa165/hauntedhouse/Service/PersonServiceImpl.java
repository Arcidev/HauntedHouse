/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pa165.hauntedhouse.Dao.PersonDao;
import pa165.hauntedhouse.Entity.Person;
import pa165.hauntedhouse.Enums.UserRole;

/**
 *
 * @author Andrej Dobes
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDao personDao;
    
    @Override
    public int create(Person p, String password) {
        p.setPasswordHash(createHash(password));
        personDao.create(p);
        return p.getId();
    }
    
    @Override
    public void update(Person p, String newPassword) {
        Person person = findById(p.getId());
        person.setEmail(p.getEmail());
        person.setFirstName(p.getFirstName());
        person.setLastName(p.getLastName());
        person.setUserRole(p.getUserRole());
        if (newPassword != null && !newPassword.isEmpty()) {
            p.setPasswordHash(newPassword);
        }
        personDao.update(p);
    }
    
    @Override
    public void delete(int id) {
        Person person = findById(id);
        if (person == null) {
            throw new IllegalArgumentException("House does not exist");
        }
        personDao.delete(person);
    }

    @Override
    public Person findById(int id) {
        return personDao.findById(id);
    }
    
    @Override
    public boolean isAdmin(int id) {
        return findById(id).getUserRole() == UserRole.ADMIN;
    }

    @Override
    public Person findPersonByEmail(String email) {
        return personDao.findPersonByEmail(email);
    }

    @Override
    public List<Person> findAll() {
        return personDao.findAll();
    }

    @Override
    public List<Person> searchByLastName(String filter) {
        return personDao.searchByLastName(filter);
    }
    
    @Override
    public Person authenticate(String email, String password) {
        Person person = findPersonByEmail(email);
        if (person == null) {
            return null;
        }
        
        return validatePassword(password, person.getPasswordHash()) ? person : null;
    }
    
    //see  https://crackstation.net/hashing-security.htm#javasourcecode
    private static String createHash(String password) {
        final int SALT_BYTE_SIZE = 24;
        final int HASH_BYTE_SIZE = 24;
        final int PBKDF2_ITERATIONS = 1000;
        // Generate a random salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_BYTE_SIZE];
        random.nextBytes(salt);
        // Hash the password
        byte[] hash = pbkdf2(password.toCharArray(), salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE);
        // format iterations:salt:hash
        return PBKDF2_ITERATIONS + ":" + toHex(salt) + ":" + toHex(hash);
    }

    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes) {
        try {
            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
            return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] fromHex(String hex) {
        byte[] binary = new byte[hex.length() / 2];
        for (int i = 0; i < binary.length; i++) {
            binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return binary;
    }

    private static String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        return paddingLength > 0 ? String.format("%0" + paddingLength + "d", 0) + hex : hex;
    }

    /**
     * Compares two byte arrays in length-constant time. This comparison method
     * is used so that password hashes cannot be extracted from an on-line
     * system using a timing attack and then attacked off-line.
     *
     * @param a the first byte array
     * @param b the second byte array
     * @return true if both byte arrays are the same, false if not
     */
    private static boolean slowEquals(byte[] a, byte[] b) {
        int diff = a.length ^ b.length;
        for (int i = 0; i < a.length && i < b.length; i++)
            diff |= a[i] ^ b[i];
        return diff == 0;
    }

    public static boolean validatePassword(String password, String correctHash) {
        if(password==null) return false;
        if(correctHash==null) throw new IllegalArgumentException("password hash is null");
        String[] params = correctHash.split(":");
        int iterations = Integer.parseInt(params[0]);
        byte[] salt = fromHex(params[1]);
        byte[] hash = fromHex(params[2]);
        byte[] testHash = pbkdf2(password.toCharArray(), salt, iterations, hash.length);
        return slowEquals(hash, testHash);
    }
}
