package cz.muni.fi.pv243.mymaps.dao.impl;

import cz.muni.fi.pv243.mymaps.entities.PointEntity;
import cz.muni.fi.pv243.mymaps.entities.PointOfInterestEntity;
import java.math.BigDecimal;
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
public class PointOfInterestDaoImplTest {

    @Inject
    private PointOfInterestDaoImpl instance;

    @Deployment
    public static WebArchive createDeployment() {
        return TestUtils.createDeploymentArchive()
                .addClass(PointOfInterestDaoImpl.class);
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
        PointOfInterestEntity entity = createNewEntity();
        assertNull("Default id must be null.", entity.getId());

        PointOfInterestEntity result = instance.create(entity);

        assertNotNull("Created entity id must be non-null.", result.getId());
        assertTrue(instance.cache.size() == 1);
        assertNotNull(instance.cache.get(result.getId()));
        assertEquals(entity, result);
        assertTrue(EqualsBuilder.reflectionEquals(entity, result));

        ArrayList<PointOfInterestEntity> list = Collections.list(Collections.enumeration(instance.cache.values()));

        assertEquals(result, list.get(0));
        assertTrue(EqualsBuilder.reflectionEquals(result, list.get(0)));
    }

    @Test
    public void testDelete() {
        assertTrue(instance.cache.isEmpty());
        PointOfInterestEntity entity = createNewEntity();

        instance.create(entity);

        assertFalse(instance.cache.isEmpty());
        assertTrue(instance.cache.size() == 1);

        instance.delete(entity);

        assertTrue(instance.cache.isEmpty());
    }

    @Test
    public void testUpdate() {

        assertTrue(instance.cache.isEmpty());
        PointOfInterestEntity entity = createNewEntity();
        assertNull("Default id must be null.", entity.getId());

        instance.create(entity);
        assertNotNull("Created entity id must be non-null.", entity.getId());

        PointOfInterestEntity updated = createUpdatedEntity(entity);
        PointOfInterestEntity result = instance.update(updated);
        assertNotNull("Updated entity id must be non-null.", result.getId());
        assertEquals(updated, result);
        assertTrue(EqualsBuilder.reflectionEquals(updated, result));

        assertTrue(instance.cache.size() == 1);
        ArrayList<PointOfInterestEntity> list = Collections.list(Collections.enumeration(instance.cache.values()));

        assertEquals(updated, list.get(0));
        assertTrue(EqualsBuilder.reflectionEquals(updated, list.get(0)));
    }

    @Test
    public void testGetById() {

        assertTrue(instance.cache.isEmpty());
        PointOfInterestEntity entity = createNewEntity();

        PointOfInterestEntity createdEntity = instance.create(entity);
        Long resultId = createdEntity.getId();

        assertNotNull(resultId);
        PointOfInterestEntity result = instance.getById(resultId);
        assertNotNull(result);
        assertNotNull(result.getId());


        assertEquals(result, createdEntity);
        assertTrue(EqualsBuilder.reflectionEquals(result, createdEntity));

        assertEquals(instance.cache.get(resultId), createdEntity);
        assertTrue(EqualsBuilder.reflectionEquals(instance.cache.get(resultId), createdEntity));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNull() {
        instance.create(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateIdNotNull() {
        PointOfInterestEntity e = createNewEntity();
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

    private PointOfInterestEntity createUpdatedEntity(PointOfInterestEntity entity) {

        PointOfInterestEntity updatedEntity = new PointOfInterestEntity();

        updatedEntity.setId(entity.getId());

        PointEntity location = new PointEntity();
        location.setLatitude(BigDecimal.ONE);
        location.setLongitude(BigDecimal.ONE);
        updatedEntity.setLocation(location);

        return updatedEntity;
    }

    private PointOfInterestEntity createNewEntity() {
        PointOfInterestEntity userEntity = new PointOfInterestEntity();

        PointEntity location = new PointEntity();
        location.setLatitude(BigDecimal.ZERO);
        location.setLongitude(BigDecimal.ZERO);

        userEntity.setLocation(location);

        return userEntity;
    }
}