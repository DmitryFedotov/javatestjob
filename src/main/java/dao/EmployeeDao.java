package dao;

import java.util.List;

public interface EmployeeDao {
    List findByFIO(String firstName, String lastName, String middleName);
    List findByPosition(int position);
    List findBySchedule(int schedule);
    List getAll();
    List get(int id);
}
