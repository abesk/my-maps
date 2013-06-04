package cz.muni.fi.pv243.mymaps.dao.impl;

import cz.muni.fi.pv243.mymaps.entities.MapType;
import cz.muni.fi.pv243.mymaps.entities.MyMapEntity;
import cz.muni.fi.pv243.mymaps.entities.PointOfInterestEntity;
import cz.muni.fi.pv243.mymaps.entities.UserEntity;
import cz.muni.fi.pv243.mymaps.entities.ViewEntity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
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
public class MyMapDaoImplTest {

    @Inject
    private MyMapDaoImpl instance;

    @Deployment
    public static WebArchive createDeployment() {
        return TestUtils.createDeploymentArchive()
                .addClass(MyMapDaoImpl.class);
    }
    private UserEntity user1;
    private UserEntity user2;
    private UserEntity user3;
    private ViewEntity view1;
    private ViewEntity view2;

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

        view1 = new ViewEntity();
        view1.setName("Brno");
        view1.setMapType(MapType.HYBRID);

        view1 = new ViewEntity();
        view1.setName("Brno neighbourhood");
        view1.setMapType(MapType.SATELLITE);
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
        MyMapEntity entity = createNewEntity();
        assertNull("Default id must be null.", entity.getId());

        MyMapEntity result = instance.create(entity);

        assertNotNull("Created entity id must be non-null.", result.getId());
        assertTrue(instance.cache.size() == 1);
        assertNotNull(instance.cache.get(result.getId()));
        assertEquals(entity, result);
        EqualsBuilder.reflectionEquals(entity, result);

        ArrayList<MyMapEntity> list = Collections.list(Collections.enumeration(instance.cache.values()));

