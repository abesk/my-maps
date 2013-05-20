package cz.muni.fi.pv243.mymaps.dao.impl;

import cz.muni.fi.pv243.mymaps.entities.MyMapEntity;
import java.lang.IllegalArgumentException;
import java.util.ArrayList;
import java.util.Collections;
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
        MyMapEntity entity = createNewEntity();
        assertNull("Default id must be null.", entity.getId());

        MyMapEntity result = instance.create(entity);

        assertNotNull("Created entity id must be non-null.", entity.getId());
        assertTrue(instance.cache.size() == 1);
        assertNotNull(instance.cache.get(entity.getId()));

        ArrayList<MyMapEntity> list = Collections.list(Collections.enumeration(instance.cache.values()));

        assertEquals(entity, result);
        assertEquals(entity, list.get(0));
        EqualsBuilder.reflectionEquals(entity, result);
        EqualsBuilder.reflectionEquals(entity, list.get(0));
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

        assertTrue(instance.cache.size() == 1);
        ArrayList<MyMapEntity> list = Collections.list(Collections.enumeration(instance.cache.values()));

        assertEquals(updated, result);
        assertEquals(updated, list.get(0));
        EqualsBuilder.reflectionEquals(updated, result);
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

    protected MyMapEntity createUpdatedEntity(MyMapEntity entity) {
        MyMapEntity updateDentity = new MyMapEntity();
        //TODO: clone entity to new one and change some attributes
        return entity;
    }

    protected MyMapEntity createNewEntity() {
        MyMapEntity entity = new MyMapEntity();
        //TODO: set some attributes
        return entity;
    }
}