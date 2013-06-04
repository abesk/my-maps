package cz.muni.fi.pv243.mymaps.dao.impl;

import cz.muni.fi.pv243.mymaps.entities.MapType;
import cz.muni.fi.pv243.mymaps.entities.PointEntity;
import cz.muni.fi.pv243.mymaps.entities.ViewEntity;
import java.math.BigDecimal;
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
public class ViewDaoImplTest {

    @Inject
    private ViewDaoImpl instance;

    @Deployment
    public static WebArchive createDeployment() {
        return TestUtils.createDeploymentArchive()
                .addClass(ViewDaoImpl.class);
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
        ViewEntity entity = createNewEntity();
        assertNull("Default id must be null.", entity.getId());

        ViewEntity result = instance.create(entity);

        assertNotNull("Created entity id must be non-null.", result.getId());
        assertTrue(instance.cache.size() == 1);
        assertNotNull(instance.cache.get(result.getId()));
        assertEquals(entity, result);
        assertTrue(EqualsBuilder.reflectionEquals(entity, result));

        ArrayList<ViewEntity> list = Collections.list(Collections.enumeration(instance.cache.values()));

        assertEquals(result, list.get(0));
        assertTrue(EqualsBuilder.reflectionEquals(result, list.get(0)));
    }

    @Test
    public void testDelete() {
        assertTrue(instance.cache.isEmpty());
        ViewEntity entity = createNewEntity();

        instance.create(entity);

        assertFalse(instance.cache.isEmpty());
        assertTrue(instance.cache.size() == 1);

        instance.delete(entity);

        assertTrue(instance.cache.isEmpty());
    }

    @Test
    public void testUpdate() {

        assertTrue(instance.cache.isEmpty());
        ViewEntity entity = createNewEntity();
        assertNull("Default id must be null.", entity.getId());

        instance.create(entity);
        assertNotNull("Created entity id must be non-null.", entity.getId());

        ViewEntity updated = createUpdatedEntity(entity);
        ViewEntity result = instance.update(updated);
        assertNotNull("Updated entity id must be non-null.", result.getId());
        assertEquals(updated, result);
        assertTrue(EqualsBuilder.reflectionEquals(updated, result));

        assertTrue(instance.cache.size() == 1);
        ArrayList<ViewEntity> list = Collections.list(Collections.enumeration(instance.cache.values()));

        assertEquals(updated, list.get(0));
        assertTrue(EqualsBuilder.reflectionEquals(updated, list.get(0)));
    }

    @Test
    public void testGetById() {

        assertTrue(instance.cache.isEmpty());
        ViewEntity entity = createNewEntity();

        ViewEntity createdEntity = instance.create(entity);
        Long resultId = createdEntity.getId();

        assertNotNull(resultId);
        ViewEntity result = instance.getById(resultId);
        assertNotNull(result);
        assertNotNull(result.getId());


        assertEquals(result, createdEntity);
        assertTrue(EqualsBuilder.reflectionEquals(result, createdEntity));

        assertEquals(instance.cache.get(resultId), createdEntity);
        assertTrue(EqualsBuilder.reflectionEquals(instance.cache.get(resultId), createdEntity));
    }

    @Test
    public void testFindViewsByName() {

        assertTrue(instance.cache.isEmpty());
        ViewEntity entity = createNewEntity();
        entity.setName("test1");
        ViewEntity entity2 = createNewEntity();
        entity2.setName("test2");
        ViewEntity entity3 = createNewEntity();
        entity3.setName("test3");

        instance.create(entity);
        ViewEntity createdEntity2 = instance.create(entity2);
        instance.create(entity3);

        List<ViewEntity> foundEntities = instance.findViewsByName("test2");

        assertNotNull(foundEntities);
        assertEquals(foundEntities.size(), 1);

        assertEquals(foundEntities.get(0), createdEntity2);
        assertTrue(EqualsBuilder.reflectionEquals(foundEntities.get(0), createdEntity2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNull() {
        instance.create(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateIdNotNull() {
        ViewEntity e = createNewEntity();
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

    @Test(expected = IllegalArgumentException.class)
    public void testFindViewsByNameNull() {
        instance.findViewsByName(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindViewsByNameEmpty() {
        instance.findViewsByName("");
    }

    private ViewEntity createUpdatedEntity(ViewEntity entity) {
        ViewEntity updatedEntity = new ViewEntity();

        updatedEntity.setId(entity.getId());

        PointEntity pointNE = new PointEntity();
        pointNE.setLatitude(BigDecimal.ONE);
        pointNE.setLongitude(BigDecimal.ONE);

        PointEntity pointSW = new PointEntity();
        pointSW.setLatitude(BigDecimal.ONE);
        pointSW.setLongitude(BigDecimal.ONE);

        updatedEntity.setNorthEast(pointNE);
        updatedEntity.setSouthWest(pointSW);
        updatedEntity.setMapType(MapType.HYBRID);
        updatedEntity.setName("default_view2");

        return updatedEntity;
    }

    private ViewEntity createNewEntity() {
        ViewEntity entity = new ViewEntity();

        PointEntity pointNE = new PointEntity();
        pointNE.setLatitude(BigDecimal.ZERO);
        pointNE.setLongitude(BigDecimal.ZERO);

        PointEntity pointSW = new PointEntity();
        pointSW.setLatitude(BigDecimal.ZERO);
        pointSW.setLongitude(BigDecimal.ZERO);

        entity.setNorthEast(pointNE);
        entity.setSouthWest(pointSW);
        entity.setMapType(MapType.ROADMAP);
        entity.setName("default_view");

        return entity;
    }
}