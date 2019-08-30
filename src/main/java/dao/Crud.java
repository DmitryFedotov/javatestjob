package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import javax.swing.*;

public abstract class Crud {
    public boolean create(Object object) {
        boolean result = true;
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.save(object);
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
        }

        return result;
    }

    public boolean update(Object object) {
        boolean result = true;
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.update(object);
            transaction.commit();
        }
        catch (Exception e){
            result = false;
            if(transaction != null)
                transaction.rollback();
        }
        finally {
            session.close();
        }

        return result;
    }

    public boolean delete(Object object) {
        boolean result = true;
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.delete(object);
            transaction.commit();
        }
        catch (Exception e){
            result = false;
            JOptionPane.showMessageDialog(null, e);
            if(transaction != null)
                transaction.rollback();
        }
        finally {
            session.close();
        }

        return result;
    }
}
