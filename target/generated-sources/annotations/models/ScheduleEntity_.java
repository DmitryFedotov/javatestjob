package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ScheduleEntity.class)
public abstract class ScheduleEntity_ {

	public static volatile SingularAttribute<ScheduleEntity, Integer> workTimeHours;
	public static volatile SingularAttribute<ScheduleEntity, String> shifts;
	public static volatile SingularAttribute<ScheduleEntity, Integer> id;
	public static volatile CollectionAttribute<ScheduleEntity, EmployeesEntity> employeesById;

}

