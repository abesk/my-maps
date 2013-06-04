package cz.muni.fi.pv243.mymaps.dao;

import cz.muni.fi.pv243.mymaps.entities.UserEntity;
import java.util.List;

/**
 * DAO layer for User entity.
 * 
 * @author Jiri Holusa
 */
public interface UserDao extends GenericDao<UserEntity> {
    
    /**
     * Retrieves user whose login is equal to login suplied.
     * 
     * @throws IllegalStateException when data layer is not available due to internal error
     * @throws IllegalArgumentException when login == null or login.length == 0
     * @param login login of the user to be retrieved
     * @return corresponding user or null if user not found
     */
    UserEntity getUserByLogin(String login);
    
    /**
     * Retrieves list of users whose names are equal to name suplied.
     * 
     * @throws IllegalStateException when data layer is not available due to internal error
     * @throws IllegalArgumentException when name == null or name.length == 0
     * @param name name of the users to be retrieved
     * @return list of users with name equal to the name suplied
     */
    List<UserEntity> findUsersByName(String name);
    
    /**
     * Retrieves list of users whose role are equal to name suplied.
     * 
     * @throws IllegalStateException when data layer is not available due to internal error
     * @throws IllegalArgumentException when role == null or role.length == 0
     * @param role role of the users to be retrieved
     * @return list of users with given role
     */
    List<UserEntity> findUsersByRole(String role);
    
}
