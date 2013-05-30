package cz.muni.fi.pv243.mymaps.dao;

import cz.muni.fi.pv243.mymaps.entities.AbstractEntity;

/**
 * Interface introduced to avoid redundancy of CRUD operations in every
 * DAO interface. Every DAO interface must extends this interface.
 *
 * @param <T> entity class with which will be manipulated
 * @author Andrej Bauer
 */
public interface GenericDao <T extends AbstractEntity> {

    /**
     * Inserts entity into data layer and sets its newly generated ID.
     *      
     * @throws IllegalStateException when data layer is not available due to internal error in CDI
     * @throws IllegalArgumentException when entity == null or entity.getId() != null
     * @param entity entity to be created
     * @return newly created entity with ID set
     */
    T create(T entity);

    /**
     * Deletes entity from datalayer.
     * 
     * @throws IllegalStateException when data layer is not available due to internal error
     * @throws IllegalArgumentException when entity == null or entity.getId() == null
     * @param entity entity to be deleted
     */
    void delete(T entity);

    /**
     * Retrieves entity with corresponding id.
     * 
     * @throws IllegalStateException when data layer is not available due to internal error
     * @throws IllegalArgumentException when id == null
     * @param id id of the entity to be retrieved
     * @return entity with corresponding id or null when entity with id doesn't exist
     */
    T getById(Long id);

    /**
     * Replaces entity in datalayer with corresponding ID by entity suplied in argument.
     * 
     * @throws IllegalStateException when data layer is not available due to internal error
     * @throws IllegalArgumentException when entity == null or entity.getId() == null
     * @param entity modified entity to be stored in datalayer
     * @return modified entity
     */
    T update(T entity);
    
}
