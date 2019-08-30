package dao;

import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class PhonesDaoImpl extends Crud implements PhonesDao {

    public List getByEmpId(int ID) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From PhonesEntity P WHERE P.employeesByEmployeeId = "+ID+"").list();
    }


    public List getByNumber(String number) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From PhonesEntity P WHERE P.number = "+number+"").list();
    }

    public List getAll() {
        return null;
    }
}
