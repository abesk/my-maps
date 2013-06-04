/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv243.mymaps.serviceImpl;

import cz.muni.fi.pv243.mymaps.dao.MapPermissionDao;
import cz.muni.fi.pv243.mymaps.dao.MyMapDao;
import cz.muni.fi.pv243.mymaps.dao.UserDao;
import cz.muni.fi.pv243.mymaps.dto.User;
import cz.muni.fi.pv243.mymaps.entities.MapPermissionEntity;
import cz.muni.fi.pv243.mymaps.entities.MyMapEntity;
import cz.muni.fi.pv243.mymaps.entities.UserEntity;
import cz.muni.fi.pv243.mymaps.service.UserService;
import cz.muni.fi.pv243.mymaps.util.Crypto;
import cz.muni.fi.pv243.mymaps.util.EntityDTOconvertor;
import cz.muni.fi.pv243.mymaps.util.PasswordGenerator;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.jboss.logging.Logger;

/**
 *
 * @author Kuba
 */
public class UserServiceImpl implements UserService {

    @Inject
    private UserDao userDao;
    @Inject
    private PasswordGenerator passwordGenerator;
    @Inject
    private MapPermissionDao mapPermissionDao;
    @Inject
    private MyMapDao myMapDao;
    @Inject
    protected Logger log;

    @Override
    public void createUser(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if (user == null) {
            String msg = "User can not be null.";
            log.error(msg);
            throw new IllegalArgumentException(msg);
        }
        try {
            String password = passwordGenerator.generatePassword(8, true, true);
            password = Crypto.encode(password);
            user.setPassword(password);

            UserEntity newUser = EntityDTOconvertor.convertUser(user);

            userDao.create(newUser);

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            log.error(e);
        }


    }

    @Override
    public void updateUser(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if (user == null) {
            String msg = "User can not be null.";
            log.error(msg);
            throw new IllegalArgumentException(msg);
        }

        if (!userDao.getById(user.getId()).getPasswordHash().equals(Crypto.encode(user.getPassword()))) {
            user.setPassword(Crypto.encode(user.getPassword()));
        }

        UserEntity newUser = EntityDTOconvertor.convertUser(user);

        userDao.update(newUser);
    }

    @Override
    public void deleteUser(User user) {
        if (user == null) {
            String msg = "User can not be null.";
            log.error(msg);
            throw new IllegalArgumentException(msg);
        }

        UserEntity newUser = EntityDTOconvertor.convertUser(user);

        List<MyMapEntity> userMaps = myMapDao.findMapsByCreator(newUser);

        for (MyMapEntity u : userMaps) {
            myMapDao.delete(u);
        }

        List<MapPermissionEntity> userPermissions = mapPermissionDao.getUsersPermissions(newUser);

        for (MapPermissionEntity p : userPermissions) {

            mapPermissionDao.delete(p);
        }

        userDao.delete(newUser);
    }

    @Override
    public User getUserById(Long id) {
        if (id == null) {
            String msg = "ID can not be null.";
            log.error(msg);
            throw new IllegalArgumentException(msg);
        }

        return EntityDTOconvertor.convertUser(userDao.getById(id));
    }

    @Override
    public User getUserByLogin(String login) {
        if (login == null) {
            String msg = "Login can not be null.";
            log.error(msg);
            throw new IllegalArgumentException(msg);
        }

        UserEntity user = userDao.getUserByLogin(login);

        return EntityDTOconvertor.convertUser(user);
    }

    @Override
    public List<User> findUsersByName(String name) {
        if (name == null) {
            String msg = "Name can not be null.";
            log.error(msg);
            throw new IllegalArgumentException(msg);
        }

        List<User> userList = new ArrayList<>();
        List<UserEntity> users = userDao.findUsersByName(name);
        for (UserEntity u : users) {
            userList.add(EntityDTOconvertor.convertUser(u));
        }

        return userList;
    }

    @Override
    public List<User> findUsersByRole(String role) {
        if (role == null) {
            String msg = "Role can not be null.";
            log.error(msg);
            throw new IllegalArgumentException(msg);
        }

        List<User> userList = new ArrayList<>();
        List<UserEntity> users = userDao.findUsersByRole(role);
        for (UserEntity u : users) {
            userList.add(EntityDTOconvertor.convertUser(u));
        }

        return userList;
    }

    @Override
    public List<User> geAllUsers() {

        List<User> userList = new ArrayList<>();
        List<UserEntity> users = new ArrayList<>();
        //users = userDao.getAllUsers();
        for (UserEntity u : users) {
            userList.add(EntityDTOconvertor.convertUser(u));
        }

        return userList;
    }
}