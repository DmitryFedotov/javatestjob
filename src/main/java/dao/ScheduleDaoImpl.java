package dao;

import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class ScheduleDaoImpl implements ScheduleDao {

    public List getAll() {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From ScheduleEntity").list();
    }
}
