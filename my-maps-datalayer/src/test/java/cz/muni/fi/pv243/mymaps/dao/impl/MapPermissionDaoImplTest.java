package cz.muni.fi.pv243.mymaps.dao.impl;

import cz.muni.fi.pv243.mymaps.entities.MapPermissionEntity;
import cz.muni.fi.pv243.mymaps.entities.MyMapEntity;
import cz.muni.fi.pv243.mymaps.entities.Permission;
import cz.muni.fi.pv243.mymaps.entities.UserEntity;
import java.util.ArrayList;
import java.util.Arrays;
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
public class MapPermissionDaoImplTest {

    @Inject
    private MapPermissionDaoImpl instance;

    @Deployment
    public static WebArchive createDeployment() {
        return TestUtils.createDeploymentArchive()
                .addClass(MapPermissionDaoImpl.class);
    }
    private UserEntity user1;
    private UserEntity user2;
    private UserEntity user3;
    private UserEntity userIdNull;
    private MyMapEntity map1;
    private MyMapEntity map2;
    private MyMapEntity map3;
    private MyMapEntity mapIdNull;

    @Before
    public void setUp() {
        instance.cache.clear();

        user1 = new UserEntity();
        user1.setId(1L);
        user1.setLogin("login");
        user1.setName("User 1");

        user2 = new UserEntity();
        user2.setId(2L);
        user2.setLogin("login2");
        user2.setName("User 2");
        user3 = new UserEntity();
        user3.setId(3L);
        user3.setLogin("login3");
        user3.setName("User 3");

        userIdNull = new UserEntity();
        userIdNull.setId(null);
        userIdNull.setLogin("login4");
        userIdNull.setName("User 4");

        map1 = new MyMapEntity();
        map1.setId(1L);
        map1.setName("Brno schools");

        map2 = new MyMapEntity();
        map2.setId(2L);
        map2.setName("Brno pubs");

        map3 = new MyMapEntity();
        map3.setId(3L);
        map3.setName("Brno geocaches");

        mapIdNull = new MyMapEntity();
        mapIdNull.setId(null);
        mapIdNull.setName("Brno free parking");

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
        MapPermissionEntity entity = createNewEntity();
        assertNull("Default id must be null.", entity.getId());

        MapPermissionEntity result = instance.create(entity);

        assertNotNull("Created entity id must be non-null.", result.getId());
        assertTrue(instance.cache.size() == 1);
        assertNotNull(instance.cache.get(result.getId()));
        assertEquals(entity, result);
        assertTrue(EqualsBuilder.reflectionEquals(entity, result));

        ArrayList<MapPermissionEntity> list = Collections.list(Collections.enumeration(instance.cache.values()));

        assertEquals(result, list.get(0));
        assertTrue(EqualsBuilder.reflectionEquals(result, list.get(0)));
    }

    @Test
    public void testDelete() {
        assertTrue(instance.cache.isEmpty());
        MapPermissionEntity entity = createNewEntity();

        instance.create(entity);

        assertFalse(instance.cache.isEmpty());
        assertTrue(instance.cache.size() == 1);

        instance.delete(entity);

        assertTrue(instance.cache.isEmpty());
    }

    @Test
    public void testUpdate() {

        assertTrue(instance.cache.isEmpty());
        MapPermissionEntity entity = createNewEntity();
        assertNull("Default id must be null.", entity.getId());

        instance.create(entity);
        assertNotNull("Created entity id must be non-null.", entity.getId());

        MapPermissionEntity updated = createUpdatedEntity(entity);
        MapPermissionEntity result = instance.update(updated);
        assertNotNull("Updated entity id must be non-null.", result.getId());
        assertEquals(updated, result);
        assertTrue(EqualsBuilder.reflectionEquals(updated, result));

        assertTrue(instance.cache.size() == 1);
        ArrayList<MapPermissionEntity> list = Collections.list(Collections.enumeration(instance.cache.values()));

        assertEquals(updated, list.get(0));
        assertTrue(EqualsBuilder.reflectionEquals(updated, list.get(0)));
    }

    @Test
    public void testGetById() {

        assertTrue(instance.cache.isEmpty());
        MapPermissionEntity entity = createNewEntity();

        MapPermissionEntity createdEntity = instance.create(entity);
        Long resultId = createdEntity.getId();

        assertNotNull(resultId);
        MapPermissionEntity result = instance.getById(resultId);
        assertNotNull(result);
        assertNotNull(result.getId());


        assertEquals(result, createdEntity);
        assertTrue(EqualsBuilder.reflectionEquals(result, createdEntity));

        assertEquals(instance.cache.get(resultId), createdEntity);
        assertTrue(EqualsBuilder.reflectionEquals(instance.cache.get(resultId), createdEntity));

    }

