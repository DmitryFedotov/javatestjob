package models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EmployeesEntity.class)
public abstract class EmployeesEntity_ {

	public static volatile SingularAttribute<EmployeesEntity, Date> birthday;
	public static volatile SingularAttribute<EmployeesEntity, String> firstName;
	public static volatile SingularAttribute<EmployeesEntity, String> lastName;
	public static volatile SingularAttribute<EmployeesEntity, PositionsEntity> positionsByPositionId;
	public static volatile SingularAttribute<EmployeesEntity, ScheduleEntity> scheduleByScheduleId;
	public static volatile SingularAttribute<EmployeesEntity, String> middleName;
	public static volatile SingularAttribute<EmployeesEntity, String> adress;
	public static volatile SingularAttribute<EmployeesEntity, String> comment;
	public static volatile SingularAttribute<EmployeesEntity, Integer> id;
	public static volatile CollectionAttribute<EmployeesEntity, PhonesEntity> phonesById;

}

