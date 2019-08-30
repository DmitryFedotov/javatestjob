package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PhonesEntity.class)
public abstract class PhonesEntity_ {

	public static volatile SingularAttribute<PhonesEntity, String> number;
	public static volatile SingularAttribute<PhonesEntity, Integer> id;
	public static volatile SingularAttribute<PhonesEntity, String> type;
	public static volatile SingularAttribute<PhonesEntity, EmployeesEntity> employeesByEmployeeId;

}

