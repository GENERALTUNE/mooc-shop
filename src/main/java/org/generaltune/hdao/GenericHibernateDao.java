package org.generaltune.hdao;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by zhumin on 2017/3/8.
 */
@Repository
@Scope( BeanDefinition.SCOPE_PROTOTYPE )
public class GenericHibernateDao< T extends Serializable>
        extends AbstractHibernateDao< T > implements IGenericDao< T >{
    //
}
