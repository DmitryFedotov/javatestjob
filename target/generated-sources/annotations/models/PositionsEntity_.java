package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PositionsEntity.class)
public abstract class PositionsEntity_ {

	public static volatile SingularAttribute<PositionsEntity, String> name;
	public static volatile SingularAttribute<PositionsEntity, Integer> id;
	public static volatile CollectionAttribute<PositionsEntity, EmployeesEntity> employeesById;

}

