/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv243.mymaps.dao;

import cz.muni.fi.pv243.mymaps.entities.AbstractEntity;

/**
 *
 * @author Jiri Holusa
 */
public interface PointDao<T extends AbstractEntity> {

    T create(T entity);

    void delete(T entity);

    T getById(Long id);

    T update(T entity);
    
}
