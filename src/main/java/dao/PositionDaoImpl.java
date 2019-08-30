package dao;

import models.PositionsEntity;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class PositionDaoImpl implements PositionsDao {
    public PositionsEntity get(String id) {
        return null;
    }
    public List getAll() {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From PositionsEntity").list();
    }
}
