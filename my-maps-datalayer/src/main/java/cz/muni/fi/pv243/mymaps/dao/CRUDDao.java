package cz.muni.fi.pv243.mymaps.dao;

import cz.muni.fi.pv243.mymaps.entities.AbstractEntity;

/**
 *
 * @author andrej
 */
public interface CRUDDao <T extends AbstractEntity> {

    T create(T entity);

    void delete(T entity);

    T getById(Long id);

    T update(T entity);
    
}
