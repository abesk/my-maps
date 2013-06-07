/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv243.mymaps.service;

import cz.muni.fi.pv243.mymaps.dto.User;
import java.util.List;

/**
 *
 * @author Kuba
 */
public interface UserService {

    /**
     * Creates new user
     *
     * @param user user to be created
     * @throws IllegalArgumentException if user is null
     * @throws IllegalStateException in case of internal error
     * @return newly created user with unhashed password
     */
    public User createUser(User user);

    /**
     * Updates existing user
     *
     * @param user user to be updated
     * @throws IllegalArgumentException if user is null
     * @throws IllegalStateException in case of internal error
     * @return updated user
     */
    public User updateUser(User user);

    /**
     * Deletes user, all his/hers maps and all associated permissions
     *
     * @param user user to be deleted
     * @throws IllegalArgumentException if user is null
     */
    public void deleteUser(User user);

    /**
     * Returns user with specified ID
     *
     * @param id id of user
     * @throws IllegalArgumentException if id is null
     * @return user with specified id
     */
    public User getUserById(Long id);

    /**
     * Returns user with specified login
     *
     * @param login login of user
     * @throws IllegalArgumentException if login is null
     * @return user with specified login
     */
    public User getUserByLogin(String login);

    /**
     * Returns list of users with specified name
     *
     * @param name name of user to be found
     * @throws IllegalArgumentException if name is null
     * @return list of found users
     */
    public List<User> findUsersByName(String name);

    /**
     * Returns list of users with specified role
     *
     * @param role specified role
     * @throws IllegalArgumentException if role is null
     * @return list of found users
     */
    public List<User> findUsersByRole(String role);

    /**
     * Returns all users
     *
     * @return list of all users
     */
    public List<User> geAllUsers();
}
