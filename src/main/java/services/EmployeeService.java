package services;

import dao.EmployeeDaoImpl;
import models.EmployeesEntity;
import models.PhonesEntity;

import java.util.List;

public class EmployeeService {
    private EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();

    public EmployeeService(){}

    public boolean createEmp(EmployeesEntity employee, List<PhonesEntity> phones){
        boolean result = true;
        if(!employeeDao.create(employee, phones))
            result = false;
        return result;
    }

    public boolean updateEmp(EmployeesEntity employee){
        boolean result = true;
        if(!employeeDao.update(employee))
            result = false;
        return result;
    }

    public boolean deleteEmp(EmployeesEntity employee){
        boolean result = true;
        if(!employeeDao.delete(employee))
            result = false;
        return result;
    }

    public List getAllEmp(){
        return employeeDao.getAll();
    }

    public List getByPos(int id){ return employeeDao.findByPosition(id);}

    public List getBySchedule(int id){return employeeDao.findBySchedule(id);}

    public List searchByFIO(String lastName, String firstName, String middleName){
        return employeeDao.findByFIO(lastName, firstName, middleName);
    }

    public List getEmpById(int id){return employeeDao.get(id);}
}
