package org.generaltune.hdao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhumin on 2017/3/8.
 */
public interface IGenericDao<T extends Serializable> {

    T findOne(final long id);

    List<T> findAll();

    void create(final T entity);

     void setClazz( Class< T > clazzToSet );

    T update(final T entity);

    void delete(final T entity);

    void deleteById(final long entityId);
}