    @Test
    public void testGetUsersPermissions() {
        assertTrue(instance.cache.isEmpty());

        MapPermissionEntity entity1 = createNewEntity();
        entity1.setUser(user1);
        MapPermissionEntity createdEntity1 = instance.create(entity1);
        MapPermissionEntity entity2 = createNewEntity();
        entity2.setUser(user2);
        MapPermissionEntity createdEntity2 = instance.create(entity2);
        MapPermissionEntity entity3 = createNewEntity();
        entity1.setUser(user1);
        entity3.setMap(map2);
        MapPermissionEntity createdEntity3 = instance.create(entity3);

        List<MapPermissionEntity> user1Permissions = Arrays.asList(createdEntity3, createdEntity1);
        List<MapPermissionEntity> user2Permissions = Arrays.asList(createdEntity2);

        List<MapPermissionEntity> foundEntities;

        //user3 with no permisions
        foundEntities = instance.getUsersPermissions(user3);
        assertNotNull(foundEntities);
        assertEquals(foundEntities.size(), 0);

        //user1 permissions 
        foundEntities = instance.getUsersPermissions(user1);
        assertNotNull(foundEntities);
        assertEquals(foundEntities.size(), user1Permissions.size());

        for (MapPermissionEntity foundEntity : foundEntities) {
            boolean found = false;
            for (MapPermissionEntity mme : user1Permissions) {
                if (foundEntity.equals(mme) && EqualsBuilder.reflectionEquals(foundEntity, mme)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                fail("Created entity not found in result");
            }
        }


        //user2 permissions
        foundEntities = instance.getUsersPermissions(user2);
        assertNotNull(foundEntities);
        assertEquals(foundEntities.size(), user2Permissions.size());

        for (MapPermissionEntity foundEntity : foundEntities) {
            boolean found = false;
            for (MapPermissionEntity mme : user2Permissions) {
                if (foundEntity.equals(mme) && EqualsBuilder.reflectionEquals(foundEntity, mme)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                fail("Created entity not found in result");
            }
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetUsersPermissionsNull() {
        instance.getUsersPermissions(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetUsersPermissionsIdNull() {
        instance.getUsersPermissions(userIdNull);
    }

    @Test
    public void testGetMapsPermissions() {
        assertTrue(instance.cache.isEmpty());

        MapPermissionEntity entity1 = createNewEntity();
        entity1.setUser(user1);
        MapPermissionEntity createdEntity1 = instance.create(entity1);
        MapPermissionEntity entity2 = createNewEntity();
        entity2.setUser(user2);
        MapPermissionEntity createdEntity2 = instance.create(entity2);
        MapPermissionEntity entity3 = createNewEntity();
        entity1.setUser(user1);
        entity3.setMap(map2);
        MapPermissionEntity createdEntity3 = instance.create(entity3);

        List<MapPermissionEntity> map1Permissions = Arrays.asList(createdEntity2, createdEntity1);
        List<MapPermissionEntity> map2Permissions = Arrays.asList(createdEntity3);

        List<MapPermissionEntity> foundEntities;

        //map3 with no permisions
        foundEntities = instance.getMapsPermissions(map3);
        assertNotNull(foundEntities);
        assertEquals(0, foundEntities.size());

        //map1 permissions 
        foundEntities = instance.getMapsPermissions(map1);
        assertNotNull(foundEntities);
        assertEquals(map1Permissions.size(), foundEntities.size());

        for (MapPermissionEntity foundEntity : foundEntities) {
            boolean found = false;
            for (MapPermissionEntity mme : map1Permissions) {
                if (foundEntity.equals(mme) && EqualsBuilder.reflectionEquals(foundEntity, mme)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                fail("Created entity not found in result");
            }
        }


        //map2 permissions
        foundEntities = instance.getMapsPermissions(map2);
        assertNotNull(foundEntities);
        assertEquals(map2Permissions.size(), foundEntities.size());

        for (MapPermissionEntity foundEntity : foundEntities) {
            boolean found = false;
            for (MapPermissionEntity mme : map2Permissions) {
                if (foundEntity.equals(mme) && EqualsBuilder.reflectionEquals(foundEntity, mme)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                fail("Created entity not found in result");
            }
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetMapsPermissionsNull() {
        instance.getMapsPermissions(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetMapsPermissionsIdNull() {
        instance.getMapsPermissions(mapIdNull);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNull() {
        instance.create(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateIdNotNull() {
        MapPermissionEntity e = createNewEntity();
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

    private MapPermissionEntity createUpdatedEntity(MapPermissionEntity entity) {
        MapPermissionEntity updatedEntity = new MapPermissionEntity();

        updatedEntity.setId(entity.getId());
        updatedEntity.setMap(entity.getMap());
        updatedEntity.setUser(entity.getUser());
        updatedEntity.setPermission(Permission.WRITE);

        return updatedEntity;
    }

    private MapPermissionEntity createNewEntity() {
        MapPermissionEntity entity = new MapPermissionEntity();

        entity.setMap(map1);
        entity.setUser(user1);
        entity.setPermission(Permission.READ);

        return entity;
    }
}