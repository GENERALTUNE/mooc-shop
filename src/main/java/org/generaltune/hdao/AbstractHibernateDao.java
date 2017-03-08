package org.generaltune.hdao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhumin on 2017/3/8.
 */
public abstract class AbstractHibernateDao< T extends Serializable> {

    private Class< T > clazz;

    @Autowired
    SessionFactory sessionFactory;

    public final void setClazz( Class< T > clazzToSet ){
        this.clazz = clazzToSet;
    }

    public T findOne( long id ){
        return (T) getCurrentSession().get( clazz, id );
    }
    public List< T > findAll(){
        return getCurrentSession().createQuery( "from " + clazz.getName() ).list();
    }

    public void create( T entity ){
        getCurrentSession().persist( entity );
    }

    public T update( T entity ){
        return (T) getCurrentSession().merge( entity );
    }

    public void delete( T entity ){
        getCurrentSession().delete( entity );
    }
    public void deleteById( long entityId ) {
        T entity = findOne( entityId );
        delete( entity );
    }

    protected final Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}

