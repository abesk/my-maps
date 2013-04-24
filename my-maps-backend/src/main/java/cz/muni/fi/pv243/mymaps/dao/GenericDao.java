package cz.muni.fi.pv243.mymaps.dao;

import java.lang.reflect.ParameterizedType;


public class GenericDao<T> {
    protected Class<T> entityClass;

    public GenericDao() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        entityClass = (Class<T>) type.getActualTypeArguments()[0];

    }

    public T create(T entity) {
        /**
         * TODO : create code
         */
        return entity;
    }

    public void delete(T entity) {
        /**
         * TODO :delete code
         */

    }

    public T update(T entity) {
        /**
         * TODO : updatecode
         */
        return entity;
    }
}
