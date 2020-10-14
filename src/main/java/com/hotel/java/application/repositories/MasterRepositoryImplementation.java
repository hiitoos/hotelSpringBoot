package com.hotel.java.application.repositories;
/*

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


@Repository
public class MasterRepositoryImplementation implements MasterRepository{
    Session session;
    Transaction transaction;
    SessionFactory dbConnection;
    private static CriteriaBuilder cb;

    @Autowired
    public MasterRepositoryImplementation (SessionFactory dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public void newObject(Object object, boolean saveOrUpdate) {
        session = dbConnection.openSession();
        try {
            transaction = session.beginTransaction();
            if (saveOrUpdate)
                session.saveOrUpdate (object);
            else
                session.delete (object);
            transaction.commit();
        } catch (Throwable ex) {
            if (transaction!=null) transaction.rollback();
            ex.printStackTrace();
        }
        finally {
            session.close();
        }
    }



    @Override
    public List<Object> listarTodo(Class classEntity) {
        List<Object> objects = new ArrayList<> ();
        session = dbConnection.openSession ();
        cb = session.getCriteriaBuilder();
        try {
            CriteriaQuery<Object> q = cb.createQuery (classEntity);
            q.select (q.from (classEntity));
            objects = session.createQuery (q).getResultList ();
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        finally {
            session.close();
        }
        return objects;
    }

    @Override
    public Object listarById(long id, Class classEntity) {
        Object object = null;
        session = dbConnection.openSession ();
        try{
            object = session.get(classEntity, id);
        }catch (Throwable ex) {
            ex.printStackTrace ();
        }
        finally {
            session.close();
        }
        return object;
    }

    @Override
    public List<Object> listCampoGT(Class classEntity, int valor, String campo) {
        List<Object> objects = new ArrayList<> ();
        session = dbConnection.openSession ();
        cb = session.getCriteriaBuilder();
        try {
            CriteriaQuery<Object> q = cb.createQuery (classEntity);
            Root<Object> from = q.from (classEntity);
            q.select (from);
            q.where (cb.gt (from.get (campo), valor-1));
            objects = session.createQuery (q).getResultList ();
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        finally {
            session.close();
        }
        return objects;
    }

    @Override
    public List<Object> listCampoLT(Class classEntity, int valor, String campo) {
        List<Object> objects = new ArrayList<> ();
        session = dbConnection.openSession ();
        cb = session.getCriteriaBuilder();
        try {
            CriteriaQuery<Object> q = cb.createQuery (classEntity);
            Root<Object> from = q.from (classEntity);
            q.select (from);
            q.where (cb.lt (from.get (campo), valor-1));
            objects = session.createQuery (q).getResultList ();
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        finally {
            session.close();
        }
        return objects;
    }

    @Override
    public long newSignUp(Object object) {
        session = dbConnection.openSession();
        long res=0;
        try {
            transaction = session.beginTransaction();
            res=(long)session.save (object);
            transaction.commit();
        } catch (Throwable ex) {
            if (transaction!=null) transaction.rollback();
            ex.printStackTrace();
        }
        finally {
            session.close();
        }
        return res;
    }

    @Override
    public List<Object> showByType(Class classEntity, String campo) {
        List<Object> objects = new ArrayList<> ();
        session = dbConnection.openSession ();
        cb = session.getCriteriaBuilder();
        try {
            CriteriaQuery<Object> q = cb.createQuery (classEntity);
            Root<Object> from = q.from (classEntity);
            q.select (from);
            q.where (from.get (campo));
            q.orderBy (cb.asc(from.get(campo)));
            objects = session.createQuery (q).getResultList ();
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        finally {
            session.close();
        }
        return objects;
    }

    @Override
    public List<Object> showByTypeID(Class classEntity, long valor, String campo) {
        List<Object> objects = new ArrayList<> ();
        session = dbConnection.openSession ();
        cb = session.getCriteriaBuilder();
        try {
            CriteriaQuery<Object> q = cb.createQuery (classEntity);
            Root<Object> from = q.from (classEntity);
            q.select (from);
            q.where (cb.equal (from.get (campo), valor));
            objects = session.createQuery (q).getResultList ();
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        finally {
            session.close();
        }
        return objects;
    }

    @Override
    public Object listarCampo(String campo, Class classEntity, String valor) {
        Object object = null;
        session = dbConnection.openSession ();
        cb = session.getCriteriaBuilder();
        try{
            CriteriaQuery<Object> q = cb.createQuery (classEntity);
            Root<Object> from = q.from (classEntity);
            q.select (from);
            q.where (cb.equal (from.get (campo), valor));
            object = session.createQuery (q).getSingleResult ();
            object = session.get(classEntity, campo);
        }catch (Throwable ex) {
            ex.printStackTrace ();
        }
        finally {
            session.close();
        }
        return object;
    }
}*/