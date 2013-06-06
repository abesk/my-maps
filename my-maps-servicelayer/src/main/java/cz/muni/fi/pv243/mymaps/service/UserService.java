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

    public String createUser(User user);

    public void updateUser(User user);

    public void deleteUser(User user);
    
    public User getUserById(Long id);
    
    public User getUserByLogin(String login);
    
    public List<User> findUsersByName(String name);
    
    public List<User> findUsersByRole(String role);

    public List<User> geAllUsers();
}