        assertEquals(result, list.get(0));
        EqualsBuilder.reflectionEquals(result, list.get(0));
    }

    @Test
    public void testDelete() {
        assertTrue(instance.cache.isEmpty());
        MyMapEntity entity = createNewEntity();

        instance.create(entity);

        assertFalse(instance.cache.isEmpty());
        assertTrue(instance.cache.size() == 1);

        instance.delete(entity);

        assertTrue(instance.cache.isEmpty());
    }

    @Test
    public void testUpdate() {

        assertTrue(instance.cache.isEmpty());
        MyMapEntity entity = createNewEntity();
        assertNull("Default id must be null.", entity.getId());

        instance.create(entity);
        assertNotNull("Created entity id must be non-null.", entity.getId());

        MyMapEntity updated = createUpdatedEntity(entity);
        MyMapEntity result = instance.update(updated);
        assertNotNull("Updated entity id must be non-null.", result.getId());
        assertEquals(updated, result);
        EqualsBuilder.reflectionEquals(updated, result);

        assertTrue(instance.cache.size() == 1);
        ArrayList<MyMapEntity> list = Collections.list(Collections.enumeration(instance.cache.values()));

        assertEquals(updated, list.get(0));
        EqualsBuilder.reflectionEquals(updated, list.get(0));
    }

    @Test
    public void testGetById() {

        assertTrue(instance.cache.isEmpty());
        MyMapEntity entity = createNewEntity();

        MyMapEntity createdEntity = instance.create(entity);
        Long resultId = createdEntity.getId();

        assertNotNull(resultId);
        MyMapEntity result = instance.getById(resultId);
        assertNotNull(result);
        assertNotNull(result.getId());


        assertEquals(result, createdEntity);
        EqualsBuilder.reflectionEquals(result, createdEntity);

        assertEquals(instance.cache.get(resultId), createdEntity);
        EqualsBuilder.reflectionEquals(instance.cache.get(resultId), createdEntity);

    }

    @Test
    public void testFindMapsByName() {

        assertTrue(instance.cache.isEmpty());
        MyMapEntity entity = createNewEntity();
        entity.setName("test1");
        MyMapEntity entity2 = createNewEntity();
        entity2.setName("test2");
        MyMapEntity entity3 = createNewEntity();
        entity3.setName("test3");

        instance.create(entity);
        MyMapEntity createdEntity2 = instance.create(entity2);
        instance.create(entity3);

        List<MyMapEntity> foundEntities = instance.findMapsByName("test2");

        assertNotNull(foundEntities);
        assertEquals(foundEntities.size(), 1);

        assertEquals(foundEntities.get(0), createdEntity2);
        assertTrue(EqualsBuilder.reflectionEquals(foundEntities.get(0), createdEntity2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindMapsByNameNull() {
        instance.findMapsByName(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindMapsByNameEmpty() {
        instance.findMapsByName("");
    }

    @Test
    public void testFindMapsByCreationDate() {

        Date d1 = new GregorianCalendar(2013, 6, 3).getTime();
        Date d2 = new GregorianCalendar(2013, 2, 3).getTime();
        Date d3 = new GregorianCalendar(2010, 2, 3).getTime();

        assertTrue(instance.cache.isEmpty());
        MyMapEntity entity = createNewEntity();
        entity.setCreationDate(d1);
        MyMapEntity entity2 = createNewEntity();
        entity2.setCreationDate(d2);

        instance.create(entity);
        MyMapEntity createdEntity2 = instance.create(entity2);

        List<MyMapEntity> foundEntities;

        //no map has this creation date
        foundEntities = instance.findMapsByCreationDate(d3);
        assertNotNull(foundEntities);
        assertEquals(foundEntities.size(), 0);

        //one map has this creation date
        foundEntities = instance.findMapsByCreationDate(d2);
        assertNotNull(foundEntities);
        assertEquals(foundEntities.size(), 1);

        assertEquals(foundEntities.get(0), createdEntity2);
        EqualsBuilder.reflectionEquals(foundEntities.get(0), createdEntity2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindMapsByCreationDateNull() {
        instance.findMapsByCreationDate(null);
    }

    @Test
    public void testFindMapsByCreationDateInterval() {

        Date dLowest = new GregorianCalendar(1953, 6, 3).getTime();
        Date d1 = new GregorianCalendar(2013, 6, 3).getTime();
        Date d2 = new GregorianCalendar(2013, 2, 3).getTime();
        Date d3 = new GregorianCalendar(2010, 2, 3).getTime();
        Date dHighest = new GregorianCalendar(2030, 6, 3).getTime();

        assertTrue(instance.cache.isEmpty());
        MyMapEntity entity = createNewEntity();
        entity.setCreationDate(d1);
        MyMapEntity entity2 = createNewEntity();
        entity2.setCreationDate(d2);
        MyMapEntity entity3 = createNewEntity();
        entity3.setCreationDate(d3);

        MyMapEntity createdEntity1 = instance.create(entity);
        MyMapEntity createdEntity2 = instance.create(entity2);
        MyMapEntity createdEntity3 = instance.create(entity3);
        List<MyMapEntity> createdEntities = Arrays.asList(createdEntity3, createdEntity1, createdEntity2);


        List<MyMapEntity> foundEntities;

        //no map created within interval
        foundEntities = instance.findMapsByCreationDate(dLowest, dLowest);
        assertNotNull(foundEntities);
        assertEquals(0, foundEntities.size());


        //all maps in interval
        foundEntities = instance.findMapsByCreationDate(dLowest, dHighest);
        assertNotNull(foundEntities);
        assertEquals(3, foundEntities.size());

        for (MyMapEntity foundEntity : foundEntities) {
            boolean found = false;
            for (MyMapEntity mme : createdEntities) {
                if (foundEntity.equals(mme) && EqualsBuilder.reflectionEquals(foundEntity, mme)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                fail("Created entity not found in result");
            }
        }

        //all maps in interval including edge values
        foundEntities = instance.findMapsByCreationDate(d3, d1);
        assertNotNull(foundEntities);
        assertEquals(3, foundEntities.size());

        for (MyMapEntity foundEntity : foundEntities) {
            boolean found = false;
            for (MyMapEntity mme : createdEntities) {
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
    public void testFindMapsByCreationDateIntervalFromNull() {
        instance.findMapsByCreationDate(null, new Date());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindMapsByCreationDateIntervalToNull() {
        instance.findMapsByCreationDate(new Date(), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindMapsByCreationDateIntervalFromAfterTo() {
        instance.findMapsByCreationDate(new GregorianCalendar(2013, 5, 6).getTime(), new GregorianCalendar(2012, 5, 6).getTime());
    }

    @Test
    public void testFindMapsByCreator() {


        assertTrue(instance.cache.isEmpty());
        MyMapEntity entity = createNewEntity();
        entity.setCreator(user1);
        MyMapEntity entity2 = createNewEntity();
        entity2.setCreator(user1);
        MyMapEntity entity3 = createNewEntity();
        entity3.setCreator(user2);

        MyMapEntity createdEntity1 = instance.create(entity);
        MyMapEntity createdEntity2 = instance.create(entity2);
        MyMapEntity createdEntity3 = instance.create(entity3);
        List<MyMapEntity> createdEntities = Arrays.asList(createdEntity3, createdEntity1, createdEntity2);


        List<MyMapEntity> foundEntities;

        //no map created by user3
        foundEntities = instance.findMapsByCreator(user3);
        assertNotNull(foundEntities);
        assertEquals(0, foundEntities.size());


        //two maps of user1
        foundEntities = instance.findMapsByCreator(user1);
        assertNotNull(foundEntities);
        assertEquals(2, foundEntities.size());

        for (MyMapEntity foundEntity : foundEntities) {
            boolean found = false;
            for (MyMapEntity mme : createdEntities) {
                if (foundEntity.equals(mme) && EqualsBuilder.reflectionEquals(foundEntity, mme)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                fail("Created entity not found in result");
            }
        }

        //one maps of user2
        foundEntities = instance.findMapsByCreator(user2);
        assertNotNull(foundEntities);
        assertEquals(1, foundEntities.size());

        for (MyMapEntity foundEntity : foundEntities) {
            boolean found = false;
            for (MyMapEntity mme : createdEntities) {
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
    public void testFindMapsByCreatorNull() {
        instance.findMapsByCreator(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNull() {
        instance.create(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateIdNotNull() {
        MyMapEntity e = createNewEntity();
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

    private MyMapEntity createUpdatedEntity(MyMapEntity entity) {
        MyMapEntity updatedEntity = new MyMapEntity();

        updatedEntity.setId(entity.getId());
        entity.setName("mushrooms map - neighbourhood");
        entity.setView(view2);

        return updatedEntity;
    }

    private MyMapEntity createNewEntity() {
        MyMapEntity entity = new MyMapEntity();

        entity.setCreationDate(new Date());
        entity.setCreator(user1);
        entity.setName("mushrooms map");
        entity.setView(view1);
        entity.setPointsOfInterest(new ArrayList<PointOfInterestEntity>());

        return entity;
    }
}