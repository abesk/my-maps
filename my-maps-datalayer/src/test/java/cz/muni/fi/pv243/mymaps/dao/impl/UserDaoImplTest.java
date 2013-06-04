package cz.muni.fi.pv243.mymaps.dao.impl;

import cz.muni.fi.pv243.mymaps.entities.UserEntity;
import cz.muni.fi.pv243.mymaps.entities.ViewEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;

/**
 *
 * @author Jan Bliznak
 */
@RunWith(Arquillian.class)
public class UserDaoImplTest {

    @Inject
    private UserDaoImpl instance;

    @Deployment
    public static WebArchive createDeployment() {
        return TestUtils.createDeploymentArchive()
                .addClass(UserDaoImpl.class);
    }

    @Before
    public void setUp() {
        instance.cache.clear();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testInjection() {
        assertNotNull("Injection of cache not succeeded.", instance);
    }

    @Test
    public void testCreate() {
        assertTrue(instance.cache.isEmpty());
        UserEntity entity = createNewEntity();
        assertNull("Default id must be null.", entity.getId());

        UserEntity result = instance.create(entity);

        assertNotNull("Created entity id must be non-null.", result.getId());
        assertTrue(instance.cache.size() == 1);
        assertNotNull(instance.cache.get(result.getId()));
        assertEquals(entity, result);
        assertTrue(EqualsBuilder.reflectionEquals(entity, result));

        ArrayList<UserEntity> list = Collections.list(Collections.enumeration(instance.cache.values()));

        assertEquals(result, list.get(0));
        assertTrue(EqualsBuilder.reflectionEquals(result, list.get(0)));
    }

    @Test
    public void testDelete() {
        assertTrue(instance.cache.isEmpty());
        UserEntity entity = createNewEntity();

        instance.create(entity);

        assertFalse(instance.cache.isEmpty());
        assertTrue(instance.cache.size() == 1);

        instance.delete(entity);

        assertTrue(instance.cache.isEmpty());
    }

    @Test
    public void testUpdate() {

        assertTrue(instance.cache.isEmpty());
        UserEntity entity = createNewEntity();
        assertNull("Default id must be null.", entity.getId());

        instance.create(entity);
        assertNotNull("Created entity id must be non-null.", entity.getId());

        UserEntity updated = createUpdatedEntity(entity);
        UserEntity result = instance.update(updated);
        assertNotNull("Updated entity id must be non-null.", result.getId());
        assertEquals(updated, result);
        assertTrue(EqualsBuilder.reflectionEquals(updated, result));

        assertTrue(instance.cache.size() == 1);
        ArrayList<UserEntity> list = Collections.list(Collections.enumeration(instance.cache.values()));

        assertEquals(updated, list.get(0));
        assertTrue(EqualsBuilder.reflectionEquals(updated, list.get(0)));
    }

    @Test
    public void testGetById() {

        assertTrue(instance.cache.isEmpty());
        UserEntity entity = createNewEntity();

        UserEntity createdEntity = instance.create(entity);
        Long resultId = createdEntity.getId();

        assertNotNull(resultId);
        UserEntity result = instance.getById(resultId);
        assertNotNull(result);
        assertNotNull(result.getId());


        assertEquals(result, createdEntity);
        assertTrue(EqualsBuilder.reflectionEquals(result, createdEntity));

        assertEquals(instance.cache.get(resultId), createdEntity);
        assertTrue(EqualsBuilder.reflectionEquals(instance.cache.get(resultId), createdEntity));

    }

    public void testGetUserByLogin() {
        assertTrue(instance.cache.isEmpty());
        UserEntity entity = createNewEntity();
        entity.setLogin("login");

        UserEntity createdEntity = instance.create(entity);
        Long resultId = createdEntity.getId();
        assertNotNull(resultId);


        assertNull(instance.getUserByLogin("xlogin2"));

        UserEntity result = instance.getUserByLogin("login");
        assertNotNull(result);
        assertNotNull(result.getId());


        assertEquals(result, createdEntity);
        assertTrue(EqualsBuilder.reflectionEquals(result, createdEntity));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetUserByLoginNull() {
        instance.getUserByLogin(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetUserByLoginEmpty() {
        instance.getUserByLogin("");
    }

    @Test
    public void testFindUsersByName() {

        assertTrue(instance.cache.isEmpty());
        UserEntity entity = createNewEntity();
        entity.setName("test1");
        UserEntity entity2 = createNewEntity();
        entity2.setName("test2");
        UserEntity entity3 = createNewEntity();
        entity3.setName("test3");

        instance.create(entity);
        UserEntity createdEntity2 = instance.create(entity2);
        instance.create(entity3);

        List<UserEntity> foundEntities;

        foundEntities = instance.findUsersByName("xxxx");

        assertNotNull(foundEntities);
        assertEquals(0, foundEntities.size());

        foundEntities = instance.findUsersByName("test2");

        assertNotNull(foundEntities);
        assertEquals(1, foundEntities.size());

        assertEquals(createdEntity2, foundEntities.get(0));
        assertTrue(EqualsBuilder.reflectionEquals(createdEntity2, foundEntities.get(0)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindUsersByNameNull() {
        instance.findUsersByName(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindUsersByNameEmpty() {
        instance.findUsersByName("");
    }

    @Test
    public void testFindUsersByRole() {

        assertTrue(instance.cache.isEmpty());
        UserEntity entity = createNewEntity();
        entity.setRole("test1");
        UserEntity entity2 = createNewEntity();
        entity2.setRole("test2");
        UserEntity entity3 = createNewEntity();
        entity3.setRole("test3");

        instance.create(entity);
        UserEntity createdEntity2 = instance.create(entity2);
        instance.create(entity3);

        List<UserEntity> foundEntities;

        foundEntities = instance.findUsersByRole("xxxx");

        assertNotNull(foundEntities);
        assertEquals(0, foundEntities.size());

        foundEntities = instance.findUsersByRole("test2");

        assertNotNull(foundEntities);
        assertEquals(1, foundEntities.size());

        assertEquals(createdEntity2, foundEntities.get(0));
        assertTrue(EqualsBuilder.reflectionEquals(createdEntity2, foundEntities.get(0)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindUsersByRoleNull() {
        instance.findUsersByRole(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindUsersByRoleEmpty() {
        instance.findUsersByRole("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNull() {
        instance.create(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateIdNotNull() {
        UserEntity e = createNewEntity();
        e.setId(1L);
        instance.create(e);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateNull() {
        instance.update(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateIdNull() {
        instance.update(createNewEntity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteNull() {
        instance.delete(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteIdNull() {
        instance.delete(createNewEntity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByIdNull() {
        instance.getById(null);
    }

    private UserEntity createUpdatedEntity(UserEntity entity) {

        UserEntity updatedEntity = new UserEntity();

        updatedEntity.setId(entity.getId());
        updatedEntity.setLogin("loginxy");
        updatedEntity.setName("other user name");
        updatedEntity.setPasswordHash("1234567");
        updatedEntity.setRole("user");
        updatedEntity.setViews(new ArrayList<ViewEntity>());

        return updatedEntity;
    }

    private UserEntity createNewEntity() {
        UserEntity userEntity = new UserEntity();

        userEntity.setLogin("login");
        userEntity.setName("user name");
        userEntity.setPasswordHash("123456");
        userEntity.setRole("admin");
        userEntity.setViews(new ArrayList<ViewEntity>());

        return userEntity;
    }
}