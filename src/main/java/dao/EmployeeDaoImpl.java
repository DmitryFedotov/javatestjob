package dao;

import models.EmployeesEntity;
import models.PhonesEntity;
import org.hibernate.Query;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import services.PhonesService;
import utils.HibernateSessionFactoryUtil;

import javax.swing.*;

public class EmployeeDaoImpl extends Crud implements EmployeeDao {

    private PhonesService phonesService = new PhonesService();

    public List findByFIO(String lastName, String firstName, String middleName) {
        Query query = HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From EmployeesEntity EM " +
                "WHERE EM.lastName = :l and EM.firstName = :f and EM.middleName = :m");
        query.setParameter("l", lastName);
        query.setParameter("f", firstName);
        query.setParameter("m", middleName);

        return query.list();
    }

    public boolean create(EmployeesEntity employee, List<PhonesEntity> phones){
        boolean result = true;
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.save(employee);
            for (PhonesEntity phone: phones
                 ) {
                phone.setEmployeesByEmployeeId(employee);
            }
            transaction.commit();
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
            result = false;
            if(transaction != null)
                transaction.rollback();
        }
        finally {
            session.close();
            for (PhonesEntity phone: phones
            ) {
                phonesService.create(phone);
            }
        }

        return result;
    }

    public List findByPosition(int position) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From EmployeesEntity EM WHERE EM.positionsByPositionId = "+position+"").list();
    }

    public List findBySchedule(int schedule) {

        return HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From EmployeesEntity EM WHERE EM.scheduleByScheduleId = "+schedule+"").list();
    }

    public List getAll() {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From EmployeesEntity").list();
    }


    public List get(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From EmployeesEntity EM WHERE EM.id = "+id+"").list();
    }
}
